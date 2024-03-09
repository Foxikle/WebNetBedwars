package net.cytonic.cytonicbedwars.listeners;

import net.cytonic.cytonicbedwars.CytonicBedWars;
import net.cytonic.cytonicbedwars.utils.Items;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemMergeEvent;

public class ItemMergeListener implements Listener {
    private final CytonicBedWars plugin;

    public ItemMergeListener(CytonicBedWars plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onItemMerge(ItemMergeEvent event) {
        if(event.getEntity().getPersistentDataContainer().has(Items.NAMESPACE)) {
            event.setCancelled(true);
        }
    }
}
