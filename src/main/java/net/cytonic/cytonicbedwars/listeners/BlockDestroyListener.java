package net.cytonic.cytonicbedwars.listeners;

import com.destroystokyo.paper.event.block.BlockDestroyEvent;
import net.cytonic.cytonicbedwars.CytonicBedWars;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.metadata.MetadataValue;

public class BlockDestroyListener implements Listener {
    private final CytonicBedWars plugin;

    public BlockDestroyListener(CytonicBedWars plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockDestroy(BlockDestroyEvent e) {
       if (e.getBlock().getType().name().contains("BED")) {
           e.setWillDrop(false);
           return;
       }
       e.setWillDrop(false);
       e.setPlayEffect(false);

        for (MetadataValue mv : e.getBlock().getMetadata("blockdata")) {
            if (mv.getOwningPlugin() instanceof CytonicBedWars) {
                e.setCancelled(!mv.asBoolean());
            }
        }
    }

    @EventHandler
    public void onPhysics(BlockPhysicsEvent e) {
        if(e.getBlock().getType() == Material.WATER) return;
        if(e.getBlock().getType().name().contains("GLASS")) return;
        if(e.getBlock().getType().name().contains("STAIRS")) return;
        if(e.getBlock().getType().name().contains("WALL")) return;
        if (e.getBlock().getType().name().contains("BED")) {
            e.setCancelled(true);
            return;
        }
        for (MetadataValue mv : e.getBlock().getMetadata("blockdata")) {
            if (mv.getOwningPlugin() instanceof CytonicBedWars) {
                e.setCancelled(!mv.asBoolean());
            }
        }
    }
}
