package net.cytonic.cytonicbedwars.listeners;

import com.destroystokyo.paper.event.player.PlayerSetSpawnEvent;
import net.cytonic.cytonicbedwars.CytonicBedWars;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class SetSpawnPointListener implements Listener {
    private final CytonicBedWars plugin;

    public SetSpawnPointListener(CytonicBedWars plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onSetSpawn(PlayerSetSpawnEvent event) {
        event.setCancelled(true);
    }
}
