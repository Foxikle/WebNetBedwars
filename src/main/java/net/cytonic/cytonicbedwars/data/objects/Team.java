package net.cytonic.cytonicbedwars.data.objects;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;

public record Team(String displayName, String prefix, ChatColor color, Material bedType, Location spawnLocation, Location generatorLocation,
                   Location itemShopLocation, Location teamShopLocation, Location chestLocation, Material woolType, Material glassType, Material terracottaType) {
}
