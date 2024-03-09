package dev.foxikle.cytonicbedwars.listeners;

import dev.foxikle.cytonicbedwars.CytonicBedWars;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class HungerDepleteListener implements Listener {
    private final CytonicBedWars plugin;

    public HungerDepleteListener(CytonicBedWars plugin) {this.plugin = plugin;}

    @EventHandler
    public void onHungerDeplete(FoodLevelChangeEvent e) {
        e.getEntity().setFoodLevel(20);
        e.setCancelled(true);
    }
}