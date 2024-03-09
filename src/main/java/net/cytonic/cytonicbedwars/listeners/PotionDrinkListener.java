package net.cytonic.cytonicbedwars.listeners;

import net.cytonic.cytonicbedwars.CytonicBedWars;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffectType;

public class PotionDrinkListener implements Listener {

    private final CytonicBedWars plugin;

    public PotionDrinkListener(CytonicBedWars plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPotionDrink(PlayerItemConsumeEvent e) {
        if(e.getItem().getType() == Material.POTION) {
            ItemStack item = e.getItem();
            PotionMeta meta = (PotionMeta) item.getItemMeta();
            if(meta.getCustomEffects().get(0).getType() == PotionEffectType.INVISIBILITY) {
                Player player = e.getPlayer();
                ItemStack[] armor = player.getInventory().getArmorContents();

                Bukkit.getOnlinePlayers().forEach(p -> {
                    player.getInventory().setChestplate(new ItemStack(Material.AIR));
                    player.getInventory().setLeggings(new ItemStack(Material.AIR));
                    player.getInventory().setBoots(new ItemStack(Material.AIR));
                });
                Bukkit.getScheduler().runTaskLater(plugin, () -> Bukkit.getOnlinePlayers().forEach(p -> player.getInventory().setArmorContents(armor)), meta.getCustomEffects().get(0).getDuration());
            }
            Bukkit.getScheduler().runTaskLater(plugin, () -> e.getPlayer().getInventory().getItem(e.getHand()).setAmount(0), 1);

        }
    }
}
