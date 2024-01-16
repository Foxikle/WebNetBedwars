package dev.foxikle.webnetbedwars.commands;

import dev.foxikle.webnetbedwars.WebNetBedWars;
import dev.foxikle.webnetbedwars.data.enums.GameState;
import dev.foxikle.webnetbedwars.data.enums.MappableItem;
import dev.foxikle.webnetbedwars.mobs.BedBug;
import dev.foxikle.webnetbedwars.utils.Items;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DebugCommand implements CommandExecutor, TabCompleter {
    private final WebNetBedWars plugin;

    public DebugCommand(WebNetBedWars plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player player){
            if(player.hasPermission("webnet.bedwars.debug")) {
                if(args.length >= 1){
                    switch (args[0].toLowerCase()){
                        case "start" -> {
                            if(plugin.getGameManager().STARTED) {
                                player.sendMessage( ChatColor.RED + "The game has already been started! Use '/debug stop' or '/debug end' to end it!");
                                return true;
                            }
                            player.sendMessage(ChatColor.GREEN + "Starting game!");
                            plugin.getGameManager().start();
                        }
                        case "end", "stop" -> {
                            if(plugin.getGameManager().STARTED) {
                                player.sendMessage(ChatColor.GREEN + "Ending game!");
                                plugin.getGameManager().cleanup();
                            } else {
                                player.sendMessage(ChatColor.RED + "The game is not started! Start it first with '/debug start'!");
                            }
                        }
                        case "listteams" -> {
                            plugin.getGameManager().getTeamlist().forEach(team -> player.sendMessage(team.prefix() + team.displayName()));
                        }
                        case "freeze", "f" -> {
                            if(plugin.getGameManager().getGameState() != GameState.FROZEN){
                                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&eThe game is now &b&lFROZEN&r&e!"));
                                plugin.getGameManager().freeze();
                            } else {
                                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&eThe game is now &6&lTHAWED&r&e!"));
                                plugin.getGameManager().thaw();
                            }
                        }
                        case "itemshop" -> {
                            if(!plugin.getGameManager().STARTED) {
                                player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "!! WARNING !!" + ChatColor.RESET + " " + ChatColor.RED + "The game has not been started. Some shop pages may not work!");
                            }

                            plugin.getGameManager().getMenuManager().getBlocksShop().open(player);
                        }
                        case "bedbug" -> new BedBug(plugin.getGameManager().getPlayerTeam(player.getUniqueId()), player.getLocation());
                        case "popup" -> plugin.getGameManager().getWorldManager().pastePopupTower(player.getLocation(), Items.getTeamMapped(MappableItem.WOOL, plugin.getGameManager().getPlayerTeam(player.getUniqueId())).getType());
                    }
                }
            }
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender.hasPermission("webnet.bedwars.item_command")) {
            return List.of("end", "listteams", "start", "freeze", "itemshop", "bedbug", "popup");
        }
        return new ArrayList<>();
    }
}
