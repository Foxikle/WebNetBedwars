package net.cytonic.cytonicbedwars.listeners;

import net.cytonic.cytonicbedwars.CytonicBedWars;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListener implements Listener {

    private final CytonicBedWars plugin;

    public QuitListener(CytonicBedWars plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        String prefix = ChatColor.YELLOW + "";
        try {
            prefix = event.getPlayer().getScoreboard().getPlayerTeam(event.getPlayer()).getPrefix();
        } catch (NullPointerException ignored) {}

        if (plugin.getGameManager().STARTED) {
            event.setQuitMessage(prefix + event.getPlayer().getDisplayName() + ChatColor.GRAY + " disconnected!");
        } else {
            event.setQuitMessage(prefix + event.getPlayer().getDisplayName() + ChatColor.YELLOW + " has quit!");
        }
    }

}
