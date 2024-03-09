package net.cytonic.cytonicbedwars.listeners;

import net.cytonic.cytonicbedwars.CytonicBedWars;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class MoveListener implements Listener {
    private final CytonicBedWars plugin;

    public MoveListener(CytonicBedWars plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event){
        if(!plugin.getGameManager().STARTED) return;
        if(event.getTo().y() <= -64){
            if(!plugin.getGameManager().spectators.contains(event.getPlayer().getUniqueId()))
                plugin.getGameManager().kill(event.getPlayer(), null, EntityDamageEvent.DamageCause.VOID);
            event.setCancelled(true);
        }
        Location spawn = plugin.getLocation("SpawnPlatformCenter");
        if(distance(event.getTo().x(), spawn.x(), event.getTo().z(), spawn.z()) > 105.0) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.RED + "You cannot travel too far from the map!");
        }
    }

    private double distance (double x1, double x2, double z1, double z2) {
        return Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(z1-z2, 2));
    }
}
