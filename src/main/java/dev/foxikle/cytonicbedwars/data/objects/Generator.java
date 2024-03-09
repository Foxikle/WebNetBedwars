package dev.foxikle.cytonicbedwars.data.objects;

import dev.foxikle.cytonicbedwars.CytonicBedWars;
import dev.foxikle.cytonicbedwars.data.enums.GeneratorType;
import dev.foxikle.cytonicbedwars.data.types.GeneratorItems;
import dev.foxikle.cytonicbedwars.runnables.GeneratorVisualRunnable;
import dev.foxikle.cytonicbedwars.utils.Items;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Item;
import org.bukkit.entity.TextDisplay;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import java.util.*;

public class Generator {

    public static NamespacedKey SPLIT_KEY = new NamespacedKey("cytonicbedwars", "split_key");
    private final GeneratorType type;
    private final GeneratorItems<String, Integer, Integer> items;
    private final Location spawnLoc;
    private final boolean hasVisual;
    private final boolean splitable;
    private ArmorStand visual;
    private final Map<String, List<Item>> spawnedItems = new HashMap<>();
    private GeneratorVisualRunnable visualRunnable;
    private final Map<String, BukkitRunnable> runnables = new HashMap<>();

    private TextDisplay countDown;
    private BukkitRunnable coundownRunnable;
    private String countDownFormat;
    private int toNext = 0; // in ticks
    private final int countDownDuration; // in ticks

    //todo: Gensplit by checking the players nearby and adding items to their inventory too

    public Generator(GeneratorType type, GeneratorItems<String, Integer, Integer> items, Location spawnLoc, boolean hasVisual, boolean splitable, int countDownDuration) {
        this.type = type;
        this.items = items;
        this.spawnLoc = spawnLoc;
        this.hasVisual = hasVisual;
        this.splitable = splitable;
        this.countDownDuration = countDownDuration;
        items.getKeyset().forEach(s -> {
            spawnedItems.put(s, new ArrayList<>());
            runnables.put(s, new BukkitRunnable() {

                private final int maxItems = items.getSecondValue(s);

                @Override
                public void run() {
                    new ArrayList<>(spawnedItems.get(s)).forEach(item -> {
                        if(item != null && item.isDead()) {
                            spawnedItems.get(s).remove(item);
                        }
                    });
                    if(spawnedItems.get(s).size() >= maxItems) return;
                    Item i = spawnLoc.getWorld().dropItem(spawnLoc.clone().add(0, .5,0), Items.get(s));
                    i.setVelocity(new Vector(0, 0, 0));
                    i.setUnlimitedLifetime(true);
                    i.setWillAge(false);
                    i.getPersistentDataContainer().set(Items.NAMESPACE, PersistentDataType.STRING, UUID.randomUUID().toString());
                    if(splitable)
                        i.getPersistentDataContainer().set(SPLIT_KEY, PersistentDataType.BOOLEAN, true);
                    spawnedItems.get(s).add(i);
                }
            });
        });
    }

    public void setVisual(ArmorStand visual) {
        this.visual = visual;
    }

    public void start() {
        if(countDown != null) {
            coundownRunnable = new BukkitRunnable() {
                @Override
                public void run() {
                    toNext++;
                    float toNextF = ((countDownDuration - toNext) / 20.0F);
                    String text = String.format(countDownFormat, toNextF);
                    countDown.setText(text);
                    if(toNext == countDownDuration)
                        toNext = 0;
                }
            };
            coundownRunnable.runTaskTimer(CytonicBedWars.INSTANCE, 0, 2);
        }
        if(hasVisual) {
            if(visual == null) throw new NullPointerException("The visual cannot be null!");
            visualRunnable = new GeneratorVisualRunnable(visual, CytonicBedWars.INSTANCE);
            visualRunnable.start();
        }
        runnables.forEach((s, bukkitRunnable) -> bukkitRunnable.runTaskTimer(CytonicBedWars.INSTANCE, 0, items.getFirstValue(s)));
    }

    public void stop() {
        if(hasVisual) {
            visualRunnable.cancel();
            visual.remove();
        }
        runnables.values().forEach(BukkitRunnable::cancel);
    }

    public void setCountDown(TextDisplay display, String format) {
        this.countDown = display;
        this.countDownFormat = format;
    }

    public Location getSpawnLoc() {
        return spawnLoc;
    }
}
