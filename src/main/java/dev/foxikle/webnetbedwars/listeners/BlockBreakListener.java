package dev.foxikle.webnetbedwars.listeners;

import dev.foxikle.webnetbedwars.WebNetBedWars;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.metadata.MetadataValue;

public class BlockBreakListener implements Listener {
    private final WebNetBedWars plugin;

    public BlockBreakListener(WebNetBedWars plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if(e.getPlayer().getGameMode() == GameMode.CREATIVE) return;
        if(e.getBlock().getType() == Material.FIRE) return;
        if(plugin.getGameManager().spectators.contains(e.getPlayer().getUniqueId())){
            e.getPlayer().sendMessage(ChatColor.RED + "You cannot do this as a spectator!");
            e.setCancelled(true);
            return;
        }
        if(e.getBlock().getType().name().contains("BED")){
            e.setDropItems(false);
            plugin.getGameManager().getTeamlist().forEach(team -> {
                if(e.getBlock().getType() == team.bedType()) {
                    if(plugin.getGameManager().getPlayerTeam(e.getPlayer().getUniqueId()) == team) {
                        e.setCancelled(true);
                        e.getPlayer().sendMessage(ChatColor.RED + "You cannot break your own bed!");
                        return;
                    }

                    if(plugin.getGameManager().getBeds().get(team)) // prevent it from being annoying
                        plugin.getGameManager().breakBed(e.getPlayer(), team);
                }
            });
            return;
        }
        for (MetadataValue mv : e.getBlock().getMetadata("blockdata")) {
            if(mv.asBoolean() && mv.getOwningPlugin() instanceof WebNetBedWars){
                return;
            }
        }
        e.getPlayer().sendMessage(Component.text("You can only break blocks placed by players!", NamedTextColor.RED));
        e.setCancelled(true);
    }
}
