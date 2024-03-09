package dev.foxikle.cytonicbedwars.runnables;

import dev.foxikle.cytonicbedwars.CytonicBedWars;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Egg;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BlockIterator;

import java.util.Iterator;

public class BridgeEggRunnable extends BukkitRunnable {

    private final Egg egg;
    private final Location playerLocation;
    private final int maxDistance;
    private final BlockData blockData;
    private final CytonicBedWars plugin;
    private final int degradeTime;

    private Location lastLocation;

    public BridgeEggRunnable(Egg egg, Location playerLocation, int distance, BlockData blockData, CytonicBedWars plugin) {
        this.egg = egg;
        this.playerLocation = playerLocation;
        this.maxDistance = distance;
        this.blockData = blockData;
        this.plugin = plugin;
        degradeTime = plugin.getConfig().getInt("BridgeEggBlockDespawn");
    }

    @Override
    public void run() {
        if (egg.isDead()) {
            cancel();
        } else {
            Location twoBlocksDown = egg.getLocation().subtract(0, 2, 0);
            double distanceFromStart = twoBlocksDown.distance(playerLocation);

            if (lastLocation == null)
                lastLocation = twoBlocksDown;

            if (distanceFromStart < maxDistance) {
                scheduleSegmentPlace(twoBlocksDown);
            } else {
                egg.remove();
                cancel();
            }

            lastLocation = twoBlocksDown;
        }
    }

    private void scheduleSegmentPlace(Location location) {
        Bukkit.getScheduler().runTaskLater(plugin, () -> placeSegment(location), 5);
    }

    private void placeSegment(Location location) {
        var segmentRaytrace = new BlockIterator(location, 0, 1);
        replaceNonSolidBlocks(segmentRaytrace);
    }

    private void replaceNonSolidBlocks(Iterator<Block> blocks) {
        blocks.forEachRemaining(this::setData);
    }

    private void setData(Block block) {
        if (block.getType() == Material.AIR) {
            plugin.getGameManager().getWorldManager().setBlock(blockData.getMaterial(), block.getLocation());
            Bukkit.getScheduler().runTaskLater(plugin, () -> block.setType(Material.AIR), degradeTime);
            new BlockDamageRunnable(degradeTime, block.getLocation()).runTaskTimerAsynchronously(plugin, 0, 1);
        }
    }
}
