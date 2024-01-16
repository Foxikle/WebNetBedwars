package dev.foxikle.webnetbedwars.listeners;

import dev.foxikle.webnetbedwars.WebNetBedWars;
import dev.foxikle.webnetbedwars.data.objects.Generator;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

public class ItemPickupListener implements Listener {
    private final WebNetBedWars plugin;

    public ItemPickupListener(WebNetBedWars plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onItemPickup(EntityPickupItemEvent event) {
        if(event.getEntity() instanceof Player player) {
            if(plugin.getGameManager().spectators.contains(player.getUniqueId())) {
                event.setCancelled(true);
                return;
            }
            if (event.getItem().getPersistentDataContainer().has(Generator.SPLIT_KEY)) {
                player.getNearbyEntities(3, 3, 3).forEach(entity -> {
                    if(entity instanceof Player p) {
                        if(plugin.getGameManager().spectators.contains(p.getUniqueId())) return;
                        if(plugin.getGameManager().getPlayerTeam(p.getUniqueId()) == plugin.getGameManager().getPlayerTeam(player.getUniqueId()))
                            p.getInventory().addItem(event.getItem().getItemStack());
                    }
                });
            }
        }
    }
}
