package net.cytonic.cytonicbedwars.listeners;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import net.cytonic.cytonicbedwars.CytonicBedWars;
import net.cytonic.cytonicbedwars.utils.Items;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ArmorEquipListener implements Listener {
    private final CytonicBedWars plugin;

    public ArmorEquipListener(CytonicBedWars plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onArmorEquip(PlayerArmorChangeEvent event) {
        if(event.getOldItem().getType() == Material.BARRIER && event.getPlayer().getGameMode() == GameMode.ADVENTURE) {
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                event.getPlayer().getInventory().setBoots(Items.SPECTATOR_ARMOR);
                event.getPlayer().getInventory().setLeggings(Items.SPECTATOR_ARMOR);
                event.getPlayer().getInventory().setChestplate(Items.SPECTATOR_ARMOR);
            }, 1);
        }
    }
}
