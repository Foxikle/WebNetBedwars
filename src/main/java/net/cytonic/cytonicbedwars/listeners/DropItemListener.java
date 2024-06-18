package net.cytonic.cytonicbedwars.listeners;

import net.cytonic.cytonicbedwars.CytonicBedWars;
import net.cytonic.cytonicbedwars.utils.Items;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class DropItemListener implements Listener {
    private final CytonicBedWars plugin;

    public DropItemListener(CytonicBedWars plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItemDrop().getItemStack();
        ItemMeta meta = item.getItemMeta();
        if(meta.getPersistentDataContainer().has(Items.NO_DROP)) {
            if(meta.getPersistentDataContainer().get(Items.NO_DROP, PersistentDataType.BOOLEAN)){
                event.setCancelled(true);
                return;
            }
        }

        if(player.getFallDistance() > 0){
            // prevent dropping items whilst falling
            event.setCancelled(true);
        }
    }
}
