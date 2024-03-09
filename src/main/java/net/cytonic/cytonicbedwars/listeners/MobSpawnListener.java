package net.cytonic.cytonicbedwars.listeners;
import net.cytonic.cytonicbedwars.CytonicBedWars;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class MobSpawnListener implements Listener {
    private final CytonicBedWars plugin;

    public MobSpawnListener(CytonicBedWars plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onMobSpawn(EntitySpawnEvent e) {
        if(e.getEntityType() == EntityType.ENDERMITE || e.getEntityType() == EntityType.CHICKEN)
            e.setCancelled(true);
    }
}
