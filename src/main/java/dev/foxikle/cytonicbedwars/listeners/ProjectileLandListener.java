package dev.foxikle.cytonicbedwars.listeners;

import dev.foxikle.cytonicbedwars.CytonicBedWars;
import dev.foxikle.cytonicbedwars.mobs.BedBug;
import dev.foxikle.cytonicbedwars.utils.Items;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Trident;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.persistence.PersistentDataType;

public class ProjectileLandListener implements Listener {
    private final CytonicBedWars plugin;

    public ProjectileLandListener(CytonicBedWars plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onProjectileLand(ProjectileHitEvent event) {
        if(event.getEntity() instanceof Snowball bedbug) {
            if(bedbug.getPersistentDataContainer().has(Items.NAMESPACE)){
                if(bedbug.getPersistentDataContainer().get(Items.NAMESPACE, PersistentDataType.BOOLEAN)) {
                    if(event.getEntity().getShooter() instanceof Player player) {
                        new BedBug(plugin.getGameManager().getPlayerTeam(player.getUniqueId()), event.getEntity().getLocation());
                    }
                }
            }
        } else if (event.getEntity() instanceof Trident trident) {
            if(trident.getPersistentDataContainer().has(Items.NAMESPACE)) {
                String id = trident.getPersistentDataContainer().get(Items.NAMESPACE, PersistentDataType.STRING);
                if(id.equals("TRIDENT_3")) {
                    trident.getLocation().getWorld().spawn(trident.getLocation(), LightningStrike.class);
                }
            }
        }
    }
}
