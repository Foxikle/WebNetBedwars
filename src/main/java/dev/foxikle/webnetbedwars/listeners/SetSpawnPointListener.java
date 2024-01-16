package dev.foxikle.webnetbedwars.listeners;

import com.destroystokyo.paper.event.player.PlayerSetSpawnEvent;
import dev.foxikle.webnetbedwars.WebNetBedWars;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class SetSpawnPointListener implements Listener {
    private final WebNetBedWars plugin;

    public SetSpawnPointListener(WebNetBedWars plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onSetSpawn(PlayerSetSpawnEvent event) {
        event.setCancelled(true);
    }
}
