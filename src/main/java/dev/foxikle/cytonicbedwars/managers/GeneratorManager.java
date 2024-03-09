package dev.foxikle.cytonicbedwars.managers;

import dev.foxikle.cytonicbedwars.CytonicBedWars;
import dev.foxikle.cytonicbedwars.data.enums.GeneratorType;
import dev.foxikle.cytonicbedwars.data.objects.Generator;
import dev.foxikle.cytonicbedwars.data.objects.Team;
import dev.foxikle.cytonicbedwars.data.types.GeneratorItems;
import dev.foxikle.cytonicbedwars.data.types.Pair;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Display;
import org.bukkit.entity.TextDisplay;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class GeneratorManager {
    private final CytonicBedWars plugin;
    private final GameManager gameManager;

    private List<Generator> diamondGenerators = new ArrayList<>();
    private List<Generator> emeraldGenerators = new ArrayList<>();


    public GeneratorManager(CytonicBedWars plugin, GameManager gameManager) {
        this.plugin = plugin;
        this.gameManager = gameManager;
    }


    public void registerTeamGenerators() {
        for (Team t : plugin.getGameManager().getTeamlist()) {
            Location loc = t.generatorLocation();
            Generator ironGenerator = new Generator(
                    GeneratorType.IRON,
                    new GeneratorItems<>("IRON", new Pair<>(plugin.getConfig().getInt("GeneratorsWaitTime.iron"), plugin.getConfig().getInt("GeneratorsItemLimit.iron"))),
                    loc,
                    false,
                    true,
                    plugin.getConfig().getInt("GeneratorsWaitTime.iron"));

            Generator goldGenerator = new Generator(
                    GeneratorType.GOLD,
                    new GeneratorItems<>("GOLD", new Pair<>(plugin.getConfig().getInt("GeneratorsWaitTime.gold"), plugin.getConfig().getInt("GeneratorsItemLimit.gold"))),
                    loc,
                    false,
                    true,
                    plugin.getConfig().getInt("GeneratorsWaitTime.gold"));

            ironGenerator.start();
            goldGenerator.start();
        }
    }

    public void registerDiamondGenerators() {
        ConfigurationSection diaGenSection = plugin.getConfig().getConfigurationSection("Generators.Diamond");
        for (String key : diaGenSection.getKeys(false)) {
            Location loc = plugin.getLocation("Generators.Diamond." + key);
            Generator generator = new Generator(
                    GeneratorType.DIAMOND,
                    new GeneratorItems<>("DIAMOND", new Pair<>(plugin.getConfig().getInt("GeneratorsWaitTime.diamond"), plugin.getConfig().getInt("GeneratorsItemLimit.diamond"))),
                    loc,
                    true,
                    false,
                    plugin.getConfig().getInt("GeneratorsWaitTime.diamond")

            );
            ArmorStand visual = loc.getWorld().spawn(loc.clone().add(0, 2, 0), ArmorStand.class);
            visual.setInvisible(true);
            visual.setMarker(true);
            visual.getEquipment().setHelmet(new ItemStack(Material.DIAMOND_BLOCK));
            visual.setInvulnerable(true);
            visual.setGravity(false);
            generator.setVisual(visual);

            TextDisplay name = loc.getWorld().spawn(loc.clone().add(0, 2.5, 0), TextDisplay.class);
            name.setText(ChatColor.AQUA + "" + ChatColor.BOLD + "Diamond Generator");
            name.setBillboard(Display.Billboard.CENTER);

            String nextSpawnStr = ChatColor.YELLOW + "Spawns in " + ChatColor.RED + "%.2f" + ChatColor.YELLOW + " seconds";
            TextDisplay nextSpawn = loc.getWorld().spawn(loc.clone().add(0, 2.2, 0), TextDisplay.class);
            nextSpawn.setText(nextSpawnStr);
            nextSpawn.setBillboard(Display.Billboard.CENTER);
            generator.setCountDown(nextSpawn, nextSpawnStr);
            generator.start();
            diamondGenerators.add(generator);
        }
    }

    public void registerEmeraldGenerators() {
        ConfigurationSection emGenSection = plugin.getConfig().getConfigurationSection("Generators.Emerald");
        for (String key : emGenSection.getKeys(false)) {
            Location loc = plugin.getLocation("Generators.Emerald." + key);
            Generator generator = new Generator(
                    GeneratorType.EMERALD,
                    new GeneratorItems<>("EMERALD", new Pair<>(plugin.getConfig().getInt("GeneratorsWaitTime.emerald"), plugin.getConfig().getInt("GeneratorsItemLimit.emerald"))),
                    loc,
                    true,
                    false,
                    plugin.getConfig().getInt("GeneratorsWaitTime.emerald")

            );
            ArmorStand visual = loc.getWorld().spawn(loc.clone().add(0, 2, 0), ArmorStand.class);
            visual.setInvisible(true);
            visual.setMarker(true);
            visual.getEquipment().setHelmet(new ItemStack(Material.EMERALD_BLOCK));
            visual.setInvulnerable(true);
            visual.setGravity(false);
            generator.setVisual(visual);

            TextDisplay name = loc.getWorld().spawn(loc.clone().add(0, 2.5, 0), TextDisplay.class);
            name.setText(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Emerald Generator");
            name.setBillboard(Display.Billboard.CENTER);

            String nextSpawnStr = ChatColor.YELLOW + "Spawns in " + ChatColor.RED + "%.2f" + ChatColor.YELLOW + " seconds";
            TextDisplay nextSpawn = loc.getWorld().spawn(loc.clone().add(0, 2.2, 0), TextDisplay.class);
            nextSpawn.setText(nextSpawnStr);
            nextSpawn.setBillboard(Display.Billboard.CENTER);
            generator.setCountDown(nextSpawn, nextSpawnStr);
            generator.start();
            emeraldGenerators.add(generator);
        }
    }

    public List<Generator> getDiamondGenerators() {
        return diamondGenerators;
    }

    public List<Generator> getEmeraldGenerators() {
        return emeraldGenerators;
    }
}

