package dev.foxikle.cytonicbedwars.listeners;

import dev.foxikle.cytonicbedwars.CytonicBedWars;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitScheduler;

public class BucketEmptyListener implements Listener {

    private final CytonicBedWars plugin;

    public BucketEmptyListener(CytonicBedWars plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBucketEmpty(PlayerBucketEmptyEvent event) {
        BukkitScheduler scheduler = Bukkit.getScheduler();
        scheduler.runTaskLater(plugin, () -> event.getPlayer().getInventory().removeItem(new ItemStack(Material.BUCKET)), 1 );}}