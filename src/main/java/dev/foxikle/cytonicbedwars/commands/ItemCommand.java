package dev.foxikle.cytonicbedwars.commands;

import dev.foxikle.cytonicbedwars.utils.Items;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ItemCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player player){
            if(player.hasPermission("cytonic.bedwars.item_command")){
                if(args.length == 1){
                    if(Items.get(args[0]) != null) {
                        player.getInventory().addItem(Items.get(args[0]));
                        player.sendMessage(ChatColor.GREEN + "Gave you 1 " + args[0]);
                    } else {
                        player.sendMessage(ChatColor.RED + "Invalid item ID: '" + args[0] + "'");
                    }
                } else if (args.length >= 2) {
                    if(Items.get(args[0]) != null) {
                        try {
                            Integer.parseInt(args[1]);
                        } catch (NumberFormatException ignored){
                            player.sendMessage(ChatColor.RED + "'" + args[1] + "' is not a valid integer!");
                            return true;
                        }
                        ItemStack clone = Items.get(args[0]).clone();
                        clone.setAmount(Integer.parseInt(args[1]));
                        player.getInventory().addItem(clone);
                        player.sendMessage(ChatColor.GREEN + "Gave you " + Integer.parseInt(args[1]) + " " + args[0]);
                    } else {
                        player.sendMessage(ChatColor.RED + "Invalid item ID: '" + args[0] + "'");
                    }
                }
            }
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        List<String> returnme = new ArrayList<>();
        if(sender.hasPermission("cytonic.bedwars.item_command")) {
            if(args.length == 1)
                returnme.addAll(Items.getItemIDs());
            else if (args.length == 2)
                returnme.addAll(List.of("1", "64"));
        }
        return returnme;
    }
}
