package net.cytonic.cytonicbedwars.listeners;

import net.cytonic.cytonicbedwars.CytonicBedWars;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;

public class SilverfishBurrowListener implements Listener {
    private final CytonicBedWars plugin;

    public SilverfishBurrowListener(CytonicBedWars plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onSilverfishBurrow(EntityChangeBlockEvent event) {
        if(event.getEntityType() == EntityType.SILVERFISH)
            event.setCancelled(true);
    }
}
