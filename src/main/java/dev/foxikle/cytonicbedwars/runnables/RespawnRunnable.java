package dev.foxikle.cytonicbedwars.runnables;

import dev.foxikle.cytonicbedwars.CytonicBedWars;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class RespawnRunnable extends BukkitRunnable {
    private final CytonicBedWars plugin;
    private int timeLeft;
    private Player player;

    public RespawnRunnable(CytonicBedWars plugin, int timeLeft, Player player) {
        this.plugin = plugin;
        this.timeLeft = timeLeft;
        this.player = player;
    }


    @Override
    public void run() {
        timeLeft--;
        if(timeLeft <= 0){
            cancel();
            plugin.getGameManager().respawnPlayer(player);
            return;
        }

        switch (timeLeft) {
            case 1, 2, 3 -> {
                player.sendTitle(ChatColor.YELLOW + "Respawning in " + timeLeft, "", 0, 21, 0);
                player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK,.8f, 1f);
            }
        }
    }
}
