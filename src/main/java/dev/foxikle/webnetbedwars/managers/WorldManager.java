package dev.foxikle.webnetbedwars.managers;

import com.infernalsuite.aswm.api.SlimePlugin;
import com.infernalsuite.aswm.api.exceptions.CorruptedWorldException;
import com.infernalsuite.aswm.api.exceptions.NewerFormatException;
import com.infernalsuite.aswm.api.exceptions.UnknownWorldException;
import com.infernalsuite.aswm.api.exceptions.WorldLockedException;
import com.infernalsuite.aswm.api.loaders.SlimeLoader;
import com.infernalsuite.aswm.api.world.SlimeWorld;
import com.infernalsuite.aswm.api.world.properties.SlimeProperties;
import com.infernalsuite.aswm.api.world.properties.SlimePropertyMap;
import dev.foxikle.webnetbedwars.WebNetBedWars;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.metadata.FixedMetadataValue;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;

import static org.bukkit.Material.AIR;
import static org.bukkit.Material.SCAFFOLDING;

public class WorldManager {
    private final WebNetBedWars plugin;
    private final GameManager gameManager;
    private SlimeWorld world;

    public WorldManager(WebNetBedWars plugin, GameManager gameManager) {
        this.plugin = plugin;
        this.gameManager = gameManager;
    }

    public void createSpawnPlatform() {
        Location loc = plugin.getLocation("SpawnPlatformCenter");
        int x = (int) loc.getX();
        int y = (int) loc.getY();
        int z = (int) loc.getZ();
        World world = loc.getWorld();
        world.setSpawnLocation(loc);

        Bukkit.getScheduler().runTask(plugin, () -> {
            world.getBlockAt(x, y, z).setType(Material.BEACON);
            world.getBlockAt(x + 1, y, z).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x + 2, y, z).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x + 3, y, z).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x + 4, y, z).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x + 5, y, z).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x - 1, y, z).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x - 2, y, z).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x - 3, y, z).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x - 4, y, z).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x - 5, y, z).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x, y, z + 1).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x + 1, y, z + 1).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x + 2, y, z + 1).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x + 3, y, z + 1).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x + 4, y, z + 1).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x + 5, y, z + 1).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x - 1, y, z + 1).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x - 2, y, z + 1).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x - 3, y, z + 1).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x - 4, y, z + 1).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x - 5, y, z + 1).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x, y, z - 1).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x + 1, y, z - 1).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x + 2, y, z - 1).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x + 3, y, z - 1).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x + 4, y, z - 1).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x + 5, y, z - 1).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x - 1, y, z - 1).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x - 2, y, z - 1).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x - 3, y, z - 1).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x - 4, y, z - 1).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x - 5, y, z - 1).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x, y, z - 2).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x + 1, y, z - 2).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x + 2, y, z - 2).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x + 3, y, z - 2).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x + 4, y, z - 2).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x - 1, y, z - 2).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x - 2, y, z - 2).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x - 3, y, z - 2).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x - 4, y, z - 2).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x, y, z - 3).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x + 1, y, z - 3).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x + 2, y, z - 3).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x + 3, y, z - 3).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x + 4, y, z - 3).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x - 1, y, z - 3).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x - 2, y, z - 3).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x - 3, y, z - 3).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x - 4, y, z - 3).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x, y, z + 2).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x + 1, y, z + 2).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x + 2, y, z + 2).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x + 3, y, z + 2).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x + 4, y, z + 2).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x - 1, y, z + 2).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x - 2, y, z + 2).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x - 3, y, z + 2).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x - 4, y, z + 2).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x, y, z + 3).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x + 1, y, z + 3).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x + 2, y, z + 3).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x + 3, y, z + 3).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x + 4, y, z + 3).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x - 1, y, z + 3).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x - 2, y, z + 3).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x - 3, y, z + 3).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x - 4, y, z + 3).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x, y, z - 4).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x + 1, y, z - 4).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x + 2, y, z - 4).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x + 3, y, z - 4).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x - 1, y, z - 4).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x - 2, y, z - 4).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x - 3, y, z - 4).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x, y, z + 4).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x + 1, y, z + 4).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x + 2, y, z + 4).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x + 3, y, z + 4).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x - 1, y, z + 4).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x - 2, y, z + 4).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x - 3, y, z + 4).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x, y, z - 5).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x + 1, y, z - 5).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x - 1, y, z - 5).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x, y, z + 5).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x + 1, y, z + 5).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x - 1, y, z + 5).setType(Material.GRAY_STAINED_GLASS);
            world.getBlockAt(x + 1, y + 1, z - 5).setType(Material.WHITE_STAINED_GLASS_PANE); // WALLS V LAYER 1
            world.getBlockAt(x + 1, y + 1, z - 4).setType(Material.WHITE_STAINED_GLASS_PANE);
            world.getBlockAt(x + 2, y + 1, z - 4).setType(Material.WHITE_STAINED_GLASS_PANE);
            world.getBlockAt(x + 3, y + 1, z - 4).setType(Material.WHITE_STAINED_GLASS_PANE);
            world.getBlockAt(x + 3, y + 1, z - 3).setType(Material.COBBLED_DEEPSLATE_WALL);
            world.getBlockAt(x + 4, y + 1, z - 3).setType(Material.WHITE_STAINED_GLASS_PANE);
            world.getBlockAt(x + 4, y + 1, z - 2).setType(Material.WHITE_STAINED_GLASS_PANE);
            world.getBlockAt(x + 4, y + 1, z - 1).setType(Material.WHITE_STAINED_GLASS_PANE);
            world.getBlockAt(x + 5, y + 1, z - 1).setType(Material.WHITE_STAINED_GLASS_PANE);
            world.getBlockAt(x - 1, y + 1, z - 5).setType(Material.WHITE_STAINED_GLASS_PANE);
            world.getBlockAt(x - 1, y + 1, z - 4).setType(Material.WHITE_STAINED_GLASS_PANE);
            world.getBlockAt(x - 2, y + 1, z - 4).setType(Material.WHITE_STAINED_GLASS_PANE);
            world.getBlockAt(x - 3, y + 1, z - 4).setType(Material.WHITE_STAINED_GLASS_PANE);
            world.getBlockAt(x - 3, y + 1, z - 3).setType(Material.COBBLED_DEEPSLATE_WALL);
            world.getBlockAt(x - 4, y + 1, z - 3).setType(Material.WHITE_STAINED_GLASS_PANE);
            world.getBlockAt(x - 4, y + 1, z - 2).setType(Material.WHITE_STAINED_GLASS_PANE);
            world.getBlockAt(x - 4, y + 1, z - 1).setType(Material.WHITE_STAINED_GLASS_PANE);
            world.getBlockAt(x - 5, y + 1, z - 1).setType(Material.WHITE_STAINED_GLASS_PANE);
            world.getBlockAt(x + 1, y + 1, z + 5).setType(Material.WHITE_STAINED_GLASS_PANE);
            world.getBlockAt(x + 1, y + 1, z + 4).setType(Material.WHITE_STAINED_GLASS_PANE);
            world.getBlockAt(x + 2, y + 1, z + 4).setType(Material.WHITE_STAINED_GLASS_PANE);
            world.getBlockAt(x + 3, y + 1, z + 4).setType(Material.WHITE_STAINED_GLASS_PANE);
            world.getBlockAt(x + 3, y + 1, z + 3).setType(Material.COBBLED_DEEPSLATE_WALL);
            world.getBlockAt(x + 4, y + 1, z + 3).setType(Material.WHITE_STAINED_GLASS_PANE);
            world.getBlockAt(x + 4, y + 1, z + 2).setType(Material.WHITE_STAINED_GLASS_PANE);
            world.getBlockAt(x + 4, y + 1, z + 1).setType(Material.WHITE_STAINED_GLASS_PANE);
            world.getBlockAt(x + 5, y + 1, z + 1).setType(Material.WHITE_STAINED_GLASS_PANE);
            world.getBlockAt(x - 1, y + 1, z + 5).setType(Material.WHITE_STAINED_GLASS_PANE);
            world.getBlockAt(x - 1, y + 1, z + 4).setType(Material.WHITE_STAINED_GLASS_PANE);
            world.getBlockAt(x - 2, y + 1, z + 4).setType(Material.WHITE_STAINED_GLASS_PANE);
            world.getBlockAt(x - 3, y + 1, z + 4).setType(Material.WHITE_STAINED_GLASS_PANE);
            world.getBlockAt(x - 3, y + 1, z + 3).setType(Material.COBBLED_DEEPSLATE_WALL);
            world.getBlockAt(x - 4, y + 1, z + 3).setType(Material.WHITE_STAINED_GLASS_PANE);
            world.getBlockAt(x - 4, y + 1, z + 2).setType(Material.WHITE_STAINED_GLASS_PANE);
            world.getBlockAt(x - 4, y + 1, z + 1).setType(Material.WHITE_STAINED_GLASS_PANE);
            world.getBlockAt(x - 5, y + 1, z + 1).setType(Material.WHITE_STAINED_GLASS_PANE);
            world.getBlockAt(x - 5, y + 1, z).setType(Material.WHITE_STAINED_GLASS_PANE, true);
            world.getBlockAt(x + 5, y + 1, z).setType(Material.WHITE_STAINED_GLASS_PANE, true);
            world.getBlockAt(x, y + 1, z + 5).setType(Material.WHITE_STAINED_GLASS_PANE, true);
            world.getBlockAt(x, y + 1, z - 5).setType(Material.WHITE_STAINED_GLASS_PANE, true);
            world.getBlockAt(x + 1, y + 2, z - 5).setType(Material.WHITE_STAINED_GLASS_PANE); // WALLS LAYER 2 V
            world.getBlockAt(x + 1, y + 2, z - 4).setType(Material.WHITE_STAINED_GLASS_PANE);
            world.getBlockAt(x + 2, y + 2, z - 4).setType(Material.WHITE_STAINED_GLASS_PANE);
            world.getBlockAt(x + 3, y + 2, z - 4).setType(Material.WHITE_STAINED_GLASS_PANE);
            world.getBlockAt(x + 3, y + 2, z - 3).setType(Material.COBBLED_DEEPSLATE_WALL);
            world.getBlockAt(x + 4, y + 2, z - 3).setType(Material.WHITE_STAINED_GLASS_PANE);
            world.getBlockAt(x + 4, y + 2, z - 2).setType(Material.WHITE_STAINED_GLASS_PANE);
            world.getBlockAt(x + 4, y + 2, z - 1).setType(Material.WHITE_STAINED_GLASS_PANE);
            world.getBlockAt(x + 5, y + 2, z - 1).setType(Material.WHITE_STAINED_GLASS_PANE);
            world.getBlockAt(x - 1, y + 2, z - 5).setType(Material.WHITE_STAINED_GLASS_PANE);
            world.getBlockAt(x - 1, y + 2, z - 4).setType(Material.WHITE_STAINED_GLASS_PANE);
            world.getBlockAt(x - 2, y + 2, z - 4).setType(Material.WHITE_STAINED_GLASS_PANE);
            world.getBlockAt(x - 3, y + 2, z - 4).setType(Material.WHITE_STAINED_GLASS_PANE);
            world.getBlockAt(x - 3, y + 2, z - 3).setType(Material.COBBLED_DEEPSLATE_WALL);
            world.getBlockAt(x - 4, y + 2, z - 3).setType(Material.WHITE_STAINED_GLASS_PANE);
            world.getBlockAt(x - 4, y + 2, z - 2).setType(Material.WHITE_STAINED_GLASS_PANE);
            world.getBlockAt(x - 4, y + 2, z - 1).setType(Material.WHITE_STAINED_GLASS_PANE);
            world.getBlockAt(x - 5, y + 2, z - 1).setType(Material.WHITE_STAINED_GLASS_PANE);
            world.getBlockAt(x + 1, y + 2, z + 5).setType(Material.WHITE_STAINED_GLASS_PANE);
            world.getBlockAt(x + 1, y + 2, z + 4).setType(Material.WHITE_STAINED_GLASS_PANE);
            world.getBlockAt(x + 2, y + 2, z + 4).setType(Material.WHITE_STAINED_GLASS_PANE);
            world.getBlockAt(x + 3, y + 2, z + 4).setType(Material.WHITE_STAINED_GLASS_PANE);
            world.getBlockAt(x + 3, y + 2, z + 3).setType(Material.COBBLED_DEEPSLATE_WALL);
            world.getBlockAt(x + 4, y + 2, z + 3).setType(Material.WHITE_STAINED_GLASS_PANE);
            world.getBlockAt(x + 4, y + 2, z + 2).setType(Material.WHITE_STAINED_GLASS_PANE);
            world.getBlockAt(x + 4, y + 2, z + 1).setType(Material.WHITE_STAINED_GLASS_PANE);
            world.getBlockAt(x + 5, y + 2, z + 1).setType(Material.WHITE_STAINED_GLASS_PANE);
            world.getBlockAt(x - 1, y + 2, z + 5).setType(Material.WHITE_STAINED_GLASS_PANE);
            world.getBlockAt(x - 1, y + 2, z + 4).setType(Material.WHITE_STAINED_GLASS_PANE);
            world.getBlockAt(x - 2, y + 2, z + 4).setType(Material.WHITE_STAINED_GLASS_PANE);
            world.getBlockAt(x - 3, y + 2, z + 4).setType(Material.WHITE_STAINED_GLASS_PANE);
            world.getBlockAt(x - 3, y + 2, z + 3).setType(Material.COBBLED_DEEPSLATE_WALL);
            world.getBlockAt(x - 4, y + 2, z + 3).setType(Material.WHITE_STAINED_GLASS_PANE);
            world.getBlockAt(x - 4, y + 2, z + 2).setType(Material.WHITE_STAINED_GLASS_PANE);
            world.getBlockAt(x - 4, y + 2, z + 1).setType(Material.WHITE_STAINED_GLASS_PANE);
            world.getBlockAt(x - 5, y + 2, z + 1).setType(Material.WHITE_STAINED_GLASS_PANE);
            world.getBlockAt(x - 5, y + 2, z).setType(Material.WHITE_STAINED_GLASS_PANE, true);
            world.getBlockAt(x + 5, y + 2, z).setType(Material.WHITE_STAINED_GLASS_PANE, true);
            world.getBlockAt(x, y + 2, z + 5).setType(Material.WHITE_STAINED_GLASS_PANE, true);
            world.getBlockAt(x, y + 2, z - 5).setType(Material.WHITE_STAINED_GLASS_PANE, true);
            world.getBlockAt(x - 3, y + 3, z + 2).setType(Material.DARK_PRISMARINE_SLAB);// ROOF V
            world.getBlockAt(x - 3, y + 3, z + 1).setType(Material.DARK_PRISMARINE_SLAB);
            world.getBlockAt(x - 2, y + 3, z + 3).setType(Material.DARK_PRISMARINE_SLAB);
            world.getBlockAt(x - 1, y + 3, z + 3).setType(Material.DARK_PRISMARINE_SLAB);
            world.getBlockAt(x - 3, y + 3, z - 2).setType(Material.DARK_PRISMARINE_SLAB);
            world.getBlockAt(x - 3, y + 3, z - 1).setType(Material.DARK_PRISMARINE_SLAB);
            world.getBlockAt(x - 2, y + 3, z - 3).setType(Material.DARK_PRISMARINE_SLAB);
            world.getBlockAt(x - 1, y + 3, z - 3).setType(Material.DARK_PRISMARINE_SLAB);
            world.getBlockAt(x + 3, y + 3, z + 2).setType(Material.DARK_PRISMARINE_SLAB);
            world.getBlockAt(x + 3, y + 3, z + 1).setType(Material.DARK_PRISMARINE_SLAB);
            world.getBlockAt(x + 2, y + 3, z + 3).setType(Material.DARK_PRISMARINE_SLAB);
            world.getBlockAt(x + 1, y + 3, z + 3).setType(Material.DARK_PRISMARINE_SLAB);
            world.getBlockAt(x + 3, y + 3, z - 2).setType(Material.DARK_PRISMARINE_SLAB);
            world.getBlockAt(x + 3, y + 3, z - 1).setType(Material.DARK_PRISMARINE_SLAB);
            world.getBlockAt(x + 2, y + 3, z - 3).setType(Material.DARK_PRISMARINE_SLAB);
            world.getBlockAt(x + 1, y + 3, z - 3).setType(Material.DARK_PRISMARINE_SLAB);
            world.getBlockAt(x + 4, y + 3, z).setType(Material.DARK_PRISMARINE_SLAB);
            world.getBlockAt(x - 4, y + 3, z).setType(Material.DARK_PRISMARINE_SLAB);
            world.getBlockAt(x, y + 3, z + 4).setType(Material.DARK_PRISMARINE_SLAB);
            world.getBlockAt(x, y + 3, z - 4).setType(Material.DARK_PRISMARINE_SLAB);
        });
    }

    //fixme use a for loop or recusion to simplify
    public void removeSpawnPlatform() {
        Location loc = plugin.getLocation("SpawnPlatformCenter");
        int x = (int) loc.getX();
        int y = (int) loc.getY();
        int z = (int) loc.getZ();
        World world = loc.getWorld();

        Bukkit.getScheduler().runTask(plugin, () -> {
            world.getBlockAt(x, y, z).setType(AIR);
            world.getBlockAt(x + 1, y, z).setType(AIR);
            world.getBlockAt(x + 2, y, z).setType(AIR);
            world.getBlockAt(x + 3, y, z).setType(AIR);
            world.getBlockAt(x + 4, y, z).setType(AIR);
            world.getBlockAt(x + 5, y, z).setType(AIR);
            world.getBlockAt(x - 1, y, z).setType(AIR);
            world.getBlockAt(x - 2, y, z).setType(AIR);
            world.getBlockAt(x - 3, y, z).setType(AIR);
            world.getBlockAt(x - 4, y, z).setType(AIR);
            world.getBlockAt(x - 5, y, z).setType(AIR);
            world.getBlockAt(x, y, z + 1).setType(AIR);
            world.getBlockAt(x + 1, y, z + 1).setType(AIR);
            world.getBlockAt(x + 2, y, z + 1).setType(AIR);
            world.getBlockAt(x + 3, y, z + 1).setType(AIR);
            world.getBlockAt(x + 4, y, z + 1).setType(AIR);
            world.getBlockAt(x + 5, y, z + 1).setType(AIR);
            world.getBlockAt(x - 1, y, z + 1).setType(AIR);
            world.getBlockAt(x - 2, y, z + 1).setType(AIR);
            world.getBlockAt(x - 3, y, z + 1).setType(AIR);
            world.getBlockAt(x - 4, y, z + 1).setType(AIR);
            world.getBlockAt(x - 5, y, z + 1).setType(AIR);
            world.getBlockAt(x, y, z - 1).setType(AIR);
            world.getBlockAt(x + 1, y, z - 1).setType(AIR);
            world.getBlockAt(x + 2, y, z - 1).setType(AIR);
            world.getBlockAt(x + 3, y, z - 1).setType(AIR);
            world.getBlockAt(x + 4, y, z - 1).setType(AIR);
            world.getBlockAt(x + 5, y, z - 1).setType(AIR);
            world.getBlockAt(x - 1, y, z - 1).setType(AIR);
            world.getBlockAt(x - 2, y, z - 1).setType(AIR);
            world.getBlockAt(x - 3, y, z - 1).setType(AIR);
            world.getBlockAt(x - 4, y, z - 1).setType(AIR);
            world.getBlockAt(x - 5, y, z - 1).setType(AIR);
            world.getBlockAt(x, y, z - 2).setType(AIR);
            world.getBlockAt(x + 1, y, z - 2).setType(AIR);
            world.getBlockAt(x + 2, y, z - 2).setType(AIR);
            world.getBlockAt(x + 3, y, z - 2).setType(AIR);
            world.getBlockAt(x + 4, y, z - 2).setType(AIR);
            world.getBlockAt(x - 1, y, z - 2).setType(AIR);
            world.getBlockAt(x - 2, y, z - 2).setType(AIR);
            world.getBlockAt(x - 3, y, z - 2).setType(AIR);
            world.getBlockAt(x - 4, y, z - 2).setType(AIR);
            world.getBlockAt(x, y, z - 3).setType(AIR);
            world.getBlockAt(x + 1, y, z - 3).setType(AIR);
            world.getBlockAt(x + 2, y, z - 3).setType(AIR);
            world.getBlockAt(x + 3, y, z - 3).setType(AIR);
            world.getBlockAt(x + 4, y, z - 3).setType(AIR);
            world.getBlockAt(x - 1, y, z - 3).setType(AIR);
            world.getBlockAt(x - 2, y, z - 3).setType(AIR);
            world.getBlockAt(x - 3, y, z - 3).setType(AIR);
            world.getBlockAt(x - 4, y, z - 3).setType(AIR);
            world.getBlockAt(x, y, z + 2).setType(AIR);
            world.getBlockAt(x + 1, y, z + 2).setType(AIR);
            world.getBlockAt(x + 2, y, z + 2).setType(AIR);
            world.getBlockAt(x + 3, y, z + 2).setType(AIR);
            world.getBlockAt(x + 4, y, z + 2).setType(AIR);
            world.getBlockAt(x - 1, y, z + 2).setType(AIR);
            world.getBlockAt(x - 2, y, z + 2).setType(AIR);
            world.getBlockAt(x - 3, y, z + 2).setType(AIR);
            world.getBlockAt(x - 4, y, z + 2).setType(AIR);
            world.getBlockAt(x, y, z + 3).setType(AIR);
            world.getBlockAt(x + 1, y, z + 3).setType(AIR);
            world.getBlockAt(x + 2, y, z + 3).setType(AIR);
            world.getBlockAt(x + 3, y, z + 3).setType(AIR);
            world.getBlockAt(x + 4, y, z + 3).setType(AIR);
            world.getBlockAt(x - 1, y, z + 3).setType(AIR);
            world.getBlockAt(x - 2, y, z + 3).setType(AIR);
            world.getBlockAt(x - 3, y, z + 3).setType(AIR);
            world.getBlockAt(x - 4, y, z + 3).setType(AIR);
            world.getBlockAt(x, y, z - 4).setType(AIR);
            world.getBlockAt(x + 1, y, z - 4).setType(AIR);
            world.getBlockAt(x + 2, y, z - 4).setType(AIR);
            world.getBlockAt(x + 3, y, z - 4).setType(AIR);
            world.getBlockAt(x - 1, y, z - 4).setType(AIR);
            world.getBlockAt(x - 2, y, z - 4).setType(AIR);
            world.getBlockAt(x - 3, y, z - 4).setType(AIR);
            world.getBlockAt(x, y, z + 4).setType(AIR);
            world.getBlockAt(x + 1, y, z + 4).setType(AIR);
            world.getBlockAt(x + 2, y, z + 4).setType(AIR);
            world.getBlockAt(x + 3, y, z + 4).setType(AIR);
            world.getBlockAt(x - 1, y, z + 4).setType(AIR);
            world.getBlockAt(x - 2, y, z + 4).setType(AIR);
            world.getBlockAt(x - 3, y, z + 4).setType(AIR);
            world.getBlockAt(x, y, z - 5).setType(AIR);
            world.getBlockAt(x + 1, y, z - 5).setType(AIR);
            world.getBlockAt(x - 1, y, z - 5).setType(AIR);
            world.getBlockAt(x, y, z + 5).setType(AIR);
            world.getBlockAt(x + 1, y, z + 5).setType(AIR);
            world.getBlockAt(x - 1, y, z + 5).setType(AIR);
            world.getBlockAt(x + 1, y + 1, z - 5).setType(AIR); // WALLS V LAYER 1
            world.getBlockAt(x + 1, y + 1, z - 4).setType(AIR);
            world.getBlockAt(x + 2, y + 1, z - 4).setType(AIR);
            world.getBlockAt(x + 3, y + 1, z - 4).setType(AIR);
            world.getBlockAt(x + 3, y + 1, z - 3).setType(AIR);
            world.getBlockAt(x + 4, y + 1, z - 3).setType(AIR);
            world.getBlockAt(x + 4, y + 1, z - 2).setType(AIR);
            world.getBlockAt(x + 4, y + 1, z - 1).setType(AIR);
            world.getBlockAt(x + 5, y + 1, z - 1).setType(AIR);
            world.getBlockAt(x - 1, y + 1, z - 5).setType(AIR);
            world.getBlockAt(x - 1, y + 1, z - 4).setType(AIR);
            world.getBlockAt(x - 2, y + 1, z - 4).setType(AIR);
            world.getBlockAt(x - 3, y + 1, z - 4).setType(AIR);
            world.getBlockAt(x - 3, y + 1, z - 3).setType(AIR);
            world.getBlockAt(x - 4, y + 1, z - 3).setType(AIR);
            world.getBlockAt(x - 4, y + 1, z - 2).setType(AIR);
            world.getBlockAt(x - 4, y + 1, z - 1).setType(AIR);
            world.getBlockAt(x - 5, y + 1, z - 1).setType(AIR);
            world.getBlockAt(x + 1, y + 1, z + 5).setType(AIR);
            world.getBlockAt(x + 1, y + 1, z + 4).setType(AIR);
            world.getBlockAt(x + 2, y + 1, z + 4).setType(AIR);
            world.getBlockAt(x + 3, y + 1, z + 4).setType(AIR);
            world.getBlockAt(x + 3, y + 1, z + 3).setType(AIR);
            world.getBlockAt(x + 4, y + 1, z + 3).setType(AIR);
            world.getBlockAt(x + 4, y + 1, z + 2).setType(AIR);
            world.getBlockAt(x + 4, y + 1, z + 1).setType(AIR);
            world.getBlockAt(x + 5, y + 1, z + 1).setType(AIR);
            world.getBlockAt(x - 1, y + 1, z + 5).setType(AIR);
            world.getBlockAt(x - 1, y + 1, z + 4).setType(AIR);
            world.getBlockAt(x - 2, y + 1, z + 4).setType(AIR);
            world.getBlockAt(x - 3, y + 1, z + 4).setType(AIR);
            world.getBlockAt(x - 3, y + 1, z + 3).setType(AIR);
            world.getBlockAt(x - 4, y + 1, z + 3).setType(AIR);
            world.getBlockAt(x - 4, y + 1, z + 2).setType(AIR);
            world.getBlockAt(x - 4, y + 1, z + 1).setType(AIR);
            world.getBlockAt(x - 5, y + 1, z + 1).setType(AIR);
            world.getBlockAt(x - 5, y + 1, z).setType(AIR, true);
            world.getBlockAt(x + 5, y + 1, z).setType(AIR, true);
            world.getBlockAt(x, y + 1, z + 5).setType(AIR, true);
            world.getBlockAt(x, y + 1, z - 5).setType(AIR, true);
            world.getBlockAt(x + 1, y + 2, z - 5).setType(AIR); // WALLS LAYER 2 V
            world.getBlockAt(x + 1, y + 2, z - 4).setType(AIR);
            world.getBlockAt(x + 2, y + 2, z - 4).setType(AIR);
            world.getBlockAt(x + 3, y + 2, z - 4).setType(AIR);
            world.getBlockAt(x + 3, y + 2, z - 3).setType(AIR);
            world.getBlockAt(x + 4, y + 2, z - 3).setType(AIR);
            world.getBlockAt(x + 4, y + 2, z - 2).setType(AIR);
            world.getBlockAt(x + 4, y + 2, z - 1).setType(AIR);
            world.getBlockAt(x + 5, y + 2, z - 1).setType(AIR);
            world.getBlockAt(x - 1, y + 2, z - 5).setType(AIR);
            world.getBlockAt(x - 1, y + 2, z - 4).setType(AIR);
            world.getBlockAt(x - 2, y + 2, z - 4).setType(AIR);
            world.getBlockAt(x - 3, y + 2, z - 4).setType(AIR);
            world.getBlockAt(x - 3, y + 2, z - 3).setType(AIR);
            world.getBlockAt(x - 4, y + 2, z - 3).setType(AIR);
            world.getBlockAt(x - 4, y + 2, z - 2).setType(AIR);
            world.getBlockAt(x - 4, y + 2, z - 1).setType(AIR);
            world.getBlockAt(x - 5, y + 2, z - 1).setType(AIR);
            world.getBlockAt(x + 1, y + 2, z + 5).setType(AIR);
            world.getBlockAt(x + 1, y + 2, z + 4).setType(AIR);
            world.getBlockAt(x + 2, y + 2, z + 4).setType(AIR);
            world.getBlockAt(x + 3, y + 2, z + 4).setType(AIR);
            world.getBlockAt(x + 3, y + 2, z + 3).setType(AIR);
            world.getBlockAt(x + 4, y + 2, z + 3).setType(AIR);
            world.getBlockAt(x + 4, y + 2, z + 2).setType(AIR);
            world.getBlockAt(x + 4, y + 2, z + 1).setType(AIR);
            world.getBlockAt(x + 5, y + 2, z + 1).setType(AIR);
            world.getBlockAt(x - 1, y + 2, z + 5).setType(AIR);
            world.getBlockAt(x - 1, y + 2, z + 4).setType(AIR);
            world.getBlockAt(x - 2, y + 2, z + 4).setType(AIR);
            world.getBlockAt(x - 3, y + 2, z + 4).setType(AIR);
            world.getBlockAt(x - 3, y + 2, z + 3).setType(AIR);
            world.getBlockAt(x - 4, y + 2, z + 3).setType(AIR);
            world.getBlockAt(x - 4, y + 2, z + 2).setType(AIR);
            world.getBlockAt(x - 4, y + 2, z + 1).setType(AIR);
            world.getBlockAt(x - 5, y + 2, z + 1).setType(AIR);
            world.getBlockAt(x - 5, y + 2, z).setType(AIR, true);
            world.getBlockAt(x + 5, y + 2, z).setType(AIR, true);
            world.getBlockAt(x, y + 2, z + 5).setType(AIR, true);
            world.getBlockAt(x, y + 2, z - 5).setType(AIR, true);
            world.getBlockAt(x - 3, y + 3, z + 2).setType(AIR);// ROOF V
            world.getBlockAt(x - 3, y + 3, z + 1).setType(AIR);
            world.getBlockAt(x - 2, y + 3, z + 3).setType(AIR);
            world.getBlockAt(x - 1, y + 3, z + 3).setType(AIR);
            world.getBlockAt(x - 3, y + 3, z - 2).setType(AIR);
            world.getBlockAt(x - 3, y + 3, z - 1).setType(AIR);
            world.getBlockAt(x - 2, y + 3, z - 3).setType(AIR);
            world.getBlockAt(x - 1, y + 3, z - 3).setType(AIR);
            world.getBlockAt(x + 3, y + 3, z + 2).setType(AIR);
            world.getBlockAt(x + 3, y + 3, z + 1).setType(AIR);
            world.getBlockAt(x + 2, y + 3, z + 3).setType(AIR);
            world.getBlockAt(x + 1, y + 3, z + 3).setType(AIR);
            world.getBlockAt(x + 3, y + 3, z - 2).setType(AIR);
            world.getBlockAt(x + 3, y + 3, z - 1).setType(AIR);
            world.getBlockAt(x + 2, y + 3, z - 3).setType(AIR);
            world.getBlockAt(x + 1, y + 3, z - 3).setType(AIR);
            world.getBlockAt(x + 4, y + 3, z).setType(AIR);
            world.getBlockAt(x - 4, y + 3, z).setType(AIR);
            world.getBlockAt(x, y + 3, z + 4).setType(AIR);
            world.getBlockAt(x, y + 3, z - 4).setType(AIR);
        });
    }

    public void worldSetup() {
        World world = Bukkit.getWorld(plugin.getConfig().getString("MapName"));
        world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        world.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
        world.setGameRule(GameRule.DO_FIRE_TICK, false);
        world.setTime(6000);}

    public CompletableFuture<SlimeWorld> fetchWorld() {
        this.plugin.getLogger().info("Fetching World");

        SlimePlugin plugin = (SlimePlugin) Bukkit.getPluginManager().getPlugin("SlimeWorldManager");

        SlimeLoader sqlLoader = plugin.getLoader("mysql");
        String databaseMapName = this.plugin.getConfig().getString("DatabaseMapName");

        // create a new and empty property map
        SlimePropertyMap properties = new SlimePropertyMap();

        properties.setValue(SlimeProperties.DIFFICULTY, "normal");
        properties.setValue(SlimeProperties.SPAWN_X, this.plugin.getConfig().getInt("WorldSpawn.x"));
        properties.setValue(SlimeProperties.SPAWN_Y, this.plugin.getConfig().getInt("WorldSpawn.y"));
        properties.setValue(SlimeProperties.SPAWN_Z, this.plugin.getConfig().getInt("WorldSpawn.z"));

        CompletableFuture<SlimeWorld> callback = new CompletableFuture<>();

        callback.completeAsync(() -> {
            try {
                world = plugin.loadWorld(sqlLoader, databaseMapName, true, properties);
            } catch (UnknownWorldException | IOException | CorruptedWorldException | NewerFormatException |
                     WorldLockedException e) {
                this.plugin.getLogger().log(Level.SEVERE, "An error occoured whilst fetching map '" + databaseMapName + "' from the database!", e);
            }
            return world;
        });
        return callback;
    }

    public void pastePopupTower(Location center, Material wool) {
        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(wool, center.clone().add(1, 0, 1)), 0);
        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(wool, center.clone().add(1, 0, -1)), 1);
        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(wool, center.clone().add(-1, 0, 1)), 2);
        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(wool, center.clone().add(-1, 0, -1)), 3);
        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(SCAFFOLDING, center.clone().add(0, 0, 0)), 4);


        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(wool, center.clone().add(1, 1, 1)), 5);
        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(wool, center.clone().add(1, 1, -1)), 6);
        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(wool, center.clone().add(-1, 1, 1)), 7);
        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(wool, center.clone().add(-1, 1, -1)), 8);
        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(SCAFFOLDING, center.clone().add(0, 1, 0)), 9);

        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(wool, center.clone().add(0, 2, 1)), 10);
        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(wool, center.clone().add(0, 2, -1)), 11);
        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(wool, center.clone().add(-1, 2, 0)), 12);
        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(wool, center.clone().add(1, 2, 0)), 13);
        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(SCAFFOLDING, center.clone().add(0, 2, 0)), 14);

        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(wool, center.clone().add(0, 3, 1)), 15);
        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(wool, center.clone().add(0, 3, -1)), 16);
        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(wool, center.clone().add(-1, 3, 0)), 17);
        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(wool, center.clone().add(1, 3, 0)), 18);
        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(SCAFFOLDING, center.clone().add(0, 3, 0)), 19);

        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(wool, center.clone().add(0, 3, 1)), 20);
        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(wool, center.clone().add(0, 3, -1)), 21);
        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(wool, center.clone().add(-1, 3, 0)), 22);
        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(wool, center.clone().add(1, 3, 0)), 23);
        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(SCAFFOLDING, center.clone().add(0, 3, 0)), 24);

        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(wool, center.clone().add(0, 5, 1)), 25);
        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(SCAFFOLDING, center.clone().add(0, 5, 0)), 26);
        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(wool, center.clone().add(0, 5, -1)), 27);
        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(wool, center.clone().add(1, 5, 1)), 28);
        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(wool, center.clone().add(1, 5, 0)), 29);
        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(wool, center.clone().add(1, 5, -1)), 30);
        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(wool, center.clone().add(-1, 5, 1)), 31);
        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(wool, center.clone().add(-1, 5, 0)), 32);
        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(wool, center.clone().add(-1, 5, -1)), 33);

        // 6th layer
        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(wool, center.clone().add(2, 6, 2)), 34);
        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(wool, center.clone().add(2, 6, 1)), 35);
        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(wool, center.clone().add(2, 6, 0)), 36);
        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(wool,center.clone().add(2, 6, -1) ), 37);
        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(wool, center.clone().add(2, 6, -2)), 38);
        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(wool, center.clone().add(1, 6, 2)), 39);
        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(wool, center.clone().add(1, 6, 1)), 40);
        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(wool, center.clone().add(1, 6, 0)), 41);
        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(wool, center.clone().add(1, 6, -1)), 42);
        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(wool, center.clone().add(1, 6, -2)), 43);
        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(wool, center.clone().add(0, 6, 2)), 44);
        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(wool, center.clone().add(0, 6, 1)), 45);
        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(SCAFFOLDING, center.clone().add(0, 6, 0)), 46);
        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(wool, center.clone().add(0, 6, -1)), 47);
        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(wool, center.clone().add(0, 6, -2)), 48);
        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(wool, center.clone().add(-1, 6, 2)), 49);
        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(wool, center.clone().add(-1, 6, 1)), 50);
        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(wool, center.clone().add(-1, 6, 0)), 51);
        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(wool, center.clone().add(-1, 6, -1)), 52);
        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(wool, center.clone().add(-1, 6, -2)), 53);
        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(wool, center.clone().add(-2, 6, 2)), 54);
        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(wool, center.clone().add(-2, 6, 1)), 55);
        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(wool, center.clone().add(-2, 6, 0)), 56);
        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(wool, center.clone().add(-2, 6, -1)), 57);
        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(wool, center.clone().add(-2, 6, -2)), 58);

        // Top row
        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(wool, center.clone().add(0, 7, 2)), 59);
        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(wool, center.clone().add(0, 7, -2)), 61);
        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(wool, center.clone().add(2, 7, 2)), 62);
        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(wool, center.clone().add(2, 7, 0)), 63);
        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(wool, center.clone().add(2, 7, -2)), 64);
        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(wool, center.clone().add(-2, 7, 2)), 65);
        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(wool, center.clone().add(-2, 7, 0)), 66);
        Bukkit.getScheduler().runTaskLater(plugin, () -> setBlock(wool, center.clone().add(-2, 7, -2)), 67);
    }

    // boolean vararg because lazy
    private void setBlock(Material material, Location loc, boolean... breakable) {
        Block b = loc.getBlock();
        if(b.getType() == AIR) {
            b.getWorld().playSound(loc, Sound.ENTITY_PLAYER_BURP, .5F, .3F);
            b.setType(material, true);
        }
        if(breakable.length < 1) {
            b.setMetadata("blockdata", new FixedMetadataValue(plugin, true));
        }
    }
}