package net.cytonic.cytonicbedwars.listeners;

import net.cytonic.cytonicbedwars.CytonicBedWars;
import net.cytonic.cytonicbedwars.utils.Items;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;

public class InventoryClickListener implements Listener {

    private final CytonicBedWars plugin;

    public InventoryClickListener(CytonicBedWars plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if(event.getWhoClicked().getGameMode() == GameMode.CREATIVE) return;
        ItemStack item = event.getCurrentItem();
        if(item == null) return;
        if (item.getItemMeta() != null) {
            if (item.getItemMeta().getPersistentDataContainer().has(Items.MOVE_BLACKLIST))
                if (item.getItemMeta().getPersistentDataContainer().get(Items.MOVE_BLACKLIST, PersistentDataType.BOOLEAN)) {
                    event.setCancelled(true);
                    if (item.getItemMeta().getPersistentDataContainer().has(Items.ALLOWED_SLOTS)) {
                        int[] slots = item.getItemMeta().getPersistentDataContainer().get(Items.ALLOWED_SLOTS, PersistentDataType.INTEGER_ARRAY);
                        if(Arrays.stream(slots).allMatch(value -> value != event.getSlot())){
                            event.setCurrentItem(new ItemStack(Material.AIR));
                            event.setCursor(new ItemStack(Material.AIR));
                            event.getWhoClicked().sendMessage(ChatColor.RED + "Hey! We noticed a blacklisted item in your inventory, so we took it. Sorry! (slot " + event.getSlot() + ")");
                        }
                    }
                }
        }
    }
}

