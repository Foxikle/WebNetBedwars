package dev.foxikle.webnetbedwars.listeners;

import com.destroystokyo.paper.event.player.PlayerLaunchProjectileEvent;
import dev.foxikle.webnetbedwars.WebNetBedWars;
import dev.foxikle.webnetbedwars.data.enums.MappableItem;
import dev.foxikle.webnetbedwars.runnables.BridgeEggRunnable;
import dev.foxikle.webnetbedwars.utils.Items;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Trident;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.logging.Level;

public class ProjectileShootListener implements Listener {
    private final WebNetBedWars plugin;

    public ProjectileShootListener(WebNetBedWars plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onEntityMove(PlayerLaunchProjectileEvent event) {
        if(event.getProjectile() instanceof Trident trident) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (trident.getLocation().y() <= -40) {
                        Ghast g = trident.getWorld().spawn(trident.getLocation(), Ghast.class);
                        g.setSilent(true);
                        trident.hitEntity(g);
                        Bukkit.getScheduler().runTaskLater(plugin, g::remove, 1);
                        trident.setDamage(1);
                        this.cancel();
                    }
                }
            }.runTaskTimer(plugin, 0, 1);
            if(event.getItemStack().getItemMeta().getPersistentDataContainer().has(Items.NAMESPACE)) {
                String id = event.getItemStack().getItemMeta().getPersistentDataContainer().get(Items.NAMESPACE, PersistentDataType.STRING);
                trident.getPersistentDataContainer().set(Items.NAMESPACE, PersistentDataType.STRING, id);
            }
        } else if (event.getProjectile() instanceof Snowball bedbug) {
                bedbug.setShooter(event.getPlayer());
                bedbug.getPersistentDataContainer().set(Items.NAMESPACE, PersistentDataType.BOOLEAN, true);
        } else if (event.getProjectile() instanceof Egg egg) {
            new BridgeEggRunnable(egg,event.getPlayer().getLocation(), plugin.getConfig().getInt("BridgeEggBlockLimit"), Items.getTeamMapped(MappableItem.WOOL, plugin.getGameManager().getPlayerTeam(event.getPlayer().getUniqueId())).getType().createBlockData(), plugin).runTaskTimer(plugin, 2, 1);
        }
    }
}
