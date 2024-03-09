package dev.foxikle.cytonicbedwars.runnables;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class BlockDamageRunnable extends BukkitRunnable {
    private final int duration;
    private final Location location;
    int elapsed = 0;

    public BlockDamageRunnable(int duration, Location location) {
        this.duration = duration;
        this.location = location;
    }

    @Override
    public void run() {
        elapsed++;
        if(elapsed >= duration) {
            this.cancel();
            return;
        }
        Bukkit.getOnlinePlayers().forEach(player -> player.sendBlockDamage(location, 1.0f * elapsed / duration, new Random().nextInt()));
    }
}
