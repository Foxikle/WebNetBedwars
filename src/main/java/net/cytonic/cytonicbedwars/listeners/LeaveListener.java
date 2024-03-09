package net.cytonic.cytonicbedwars.listeners;

import net.cytonic.cytonicbedwars.CytonicBedWars;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LeaveListener implements Listener {
    private final CytonicBedWars plugin;

    public LeaveListener(CytonicBedWars plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    private void onJoin(PlayerQuitEvent event){
        if(plugin.getGameManager().STARTED){
            plugin.getGameManager().getScoreboardManager().removeScoreboard(event.getPlayer().getUniqueId());
            if(plugin.getGameManager().getPlayerTeam(event.getPlayer().getUniqueId()) == null){
                // they are a spectator
                Player player = event.getPlayer();
                Bukkit.getOnlinePlayers().forEach(p -> p.showPlayer(plugin, player));
                player.clearActivePotionEffects();
                plugin.getGameManager().spectators.remove(player.getUniqueId());
            }
        }
    }
}
