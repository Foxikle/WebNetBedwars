package dev.foxikle.webnetbedwars.commands;

import dev.foxikle.webnetbedwars.WebNetBedWars;
import dev.foxikle.webnetbedwars.data.objects.Team;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TeamShopCommand implements CommandExecutor {
    private final WebNetBedWars plugin;
    public TeamShopCommand(WebNetBedWars plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = (Player) sender;
        if (plugin.getGameManager().STARTED) {
            for (Team t : plugin.getGameManager().getTeamlist())
                if (player.getLocation().distance((t.teamShopLocation())) <= 5) {
                    player.sendMessage(ChatColor.BOLD + "" + ChatColor.DARK_RED + "COMING SOON");}}
        return true;}}