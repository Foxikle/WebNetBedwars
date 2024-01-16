package dev.foxikle.webnetbedwars.listeners;

import dev.foxikle.webnetbedwars.WebNetBedWars;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.metadata.FixedMetadataValue;

import static org.bukkit.Material.AIR;

public class BlockPlaceListener implements Listener {
    private final WebNetBedWars plugin;

    public BlockPlaceListener(WebNetBedWars plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e){
        if(e.getBlockPlaced().getType() == Material.SPONGE) {
            Bukkit.getScheduler().runTaskLater(plugin,  () -> e.getBlockPlaced().setType(AIR), 1);
        }
        if(!plugin.getGameManager().getWorldManager().isValidPlacementLocation(e.getBlockPlaced().getLocation(), 5.0)) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(ChatColor.RED + "You cannot place a block here!");
        }
        if(e.getBlockReplacedState().getType() != AIR) {
            e.setCancelled(true);
        }
        e.getBlock().setMetadata("blockdata", new FixedMetadataValue(plugin, true));
    }
}
