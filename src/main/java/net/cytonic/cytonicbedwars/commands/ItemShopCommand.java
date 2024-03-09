package net.cytonic.cytonicbedwars.commands;

import net.cytonic.cytonicbedwars.CytonicBedWars;
import net.cytonic.cytonicbedwars.data.objects.Team;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
public class ItemShopCommand implements CommandExecutor {

    private final CytonicBedWars plugin;
    public ItemShopCommand(CytonicBedWars plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = (Player) sender;
        if (plugin.getGameManager().STARTED) {
            for (Team t : plugin.getGameManager().getTeamlist())
                    if (player.getLocation().distance(t.itemShopLocation()) <= 5) {
                        plugin.getGameManager().getMenuManager().getBlocksShop().open(player);
                    }
        }
        return true;
    }
}