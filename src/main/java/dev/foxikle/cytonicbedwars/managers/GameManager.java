package dev.foxikle.cytonicbedwars.managers;

import com.infernalsuite.aswm.api.SlimePlugin;
import com.infernalsuite.aswm.api.exceptions.UnknownWorldException;
import com.infernalsuite.aswm.api.exceptions.WorldLockedException;
import dev.foxikle.customnpcs.actions.Action;
import dev.foxikle.customnpcs.actions.ActionType;
import dev.foxikle.customnpcs.actions.conditions.Conditional;
import dev.foxikle.customnpcs.api.NPC;
import dev.foxikle.customnpcs.data.Settings;
import dev.foxikle.cytonicbedwars.CytonicBedWars;
import dev.foxikle.cytonicbedwars.data.enums.ArmorLevel;
import dev.foxikle.cytonicbedwars.data.enums.AxeLevel;
import dev.foxikle.cytonicbedwars.data.enums.GameState;
import dev.foxikle.cytonicbedwars.data.enums.PickaxeLevel;
import dev.foxikle.cytonicbedwars.data.objects.Team;
import dev.foxikle.cytonicbedwars.runnables.RespawnRunnable;
import dev.foxikle.cytonicbedwars.utils.Items;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wither;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;

public class GameManager {
    private final CytonicBedWars plugin;
    private final List<Team> teamlist = new ArrayList<>();

    private Map<Team, List<UUID>> playerTeams = new HashMap<>();
    private List<UUID> alivePlayers = new ArrayList<>();
    private final Map<Team, Boolean> beds = new HashMap<>();
    private final Map<Team, org.bukkit.scoreboard.Team> mcTeams = new HashMap<>();
    private final List<NPC> npcs = new ArrayList<>();
    public List<UUID> spectators = new ArrayList<>();

    public Map<UUID, ArmorLevel> armorLevels = new HashMap<>();
    public Map<UUID, AxeLevel> axes = new HashMap<>();
    public Map<UUID, PickaxeLevel> pickaxes = new HashMap<>();
    public Map<UUID, Boolean> shears = new HashMap<>();


    private GameState beforeFrozen;
    private GameState gameState;
    private static final String NPC_SKIN_QUESTION_MARK_VALUE = "ewogICJ0aW1lc3RhbXAiIDogMTY5ODcxMzk3ODQzNCwKICAicHJvZmlsZUlkIiA6ICI3NzUwYzFhNTM5M2Q0ZWQ0Yjc2NmQ4ZGUwOWY4MjU0NiIsCiAgInByb2ZpbGVOYW1lIiA6ICJSZWVkcmVsIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2ZkMGRkNTE5NDMwOWY5YTVhMTJiMWUyOTZmMTE0ZTM1MGI2NmU0NzliZDAzMDlkM2JhOTlmMjdmZWMxZTZkNzAiCiAgICB9CiAgfQp9";
    private static final String NPC_SKIN_QUESTION_MARK_SIGNATURE = "J50Ec/p6guuZl49Psrgp3GF8t7tbexyOwldiPLYGNJWoCEPghyTFnh3ZXNuh6N5U8t1RzoJE1qcFMWNpLjYZ5MLBUvBdQHjZ3PzUG3wd9unAHJmX5D7bZHAfErx0z6pWw0EwvJ3xNsaWYzd/fmtR5nkVbR8GuDClce2FmJJ3icjtjg9BJ2vNEd7GCb+VHYmnqKtwfQAFZ0DlfMAoe0QL1zTZSs5BwGUrh4FuamAEPjv81t19briIPeleMOfcF3/k6I6XTsGU/w2Anv7cvZzT2aq4kO10lSFNqkjOlrmo0kGqKWvojOI1YwuPS3eTI9PH9+ZWMwxiMUfL5tlnoChJ3Red1fXHZC3Gq8fVqiZCxHE/2pU0MDz4f0Cdpm9jZ92r5qmi6aOcZ8ksxwnktYdW+RzbZugwunCrOTEbugxjVjeocl/yssZXkXucnmd4dcSBLE3GsHOJvUVAwWii5KSe8CEkwvdxnzPWZXrMQT9kE8kgP0cLlgq7uOv3cUEH+WGcgENgwWi9g89hVuTHlnVJvRFhdISR4QLME00+dCZS9eriaToawZy4poVYT81ARrYodBbDIa1fB38Vq/oHKI+vHFqIk4iznSUhyjCNHRfGmtoH8FX0+eoQMAFpj34R29Bc6Yb5Cn7LwDB5TbM+AgajHGrmNFACE/lIk0SmKw3s0F0=";
    private static final String NPC_SKIN_VALUE = "ewogICJ0aW1lc3RhbXAiIDogMTY2MjQ2NzA5Njc1NywKICAicHJvZmlsZUlkIiA6ICJmNTgyNGRmNGIwMTU0MDA4OGRhMzUyYTQxODU1MDQ0NCIsCiAgInByb2ZpbGVOYW1lIiA6ICJGb3hHYW1lcjUzOTIiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTI5YWI4YmRiMjI4ZTQ3MjZiNzQ1MzZhY2EwNTlhMTZjYWNjNzBjNThlNGEyZGFhMTQzZDIxOWYzNzRhOGI0YSIKICAgIH0KICB9Cn0=";
    private static final String NPC_SKIN_SIGNATURE = "yKToy4cFqIM5A3JWqXkeOaWjOd8MjAm+ECb1ga8tlBZzGvsLVHVaatVcvdYvLqxeUcWrrGLE8F4cqdVl+XyqUyILjmqw8elFwKCS28fIryuvAMaH28SRjDUsAVtTyt6xHSh2yx30IvuN+OmatcTTYQO0AmTzG6VlrOd4COzfrcOEteZb6yqh43hfxpawlavdQw7LQ3ecFXe5JPINNzXPEbbcAYeV9Gh9j6ej9n2P8KsMcTfEjb+UWh82fLegPt3pBQWdXUJVyh1SualBqVaX8hqk38KbfwtC7A9FWvycY7OacjXOyTeWEqZnGUNwc1YgXnS5EidzG/xXNJz2pgzOBlwtAv80jAXnVQcyJkuhSijSehKvMuEEd1gcY7O3itAdSb0636zjAhcKsqskzUhaRNK8QNpbIowBDA2t4EXaFkGSpBSRrOVthox6MhxDLC+ZKADNuiGEtVgpw6vY5gfulovaIX7wOWGLrxGrA6JsA9Fq7XuwHq8d8k8kI6XNRSxdKoKgHhdmlzjPax/GelXt6a9VkRoagtY8EmnliWyOorIMazjdDKq+QmddHH3sDAeahLtXoCf64Jus8bqqyNL4B0E3HwlKjQ2XZw1v/G9c70uJscaoUgpATwvHg2+dH0uxs2MSkN/GZM3GWbmyerFz+AapDjsZhBhylJ570jcbuS4=";

    private final StatsManager statsManager;
    private final ScoreboardManager scoreboardManager;
    private final WorldManager worldManager;
    private final MenuManager menuManager;
    private final PlayerInventoryManager playerInventoryManager;
    private final GeneratorManager generatorManager;

    private int secondsToNext = 0;
    private GameState nextState = GameState.PLAY;

    public boolean STARTED = false;
    public boolean ENDED = false;
    private BukkitRunnable timer;

    public GameManager(CytonicBedWars plugin) {
        this.plugin = plugin;
        statsManager = new StatsManager();
        scoreboardManager = new ScoreboardManager(this, plugin);
        worldManager = new WorldManager(plugin, this);
        menuManager = new MenuManager(plugin);
        playerInventoryManager = new PlayerInventoryManager(plugin);
        generatorManager = new GeneratorManager(plugin, this);
    }

    public void setup() {
        SlimePlugin plugin = (SlimePlugin) Bukkit.getPluginManager().getPlugin("SlimeWorldManager");
        worldManager.fetchWorld().whenComplete((slimeWorld, throwable) -> { // depends on waiting for the world to load...
            Bukkit.getScheduler().runTask(this.plugin, () -> {
                try {
                    plugin.loadWorld(slimeWorld, true);
                } catch (UnknownWorldException | WorldLockedException | IOException e) {
                    this.plugin.getLogger().log(Level.SEVERE, "An error occoured whilst loading map!", e);
                }
            });
            Bukkit.getScheduler().runTaskLater(this.plugin, () -> {
                worldManager.createSpawnPlatform();
                scoreboardManager.init();
                gameState = GameState.WAITING;
                FileConfiguration config = this.plugin.getConfig();
                ConfigurationSection section = config.getConfigurationSection("Teams");
                for (String key : section.getKeys(false)) {
                    ConfigurationSection teamSection = section.getConfigurationSection(key);
                    try {
                        Bukkit.getScoreboardManager().getMainScoreboard().getTeam(key).unregister();
                    } catch (Exception ignored) {
                    }
                    Team t = new Team(
                            key,
                            teamSection.getString("TAB_PREFIX"),
                            ChatColor.valueOf(teamSection.getString("TEAM_COLOR")),
                            Material.valueOf(teamSection.getString("BED_ITEM")),
                            this.plugin.getLocation(teamSection.getCurrentPath() + ".SPAWN_LOCATION"),
                            this.plugin.getLocation(teamSection.getCurrentPath() + ".GENERATOR_LOCATION"),
                            this.plugin.getLocation(teamSection.getCurrentPath() + ".ITEM_SHOP_LOCATION"),
                            this.plugin.getLocation(teamSection.getCurrentPath() + ".TEAM_SHOP_LOCATION"),
                            this.plugin.getLocation(teamSection.getCurrentPath() + ".TEAM_CHEST_LOCATION"),
                            Material.valueOf(teamSection.getString("WOOL_ITEM")),
                            Material.valueOf(teamSection.getString("GLASS_ITEM")),
                            Material.valueOf(teamSection.getString("TERRACOTTA_ITEM"))
                    );
                    teamlist.add(t);
                    beds.put(t, true);
                }
            }, 1);
        });
        timer = new BukkitRunnable() {
            @Override
            public void run() {
                secondsToNext--;
                if(secondsToNext == 0) {
                    setGameState(nextState);
                }
            }
        };
    }

    public void freeze() {
        beforeFrozen = gameState;
        gameState = GameState.FROZEN;
        timer.cancel();
    }

    public void thaw() {
        gameState = beforeFrozen;
        beforeFrozen = null;
        timer = new BukkitRunnable() {
            @Override
            public void run() {
                secondsToNext--;
                if(secondsToNext == 0) {
                    setGameState(nextState);
                }
            }
        };
        timer.runTaskTimer(plugin, 0, 20);
    }

    public void start() {
        worldManager.worldSetup();
        worldManager.removeSpawnPlatform();
        STARTED = true;
        timer.runTaskTimer(plugin, 0, 20);
        setGameState(GameState.PLAY);
        generatorManager.registerTeamGenerators();
        generatorManager.registerDiamondGenerators();
        generatorManager.registerEmeraldGenerators();
        Bukkit.getOnlinePlayers().forEach(player -> statsManager.propagatePlayer(player.getUniqueId()));
        // split players into teams
        List<UUID> players = new ArrayList<>();
        alivePlayers = players;
        Bukkit.getOnlinePlayers().forEach(player -> players.add(player.getUniqueId()));
        playerTeams = splitPlayersIntoTeams(players);

        playerTeams.keySet().forEach(team -> {
            List<UUID> uuids = playerTeams.get(team);
            uuids.forEach(uuid -> {
                Player p = Bukkit.getPlayer(uuid);
                if (p != null) {
                    p.getInventory().clear();
                    p.setGameMode(GameMode.SURVIVAL);
                    mcTeams.get(team).addEntry(p.getName());
                    p.teleport(team.spawnLocation());
                    setArmor(uuid, ArmorLevel.NONE);
                    setAxe(uuid, AxeLevel.NONE);
                    setPickaxe(uuid, PickaxeLevel.NONE);
                    shears.put(uuid, false);

                    p.setInvulnerable(false);
                    p.getInventory().addItem(Items.DEFAULT_SWORD);
                    p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
                    playerInventoryManager.setArmor(p, team, ArmorLevel.NONE);
                }
            });
        });
        for (Team t : teamlist) {

            // remove bed if team is empty
            if (playerTeams.get(t).isEmpty()) {
                beds.put(t, false);
            }

            NPC teamShop = new NPC(t.teamShopLocation().getWorld());
            Settings teamSettings = new Settings(true, false, false, t.teamShopLocation().getYaw(), NPC_SKIN_QUESTION_MARK_VALUE, NPC_SKIN_QUESTION_MARK_SIGNATURE, "Question Mark", "<red><bold>Coming Soon</bold></red>");

            teamShop.setPostion(t.teamShopLocation())
                    .setActions(
                            List.of(
                                    new Action(
                                            ActionType.RUN_COMMAND,
                                            new ArrayList<>(List.of("openteamshop")),
                                            0,
                                            Conditional.SelectionMode.ONE,
                                            List.of()
                                    )
                            )
                    )
                    .create();
            teamShop.setSettings(teamSettings);
            teamShop.reloadSettings();
            npcs.add(teamShop);

            NPC itemShop = new NPC(t.itemShopLocation().getWorld());
            Settings itemSettings = new Settings(true, false, false, t.itemShopLocation().getYaw(), NPC_SKIN_VALUE, NPC_SKIN_SIGNATURE, "Shop Keeper", "<GOLD><bold>ITEM SHOP</bold></gold>");
            itemShop
                    .setPostion(t.itemShopLocation())
                    .setActions(
                            List.of(
                                    new Action(
                                            ActionType.RUN_COMMAND,
                                            new ArrayList<>(List.of("openitemshop")),
                                            0,
                                            Conditional.SelectionMode.ONE,
                                            List.of()
                                    )
                            )
                    )
                    .create();
            itemShop.setSettings(itemSettings);
            itemShop.reloadSettings();
            npcs.add(itemShop);
        }
    }

    private Map<Team, List<UUID>> splitPlayersIntoTeams(List<UUID> players) {
        int numTeams = teamlist.size();
        int teamSize = players.size() / numTeams;
        int remainingPlayers = players.size() % numTeams;
        Map<Team, List<UUID>> result = new HashMap<>();

        int playerIndex = 0;
        for (Team team : teamlist) {
            org.bukkit.scoreboard.Team t = Bukkit.getScoreboardManager().getMainScoreboard().registerNewTeam(team.displayName());
            t.setOption(org.bukkit.scoreboard.Team.Option.COLLISION_RULE, org.bukkit.scoreboard.Team.OptionStatus.FOR_OTHER_TEAMS);
            t.setCanSeeFriendlyInvisibles(true);
            t.setAllowFriendlyFire(false);
            t.setColor(team.color());
            t.setPrefix(ChatColor.translateAlternateColorCodes('&', team.prefix()));
            mcTeams.put(team, t);
            List<UUID> teamPlayers = new ArrayList<>();
            int currentTeamSize = teamSize + (remainingPlayers > 0 ? 1 : 0);
            for (int i = 0; i < currentTeamSize; i++) {
                if (playerIndex < players.size()) {
                    teamPlayers.add(players.get(playerIndex));
                    playerIndex++;
                }
            }
            result.put(team, teamPlayers);
            if (remainingPlayers > 0) {
                remainingPlayers--;
            }
        }

        return result;
    }

    public GameState getGameState() {
        return gameState;
    }

    private void setGameState(GameState gameState) {
        if(gameState != GameState.FROZEN)
            secondsToNext = gameState.getDuration();
        this.gameState = gameState;
        switch (gameState) {
            case WAITING -> {
                nextState = GameState.PLAY;
            }
            case PLAY -> {
                nextState = GameState.DEATHMATCH;
            }
            case DEATHMATCH -> {
                // beds broken
                teamlist.forEach(team -> {
                    if(beds.get(team)) {
                        breakBed(team);
                    }
                });

                Bukkit.broadcastMessage(ChatColor.YELLOW + "All beds have been BROKEN!");
                nextState = GameState.SUDDEN_DEATH;
            }
            case SUDDEN_DEATH -> {
                teamlist.forEach(team -> {
                    Wither dragon = team.spawnLocation().getWorld().spawn(team.spawnLocation().clone().subtract(0, -1, 0), Wither.class);
                    if(dragon.getBossBar() != null)
                        dragon.getBossBar().setVisible(false);
                });
                nextState = GameState.ENDED;
            }
            case ENDED -> {
                if(!ENDED)
                    endGameinDraw();
                nextState = GameState.CLEANUP;
            }
        }
    }

    public StatsManager getStatsManager() {
        return statsManager;
    }

    public MenuManager getMenuManager() {
        return menuManager;
    }

    public PlayerInventoryManager getPlayerInventoryManager() {
        return playerInventoryManager;
    }

    public List<Team> getTeamlist() {
        return teamlist;
    }

    public Map<Team, Boolean> getBeds() {
        return beds;
    }

    public ScoreboardManager getScoreboardManager() {
        return scoreboardManager;
    }

    public Map<Team, List<UUID>> getPlayerTeams() {
        return playerTeams;
    }

    public void cleanup() {
        STARTED = false;
        setGameState(GameState.CLEANUP);
        mcTeams.values().forEach(org.bukkit.scoreboard.Team::unregister);
        npcs.forEach(NPC::remove);
    }

    @Nullable
    public Team getPlayerTeam(UUID uuid) {
        for (Team t : teamlist) {
            if (playerTeams.get(t).contains(uuid))
                return t;
        }
        return null;
    }

    public void breakBed(Player player, Team t) {
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1000f, 1f);
        Bukkit.broadcastMessage(getPlayerTeam(player.getUniqueId()).color() + player.getName() + ChatColor.YELLOW + " destroyed " + t.color() + t.displayName() + ChatColor.YELLOW + "'s bed!");
        getPlayerTeams().get(t).forEach(uuid -> {
            Player p = Bukkit.getPlayer(uuid);
            if(p != null && p.isOnline()) {
                p.sendTitle(ChatColor.RED + "" + ChatColor.BOLD + "BED DESTROYED!", "You will no longer respawn!", 10, 50, 10);
            }
        });
        beds.put(t, false);
    }

    public void breakBed(Team t) {
        getPlayerTeams().get(t).forEach(uuid -> {
            Player player = Bukkit.getPlayer(uuid);
            if(player != null && player.isOnline()) {
                player.sendTitle(ChatColor.RED + "" + ChatColor.BOLD + "BED DESTROYED!", "You will no longer respawn!", 10, 50, 10);
                player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1F, 1F);
            }
        });
        beds.put(t, false);
    }

    public void kill(Player dead, @Nullable Player killer, EntityDamageEvent.DamageCause cause) {
        if (spectators.contains(dead.getUniqueId())) return;
        if(ENDED) return;
        alivePlayers.remove(dead.getUniqueId());
        statsManager.addPlayerDeath(dead.getUniqueId());

        //degrade tools
        setAxe(dead.getUniqueId(), AxeLevel.getOrdered(getAxe(dead.getUniqueId()), -1));
        setPickaxe(dead.getUniqueId(), PickaxeLevel.getOrdered(getPickaxe(dead.getUniqueId()), -1));

        boolean finalkill = false;
        String message = ChatColor.translateAlternateColorCodes('&', getPlayerTeam(dead.getUniqueId()).prefix()) + dead.getName() + ChatColor.RESET;
        if (!beds.get(getPlayerTeam(dead.getUniqueId()))) {
            finalkill = true;
        }
        switch (cause) {
            case KILL, ENTITY_ATTACK -> {
                if (killer == null) {
                    kill(dead, null, EntityDamageEvent.DamageCause.CUSTOM);
                } else {
                    statsManager.addPlayerKill(killer.getUniqueId());
                    message += ChatColor.GRAY + " was slain by " + ChatColor.translateAlternateColorCodes('&', getPlayerTeam(killer.getUniqueId()).prefix()) + killer.getName();

                    int emeralds = playerInventoryManager.itemCount(dead, "EMERALD");
                    int diamonds = playerInventoryManager.itemCount(dead, "DIAMOND");
                    int gold = playerInventoryManager.itemCount(dead, "GOLD");
                    int iron = playerInventoryManager.itemCount(dead, "IRON");

                    if(emeralds >= 1) killer.sendMessage(ChatColor.DARK_GREEN + "+" + emeralds + " Emeralds");
                    if(diamonds >= 1) killer.sendMessage(ChatColor.AQUA + "+" + diamonds + " Diamonds");
                    if(gold >= 1) killer.sendMessage(ChatColor.GOLD + "+" + gold + " Gold");
                    if(iron >= 1) killer.sendMessage(ChatColor.GRAY + "+" + iron + " Iron");

                    ItemStack em = Items.EMERALD.clone();
                    em.setAmount(emeralds);

                    ItemStack dia = Items.DIAMOND.clone();
                    dia.setAmount(diamonds);

                    ItemStack goldItem = Items.GOLD.clone();
                    goldItem.setAmount(gold);

                    ItemStack ironItem = Items.IRON.clone();
                    ironItem.setAmount(iron);

                    killer.getInventory().addItem(em, dia, goldItem, ironItem);
                }
            }
            case FALL -> message += ChatColor.GRAY + " has fallen to their death";
            case FIRE, FIRE_TICK -> message += ChatColor.GRAY + " was roasted like a turkey";
            case LAVA -> message += ChatColor.GRAY + " discovered lava is, in fact, hot";
            case VOID -> message += ChatColor.GRAY + " fell into the etherial abyss";
            case FREEZE -> message += ChatColor.GRAY + " turned into an ice cube";
            case DROWNING -> message += ChatColor.GRAY + " forgot how to swim";
            case ENTITY_EXPLOSION, BLOCK_EXPLOSION ->
                    message += ChatColor.GRAY + " went " + ChatColor.RED + "" + ChatColor.BOLD + "BOOM!";
            case PROJECTILE -> message += ChatColor.GRAY + " was remotley terminated";
            case CUSTOM -> message += " died.";
            default -> {
                plugin.getLogger().info(String.valueOf(cause));
                message += ChatColor.GRAY + " died under mysterious circumstances";
            }
        }

        if (finalkill) {
            dead.getWorld().strikeLightningEffect(dead.getLocation());
            dead.teleport(plugin.getLocation("SpawnPlatformCenter"));
            dead.sendTitle(ChatColor.RED + "" + ChatColor.BOLD + "YOU DIED!", ChatColor.YELLOW + "You won't repsawn", 5, 55, 5);
            message += ChatColor.DARK_RED + "" + ChatColor.BOLD + " FINAL KILL!";
            dead.setGameMode(GameMode.SPECTATOR);
            Bukkit.broadcastMessage(message);
            checkForWin();
            return;
        }

        dead.teleport(plugin.getLocation("SpawnPlatformCenter"));
        // respawn logic...
        Bukkit.broadcastMessage(message);
        dead.sendTitle(ChatColor.RED + "" + ChatColor.BOLD + "You DIED!", ChatColor.YELLOW + "You will repsawn soon", 5, 15, 5);
        dead.setGameMode(GameMode.SPECTATOR);
        dead.getInventory().clear();
        dead.setHealth(20.0);
        dead.setFireTicks(0); // reset fire

        new RespawnRunnable(plugin, 6, dead).runTaskTimer(plugin, 0, 20);
    }

    public void respawnPlayer(Player dead) {
        dead.clearActivePotionEffects();
        dead.setGameMode(GameMode.SURVIVAL);
        dead.getInventory().addItem(Items.DEFAULT_SWORD);
        dead.setHealth(dead.getMaxHealth());
        dead.setNoDamageTicks(100);// make them invincible for 5 sec
        dead.teleport(getPlayerTeam(dead.getUniqueId()).spawnLocation());

        // set armor
        dead.getInventory().setLeggings(Items.get(String.format(armorLevels.get(dead.getUniqueId()).getLegsID(), getPlayerTeam(dead.getUniqueId()).color().name())));
        dead.getInventory().setBoots(Items.get(String.format(armorLevels.get(dead.getUniqueId()).getBootsID(), getPlayerTeam(dead.getUniqueId()).color().name())));
        dead.getInventory().setChestplate(Items.get(String.format("%s_CHEST", getPlayerTeam(dead.getUniqueId()).color().name())));

        // set tools
        //todo: check for enchants / team upgrades
        dead.getInventory().addItem(Items.get(getPickaxe(dead.getUniqueId()).getItemID()), Items.get(getAxe(dead.getUniqueId()).getItemID()));
        if (shears.get(dead.getUniqueId())) {
            dead.getInventory().addItem(Items.SHEARS);
        }

        alivePlayers.add(dead.getUniqueId());
    }

    public List<UUID> getAlivePlayers() {
        return alivePlayers;
    }

    public void setArmor(UUID uuid, @NotNull ArmorLevel level) {
        armorLevels.put(uuid, level);
    }

    public void setAxe(UUID uuid, @NotNull AxeLevel level) {
        axes.put(uuid, level);
    }

    public void setPickaxe(UUID uuid, @NotNull PickaxeLevel level) {
        pickaxes.put(uuid, level);
    }

    public PickaxeLevel getPickaxe(UUID uuid) {
        if (pickaxes.getOrDefault(uuid, PickaxeLevel.NONE) == null) {
            return PickaxeLevel.NONE;
        }
        return pickaxes.getOrDefault(uuid, PickaxeLevel.NONE);
    }

    public AxeLevel getAxe(UUID uuid) {
        if (axes.getOrDefault(uuid, AxeLevel.NONE) == null) {
            return AxeLevel.NONE;
        }
        return axes.getOrDefault(uuid, AxeLevel.NONE);
    }

    public WorldManager getWorldManager() {
        return worldManager;
    }

    public void checkForWin() {
        int teamsWithBeds = 0;
        int emptyTeams = 0;
        List<Team> emptyTeamList = new ArrayList<>();
        for (Team t : teamlist) {
            if (getBeds().get(t)) {
                teamsWithBeds++;
            } else {
                AtomicInteger numberOfAlivePlayers = new AtomicInteger();
                playerTeams.get(t).forEach(uuid -> {
                    if (!spectators.contains(uuid)) numberOfAlivePlayers.getAndIncrement();
                });
                if (numberOfAlivePlayers.get() <= 0) {
                    emptyTeams++;
                    emptyTeamList.add(t);
                }
            }
        }
        if (teamsWithBeds > 1) {
            return;
        }

        if (emptyTeams >= teamlist.size() - 1) {
            ENDED = true;
            setGameState(GameState.ENDED);
            playerTeams.forEach((team, uuids) -> {
                if (emptyTeamList.contains(team)) {
                    uuids.forEach(uuid -> {
                        Player player = Bukkit.getPlayer(uuid);
                        if (player != null && player.isOnline()) {
                            player.sendTitle(ChatColor.RED + "" + ChatColor.BOLD + "GAME OVER!", "Better luck next time!", 10, 120, 0);
                        }
                    });
                } else {
                    uuids.forEach(uuid -> {
                        Player player = Bukkit.getPlayer(uuid);
                        if (player != null && player.isOnline()) {
                            player.sendTitle(ChatColor.GOLD + "" + ChatColor.BOLD + "VICTORY!", "", 10, 120, 0);
                        }
                    });
                }
            });
        }
    }

    public void endGameinDraw() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendTitle(ChatColor.RED + "" + ChatColor.BOLD + "GAME OVER!", "This was was a draw!", 10, 120, 0);
        }
        ENDED = true;
        setGameState(GameState.ENDED);
    }

    public String getTimeToNextFormatted() {
        int mins = secondsToNext / 60;
        int seconds = secondsToNext % 60;
        return mins + ":" + (seconds <= 9 ? "0" : "") + seconds;
    }

    public GeneratorManager getGeneratorManager() {
        return generatorManager;
    }
}
