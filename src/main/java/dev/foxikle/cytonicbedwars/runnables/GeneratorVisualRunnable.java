package dev.foxikle.cytonicbedwars.runnables;

import dev.foxikle.cytonicbedwars.CytonicBedWars;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.scheduler.BukkitRunnable;

public class GeneratorVisualRunnable extends BukkitRunnable {

    private final ArmorStand as;
    private final CytonicBedWars plugin;
    private double rots = 0.0;

    public GeneratorVisualRunnable(ArmorStand as, CytonicBedWars plugin) {
        this.as = as;
        this.plugin = plugin;
    }

    @Override
    public void run() {
        rots += (Math.PI/12);
        Location loc = as.getLocation().subtract(0, Math.sin(rots)/24, 0);
        loc.setYaw(as.getYaw() + 10.5F);
        as.teleport(loc);
    }

    public void start() {
       runTaskTimer(plugin, 0, 1);
    }
}
