package net.cytonic.cytonicbedwars.utils;

import net.cytonic.cytonicbedwars.CytonicBedWars;
import net.cytonic.cytonicbedwars.data.enums.MappableItem;
import net.cytonic.cytonicbedwars.data.objects.Team;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionType;

import java.util.*;
import java.util.stream.Stream;

public class Items {
    private static final CytonicBedWars plugin = CytonicBedWars.INSTANCE;
    private static final Map<String, ItemStack> itemRegistry = new HashMap<>();
    public static NamespacedKey NAMESPACE = new NamespacedKey(plugin, "bwID");
    public static NamespacedKey MOVE_BLACKLIST = new NamespacedKey(plugin, "move_blacklist");
    public static NamespacedKey ALLOWED_SLOTS = new NamespacedKey(plugin, "allowed_slots");
    public static NamespacedKey NO_DROP = new NamespacedKey(plugin, "no_drop");

    // item constants

    // spectator items
    public static ItemStack SPECTATOR_TARGET_SELECTOR = createItem(ChatColor.GREEN + "Target Selector", "SPECTATOR_COMPASS", Material.COMPASS, true, true, List.of(0), new HashMap<>(), ChatColor.GRAY + "Right click to teleport to a player!");
    public static ItemStack SPECTATOR_SPEED_SELECTOR = createItem(ChatColor.AQUA + "Speed Selector", "SPECTATOR_SPEED_SELECTOR", Material.HEART_OF_THE_SEA, true, true, List.of(4), new HashMap<>(), ChatColor.GRAY + "Right click to change flight speed.");
    public static ItemStack SPECTATOR_LOBBY_REQEST = createItem(ChatColor.RED + "Go to Lobby", "LOBBY_REQUEST", Material.RED_BED, true, true, List.of(8), new HashMap<>(), ChatColor.GOLD + "To the lobby!");
    public static ItemStack SPECTATOR_ARMOR = createItem(" ", "SPECTATOR_ARMOR", Material.BARRIER, true, true, List.of(36, 37, 38, 39), new HashMap<>());

    // shop items

    // all 16 of the glasses
    public static ItemStack RED_BLAST_PROOF_GLASS = createItem(ChatColor.GREEN + "Blast-Proof Glass", "RED_BLAST_PROOF_GLASS", Material.RED_STAINED_GLASS);
    public static ItemStack ORANGE_BLAST_PROOF_GLASS = createItem(ChatColor.GREEN + "Blast-Proof Glass", "ORANGE_BLAST_PROOF_GLASS", Material.ORANGE_STAINED_GLASS);
    public static ItemStack YELLOW_BLAST_PROOF_GLASS = createItem(ChatColor.GREEN + "Blast-Proof Glass", "YELLOW_BLAST_PROOF_GLASS", Material.YELLOW_STAINED_GLASS);
    public static ItemStack LIME_BLAST_PROOF_GLASS = createItem(ChatColor.GREEN + "Blast-Proof Glass", "LIME_BLAST_PROOF_GLASS", Material.LIME_STAINED_GLASS);
    public static ItemStack GREEN_BLAST_PROOF_GLASS = createItem(ChatColor.GREEN + "Blast-Proof Glass", "GREEN_BLAST_PROOF_GLASS", Material.GREEN_STAINED_GLASS);
    public static ItemStack LIGHT_BLUE_BLAST_PROOF_GLASS = createItem(ChatColor.GREEN + "Blast-Proof Glass", "LIGHT_BLUE_BLAST_PROOF_GLASS", Material.LIGHT_BLUE_STAINED_GLASS);
    public static ItemStack BLUE_BLAST_PROOF_GLASS = createItem(ChatColor.GREEN + "Blast-Proof Glass", "BLUE_BLAST_PROOF_GLASS", Material.BLUE_STAINED_GLASS);
    public static ItemStack CYAN_BLAST_PROOF_GLASS = createItem(ChatColor.GREEN + "Blast-Proof Glass", "CYAN_BLAST_PROOF_GLASS", Material.CYAN_STAINED_GLASS);
    public static ItemStack MAGENTA_BLAST_PROOF_GLASS = createItem(ChatColor.GREEN + "Blast-Proof Glass", "MAGENTA_BLAST_PROOF_GLASS", Material.MAGENTA_STAINED_GLASS);
    public static ItemStack PINK_BLAST_PROOF_GLASS = createItem(ChatColor.GREEN + "Blast-Proof Glass", "PINK_BLAST_PROOF_GLASS", Material.PINK_STAINED_GLASS);
    public static ItemStack PURPLE_BLAST_PROOF_GLASS = createItem(ChatColor.GREEN + "Blast-Proof Glass", "PURPLE_BLAST_PROOF_GLASS", Material.PURPLE_STAINED_GLASS);
    public static ItemStack WHITE_BLAST_PROOF_GLASS = createItem(ChatColor.GREEN + "Blast-Proof Glass", "WHITE_BLAST_PROOF_GLASS", Material.WHITE_STAINED_GLASS);
    public static ItemStack GREY_BLAST_PROOF_GLASS = createItem(ChatColor.GREEN + "Blast-Proof Glass", "GREY_BLAST_PROOF_GLASS", Material.GRAY_STAINED_GLASS);
    public static ItemStack LIGHT_GREY_BLAST_PROOF_GLASS = createItem(ChatColor.GREEN + "Blast-Proof Glass", "LIGHT_GREY_BLAST_PROOF_GLASS", Material.LIGHT_GRAY_STAINED_GLASS);
    public static ItemStack BLACK_BLAST_PROOF_GLASS = createItem(ChatColor.GREEN + "Blast-Proof Glass", "BLACK_BLAST_PROOF_GLASS", Material.BLACK_STAINED_GLASS);

    // all 16 of the wools
    public static ItemStack RED_WOOL = createItem("Wool", "RED_WOOL", Material.RED_WOOL);
    public static ItemStack ORANGE_WOOL = createItem("Wool", "ORANGE_WOOL", Material.ORANGE_WOOL);
    public static ItemStack YELLOW_WOOL = createItem("Wool", "YELLOW_WOOL", Material.YELLOW_WOOL);
    public static ItemStack LIME_WOOL = createItem("Wool", "LIME_WOOL", Material.LIME_WOOL);
    public static ItemStack GREEN_WOOL = createItem("Wool", "GREEN_WOOL", Material.GREEN_WOOL);
    public static ItemStack LIGHT_BLUE_WOOL = createItem("Wool", "LIGHT_BLUE_WOOL", Material.LIGHT_BLUE_WOOL);
    public static ItemStack BLUE_WOOL = createItem("Wool", "BLUE_WOOL", Material.BLUE_WOOL);
    public static ItemStack CYAN_WOOL = createItem("Wool", "CYAN_WOOL", Material.CYAN_WOOL);
    public static ItemStack MAGENTA_WOOL = createItem("Wool", "MAGENTA_WOOL", Material.MAGENTA_WOOL);
    public static ItemStack PINK_WOOL = createItem("Wool", "PINK_WOOL", Material.PINK_WOOL);
    public static ItemStack PURPLE_WOOL = createItem("Wool", "PURPLE_WOOL", Material.PURPLE_WOOL);
    public static ItemStack WHITE_WOOL = createItem("Wool", "WHITE_WOOL", Material.WHITE_WOOL);
    public static ItemStack GREY_WOOL = createItem("Wool", "GREY_WOOL", Material.GRAY_WOOL);
    public static ItemStack LIGHT_GREY_WOOL = createItem("Wool", "LIGHT_GREY_WOOL", Material.LIGHT_GRAY_WOOL);
    public static ItemStack BLACK_WOOL = createItem("Wool", "BLACK_WOOL", Material.BLACK_WOOL);

    // all 16 of the terracottas
    public static ItemStack RED_TERRACOTTA = createItem("Terracota", "RED_TERRACOTTA", Material.RED_TERRACOTTA);
    public static ItemStack ORANGE_TERRACOTTA = createItem("Terracota", "ORANGE_TERRACOTTA", Material.ORANGE_TERRACOTTA);
    public static ItemStack YELLOW_TERRACOTTA = createItem("Terracota", "YELLOW_TERRACOTTA", Material.YELLOW_TERRACOTTA);
    public static ItemStack LIME_TERRACOTTA = createItem("Terracota", "LIME_TERRACOTTA", Material.LIME_TERRACOTTA);
    public static ItemStack GREEN_TERRACOTTA = createItem("Terracota", "GREEN_TERRACOTTA", Material.GREEN_TERRACOTTA);
    public static ItemStack LIGHT_BLUE_TERRACOTTA = createItem("Terracota", "LIGHT_BLUE_TERRACOTTA", Material.LIGHT_BLUE_TERRACOTTA);
    public static ItemStack BLUE_TERRACOTTA = createItem("Terracota", "BLUE_TERRACOTTA", Material.BLUE_TERRACOTTA);
    public static ItemStack CYAN_TERRACOTTA = createItem("Terracota", "CYAN_TERRACOTTA", Material.CYAN_TERRACOTTA);
    public static ItemStack MAGENTA_TERRACOTTA = createItem("Terracota", "MAGENTA_TERRACOTTA", Material.MAGENTA_TERRACOTTA);
    public static ItemStack PINK_TERRACOTTA = createItem("Terracota", "PINK_TERRACOTTA", Material.PINK_TERRACOTTA);
    public static ItemStack PURPLE_TERRACOTTA = createItem("Terracota", "PURPLE_TERRACOTTA", Material.PURPLE_TERRACOTTA);
    public static ItemStack WHITE_TERRACOTTA = createItem("Terracota", "WHITE_TERRACOTTA", Material.WHITE_TERRACOTTA);
    public static ItemStack GREY_TERRACOTTA = createItem("Terracota", "GREY_TERRACOTTA", Material.GRAY_TERRACOTTA);
    public static ItemStack LIGHT_GREY_TERRACOTTA = createItem("Terracota", "LIGHT_GREY_TERRACOTTA", Material.LIGHT_GRAY_TERRACOTTA);
    public static ItemStack BLACK_TERRACOTTA = createItem("Terracota", "BLACK_TERRACOTTA", Material.BLACK_TERRACOTTA);

    public static ItemStack OBSIDIAN = createItem(ChatColor.DARK_PURPLE + "Obsidian", "OBSIDIAN", Material.OBSIDIAN);
    public static ItemStack PLANKS = createItem("Planks", "PLANKS", Material.OAK_PLANKS);
    public static ItemStack END_STONE = createItem("End Stone", "END_STONE", Material.END_STONE);

    // weapons
    public static ItemStack DEFAULT_SWORD = createItem("Default Sword", "DEFAULT_SWORD", Material.WOODEN_SWORD, false, true, List.of(), new HashMap<>());
    public static ItemStack STONE_SWORD = createItem("Stone Sword", "STONE_SWORD", Material.STONE_SWORD);
    public static ItemStack IRON_SWORD = createItem("Iron Sword", "IRON_SWORD", Material.IRON_SWORD);
    public static ItemStack DIAMOND_SWORD = createItem("Diamond Sword", "DIAMOND_SWORD", Material.DIAMOND_SWORD);
    public static ItemStack SHIELD = createItem("Shield", "SHIELD", Material.SHIELD);
    public static ItemStack BOW_1 = createItem("Bow", "BOW_1", Material.BOW);
    public static ItemStack BOW_2 = createItem("Bow", "BOW_2", Material.BOW, false, false, List.of(), Map.of(Enchantment.ARROW_DAMAGE, 1), ChatColor.GRAY + "Power 1");
    public static ItemStack BOW_3 = createItem("Bow", "BOW_3", Material.BOW, false, false, List.of(), Map.of(Enchantment.ARROW_DAMAGE, 3, Enchantment.ARROW_KNOCKBACK, 2), ChatColor.GRAY + "Power 3", ChatColor.GRAY + "Punch 2");
    public static ItemStack CROSSBOW_1 = createItem("Crossbow", "CROSSBOW_1", Material.CROSSBOW);
    public static ItemStack CROSSBOW_2 = createItem("Crossbow", "CROSSBOW_2", Material.CROSSBOW, false, false, List.of(), Map.of(Enchantment.QUICK_CHARGE,1), ChatColor.GRAY + "Quick Charge 1");
    public static ItemStack CROSSBOW_3 = createItem("Crossbow", "CROSSBOW_3", Material.CROSSBOW, false, false, List.of(), Map.of(Enchantment.QUICK_CHARGE,2, Enchantment.MULTISHOT, 1), ChatColor.GRAY + "Quick Charge 2", ChatColor.GRAY + "Multishot");
    public static ItemStack TRIDENT_1 = createItem("Trident", "TRIDENT_1", Material.TRIDENT, false, false, List.of(), Map.of(Enchantment.LOYALTY, 1), ChatColor.GRAY + "Loyalty 1");
    public static ItemStack TRIDENT_2 = createItem("Trident", "TRIDENT_2", Material.TRIDENT, false, false, List.of(), Map.of(Enchantment.RIPTIDE, 2), ChatColor.GRAY + "Riptide 2");
    public static ItemStack TRIDENT_3 = createItem("Trident", "TRIDENT_3", Material.TRIDENT, false, false, List.of(), Map.of(Enchantment.LOYALTY, 2, Enchantment.CHANNELING, 1), ChatColor.GRAY + "Loyalty 2", ChatColor.GRAY + "Channeling");
    public static ItemStack ARROW = createItem("Arrow", "ARROW", Material.ARROW);

    // shop menu parts
    public static ItemStack MENU_SELECTED_PAGE = createItem(" ", "MENU_SELECTED_PAGE", Material.LIME_STAINED_GLASS_PANE, false, true, List.of(), Map.of(Enchantment.DURABILITY, 1));
    public static ItemStack MENU_FILLER = createItem(" ", "MENU_FILLER", Material.GRAY_STAINED_GLASS_PANE);
    public static ItemStack MENU_BLOCKS = createItem(ChatColor.GREEN + "Building Blocks", "MENU_BLOCKS", Material.BRICKS,  ChatColor.GRAY + "Click to browse!");
    public static ItemStack MENU_COMBAT = createItem(ChatColor.GREEN + "Weapons", "MENU_COMBAT", Material.DIAMOND_SWORD,  ChatColor.GRAY + "Click to browse!");
    public static ItemStack MENU_ARMOR = createItem(ChatColor.GREEN + "Armor", "MENU_ARMOR", Material.DIAMOND_CHESTPLATE,  ChatColor.GRAY + "Click to browse!");
    public static ItemStack MENU_TOOLS = createItem(ChatColor.GREEN + "Tools", "MENU_ARMOR", Material.DIAMOND_PICKAXE,  ChatColor.GRAY + "Click to browse!");
    public static ItemStack MENU_POTIONS = createItem(ChatColor.GREEN + "Potions", "MENU_BLOCKS", Material.BREWING_STAND,  ChatColor.GRAY + "Click to browse!");
    public static ItemStack MENU_UTILS = createItem(ChatColor.GREEN + "Utilities", "MENU_BLOCKS", Material.FIRE_CHARGE,  ChatColor.GRAY + "Click to browse!");
    public static ItemStack MENU_CUSTOM_ITEMS = createItem(ChatColor.GREEN + "Rotating Items", "MENU_BLOCKS", Material.ENDERMAN_SPAWN_EGG,  ChatColor.GRAY + "Click to browse!");
    public static ItemStack MENU_WOOL = createItem("Wool",  "MENU_WOOL", Material.WHITE_WOOL,  "", ChatColor.GRAY + "> 4 Iron");
    public static ItemStack MENU_PLANKS = createItem("Planks",  "MENU_PLANKS", Material.OAK_PLANKS,  "", ChatColor.GOLD + "> 4 Gold");
    public static ItemStack MENU_TERRACOTTA = createItem("Terracotta",  "MENU_TERRACOTTA", Material.TERRACOTTA,  "", ChatColor.GRAY + "> 10 Iron");
    public static ItemStack MENU_END_STONE = createItem("End Stone",  "MENU_END_STONE", Material.END_STONE,  "", ChatColor.GRAY + "> 24 Iron");
    public static ItemStack MENU_BLAST_GLASS = createItem("Blast-Proof Glass",  "MENU_BLAST_GLASS", Material.GLASS,  "", ChatColor.GRAY + "> 12 Iron");
    public static ItemStack MENU_OBSIDIAN = createItem("Obsidian",  "MENU_OBSIDIAN", Material.OBSIDIAN,  "", ChatColor.DARK_GREEN + "> 6 Emeralds");
    public static ItemStack MENU_STONE_SWORD = createItem("Stone Sword", "MENU_STONE_SWORD", Material.STONE_SWORD, "", ChatColor.GRAY + "> 10 Iron");
    public static ItemStack MENU_IRON_SWORD = createItem("Iron Sword", "MENU_IRON_SWORD", Material.IRON_SWORD, "", ChatColor.GOLD + "> 7 Gold");
    public static ItemStack MENU_DIAMOND_SWORD = createItem("Diamond Sword", "MENU_DIAMOND_SWORD", Material.DIAMOND_SWORD, "", ChatColor.DARK_GREEN + "> 7 Emeralds");
    public static ItemStack MENU_SHIELD = createItem("Shield", "MENU_SHIELD", Material.SHIELD, "", ChatColor.GOLD + "> 3 Gold");
    public static ItemStack MENU_BOW_1 = createItem("Bow", "MENU_BOW_1", Material.BOW, "", ChatColor.GOLD + "> 10 Gold");
    public static ItemStack MENU_BOW_2 = createItem("Bow", "MENU_BOW_2", Material.BOW, false, false, List.of(), Map.of(Enchantment.ARROW_DAMAGE, 1), ChatColor.GRAY + "Power 1", "", ChatColor.GOLD + "> 18 Gold");
    public static ItemStack MENU_BOW_3 = createItem("Bow", "MENU_BOW_3", Material.BOW, false, false, List.of(), Map.of(Enchantment.ARROW_DAMAGE, 1),ChatColor.GRAY + "Power 3", ChatColor.GRAY + "Punch 2", "", ChatColor.DARK_GREEN + "> 5 Emeralds");
    public static ItemStack MENU_CROSSBOW_1 = createItem("Crossbow", "MENU_CROSSBOW_1", Material.CROSSBOW, "", ChatColor.GOLD + "> 12 Gold");
    public static ItemStack MENU_CROSSBOW_2 = createItem("Crossbow", "MENU_CROSSBOW_2", Material.CROSSBOW, false, false, List.of(), Map.of(Enchantment.QUICK_CHARGE, 1),ChatColor.GRAY + "Quick Charge 1", "", ChatColor.GOLD + "> 20 Gold");
    public static ItemStack MENU_CROSSBOW_3 = createItem("Crossbow", "MENU_CROSSBOW_3", Material.CROSSBOW, false, false, List.of(), Map.of(Enchantment.QUICK_CHARGE, 1), ChatColor.GRAY + "Quick Charge 2", ChatColor.GRAY + "Multishot", "", ChatColor.DARK_GREEN + "> 8 Emeralds");
    public static ItemStack MENU_TRIDENT_1 = createItem("Trident", "MENU_TRIDENT_1", Material.TRIDENT, false, false, List.of(), Map.of(Enchantment.ARROW_DAMAGE, 1), ChatColor.GRAY + "Loyalty 1", "", ChatColor.GOLD + "> 12 Gold");
    public static ItemStack MENU_TRIDENT_2 = createItem("Trident", "MENU_TRIDENT_2", Material.TRIDENT, false, false, List.of(), Map.of(Enchantment.ARROW_DAMAGE, 1), ChatColor.GRAY + "Riptide 2", "", ChatColor.DARK_GREEN + "> 5 Emerald");
    public static ItemStack MENU_TRIDENT_3 = createItem("Trident", "MENU_TRIDENT_3", Material.TRIDENT, false, false, List.of(), Map.of(Enchantment.ARROW_DAMAGE, 1), ChatColor.GRAY + "Loyalty 2", ChatColor.GRAY + "Channeling", "", ChatColor.DARK_GREEN + "> 5 Emerald");
    public static ItemStack MENU_ARROW = createItem("Arrow", "MENU_ARROW", Material.ARROW, "", ChatColor.GOLD + "> 2 Gold");
    public static ItemStack MENU_NETHERITE_BOOTS = createItem("Permanent Netherite Armor", "NETHERITE_BOOTS", Material.NETHERITE_BOOTS,  ChatColor.DARK_GREEN + "> 16 Emeralds");
    public static ItemStack MENU_DIAMOND_BOOTS = createItem("Permanent Diamond Armor", "DIAMOND_BOOTS", Material.DIAMOND_BOOTS,  ChatColor.DARK_GREEN + "> 6 Emeralds");
    public static ItemStack MENU_IRON_BOOTS = createItem("Permanent Iron Armor", "IRON_BOOTS", Material.IRON_BOOTS,  ChatColor.GOLD + "> 12 Gold");
    public static ItemStack MENU_CHAINMAIL_BOOTS = createItem("Permanent Chainmail Armor", "CHAINMAIL_BOOTS", Material.CHAINMAIL_BOOTS,  ChatColor.GRAY + "> 40 Iron");
    public static ItemStack MENU_SHEARS = createItem("Shears", "SHEARS", Material.SHEARS, "", ChatColor.GRAY + "> 24 Iron");
    public static ItemStack MENU_WOODEN_AXE = createItem("Wooden Axe", "WOODEN_AXE", Material.WOODEN_AXE, "", ChatColor.GRAY + "> 10 Iron");
    public static ItemStack MENU_STONE_AXE = createItem("Stone Axe", "STONE_AXE", Material.STONE_AXE, "", ChatColor.GRAY + "> 20 Iron");
    public static ItemStack MENU_IRON_AXE = createItem("Iron Axe", "IRON_AXE", Material.IRON_AXE, "", ChatColor.GOLD + "> 6 Gold");
    public static ItemStack MENU_DIAMOND_AXE = createItem("Diamond Axe", "DIAMOND_AXE", Material.DIAMOND_AXE, "", ChatColor.DARK_GREEN + "> 3 Emeralds");
    public static ItemStack MENU_WOODEN_PICKAXE = createItem("Wooden Pickaxe", "WOODEN_PICKAXE", Material.WOODEN_PICKAXE, "", ChatColor.GRAY + "> 10 Iron");
    public static ItemStack MENU_STONE_PICKAXE = createItem("Stone Pickaxe", "STONE_PICKAXE", Material.STONE_PICKAXE, "", ChatColor.GRAY + "> 20 Iron");
    public static ItemStack MENU_IRON_PICKAXE = createItem("Iron Pickaxe", "IRON_PICKAXE", Material.IRON_PICKAXE, "", ChatColor.GOLD + "> 6 Gold");
    public static ItemStack MENU_DIAMOND_PICKAXE = createItem("Diamond Pickaxe", "DIAMOND_PICKAXE", Material.DIAMOND_PICKAXE, "", ChatColor.DARK_GREEN + "> 3 Emeralds");
    public static ItemStack MENU_FIRE_RESISTENCE_POTION = createPotion("Fire Resistence (60s)", "FIRE_RES_POT", PotionType.FIRE_RESISTANCE, 1200, 1, "", ChatColor.GOLD + "> 6 Gold");
    public static ItemStack MENU_INVISIBILITY_POTION = createPotion("Invisibility (30s)", "INVIS_POT", PotionType.INVISIBILITY, 600, 1, "", ChatColor.DARK_GREEN + "> 2 Emeralds");
    public static ItemStack MENU_JUMP_BOOST_POTION = createPotion("Jump Boost (60s)", "JUMP_BOOST_POT", PotionType.JUMP, 1200, 5, "", ChatColor.DARK_GREEN + "> 1 Emerald");
    public static ItemStack MENU_SPEED_POTION = createPotion("Speed (60s)", "SPEED_POT", PotionType.SPEED, 1200, 2, "", ChatColor.DARK_GREEN + "> 1 Emerald");
    public static ItemStack MENU_GOLDEN_APPLE = createItem("Golden Apple", "GOLDEN_APPLE", Material.GOLDEN_APPLE, "", ChatColor.GOLD + "> 3 Gold");
    public static ItemStack MENU_BED_BUG = createItem("Bed Bug", "BED_BUG", Material.SNOWBALL, "", ChatColor.GRAY + "> 40 Iron");
    public static ItemStack MENU_BED_PROTECTOR = createItem("Bed Protector", "BED_PROTECTOR", Material.GHAST_SPAWN_EGG, "", ChatColor.GRAY + "> 128 Iron");
    public static ItemStack MENU_FIREBALL = createItem("Fireball", "FIREBALL", Material.FIRE_CHARGE, "", ChatColor.GRAY + "> 40 Iron");
    public static ItemStack MENU_TNT = createItem("TNT", "TNT", Material.TNT, "", ChatColor.GOLD + "> 8 Gold");
    public static ItemStack MENU_ENDER_PEARL = createItem("Ender Pearl", "ENDER_PEARL", Material.ENDER_PEARL, "", ChatColor.DARK_GREEN + "> 4 Emerald");
    public static ItemStack MENU_WATER_BUCKET = createItem("Water Bucket", "WATER_BUCKET", Material.WATER_BUCKET, "", ChatColor.GOLD + "> 2 Gold");
    public static ItemStack MENU_BRIDGE_EGG = createItem("Bridge Egg", "BRIDGE_EGG", Material.EGG, "", ChatColor.DARK_GREEN + "> 1 Emerald");
    public static ItemStack MENU_SPONGE = createItem("Sponge", "SPONGE", Material.SPONGE, "", ChatColor.GOLD + "> 2 Gold");
    public static ItemStack MENU_POPUP_TOWER = createItem("Popup Tower", "POPUP_TOWER", Material.CHEST, "", ChatColor.GRAY + " > 24 Iron");
    public static ItemStack COMING_SOON = createItem(ChatColor.RED + "" + ChatColor.ITALIC + "Coming Soon", "COMING_SOON", Material.RED_STAINED_GLASS_PANE, "", ChatColor.RED + "" + ChatColor.BOLD + "COMING SOON");


    // curency items
    public static ItemStack IRON = createItem("Iron", "IRON", Material.IRON_NUGGET);
    public static ItemStack GOLD = createItem("Gold", "GOLD", Material.GOLD_NUGGET);
    public static ItemStack DIAMOND = createItem("Diamond", "DIAMOND", Material.DIAMOND);
    public static ItemStack EMERALD = createItem("Emerald", "EMERALD", Material.EMERALD);


    // Armors -- should probably make these dynamically, but not rn
    public static ItemStack BLUE_BOOTS = createArmor("Boots", "BLUE_BOOTS", Material.LEATHER_BOOTS, Color.BLUE);
    public static ItemStack BLUE_LEGS = createArmor("Leggings", "BLUE_LEGS", Material.LEATHER_LEGGINGS, Color.BLUE);
    public static ItemStack BLUE_CHEST = createArmor("Chestplate", "BLUE_CHEST", Material.LEATHER_CHESTPLATE, Color.BLUE);

    public static ItemStack RED_BOOTS = createArmor("Boots", "RED_BOOTS", Material.LEATHER_BOOTS, Color.RED);
    public static ItemStack RED_LEGS = createArmor("Leggings", "RED_LEGS", Material.LEATHER_LEGGINGS, Color.RED);
    public static ItemStack RED_CHEST = createArmor("Chestplate", "RED_CHEST", Material.LEATHER_CHESTPLATE, Color.RED);

    public static ItemStack GREEN_BOOTS = createArmor("Boots", "GREEN_BOOTS", Material.LEATHER_BOOTS, Color.LIME);
    public static ItemStack GREEN_LEGS = createArmor("Leggings", "GREEN_LEGS", Material.LEATHER_LEGGINGS, Color.LIME);
    public static ItemStack GREEN_CHEST = createArmor("Chestplate", "GREEN_CHEST", Material.LEATHER_CHESTPLATE, Color.LIME);

    public static ItemStack YELLOW_BOOTS = createArmor("Boots", "YELLOW_BOOTS", Material.LEATHER_BOOTS, Color.YELLOW);
    public static ItemStack YELLOW_LEGS = createArmor("Leggings", "YELLOW_LEGS", Material.LEATHER_LEGGINGS, Color.YELLOW);
    public static ItemStack YELLOW_CHEST = createArmor("Chestplate", "YELLOW_CHEST", Material.LEATHER_CHESTPLATE, Color.YELLOW);

    public static ItemStack NETHERITE_LEGS = createItem("Netherite Leggings", "NETHERITE_LEGS", Material.NETHERITE_LEGGINGS, true, true, List.of(37), Map.of());
    public static ItemStack DIAMOND_LEGS = createItem("Diamond Leggings", "DIAMOND_LEGS", Material.DIAMOND_LEGGINGS, true, true, List.of(37), Map.of());
    public static ItemStack IRON_LEGS = createItem("Iron Leggings", "IRON_LEGS", Material.IRON_LEGGINGS, true, true, List.of(37), Map.of());
    public static ItemStack CHAINMAIL_LEGS = createItem("Chainmail Leggings", "CHAINMAIL_LEGS", Material.CHAINMAIL_LEGGINGS, true, true, List.of(37), Map.of());

    public static ItemStack NETHERITE_BOOTS = createItem("Netherite Boots", "NETHERITE_BOOTS", Material.NETHERITE_BOOTS, true, true, List.of(36), Map.of());
    public static ItemStack DIAMOND_BOOTS = createItem("Diamond Boots", "DIAMOND_BOOTS", Material.DIAMOND_BOOTS, true, true, List.of(36), Map.of());
    public static ItemStack IRON_BOOTS = createItem("Iron Boots", "IRON_BOOTS", Material.IRON_BOOTS, true, true, List.of(36), Map.of());
    public static ItemStack CHAINMAIL_BOOTS = createItem("Chainmail Boots", "CHAINMAIL_BOOTS", Material.CHAINMAIL_BOOTS, true, true, List.of(36), Map.of());


    // tools

    public static ItemStack SHEARS = createItem("Shears", "SHEARS", Material.SHEARS, false, true, List.of(), Map.of());

    public static ItemStack WOODEN_AXE = createItem("Wooden Axe", "WOODEN_AXE", Material.WOODEN_AXE, false, true, List.of(), Map.of());
    public static ItemStack STONE_AXE = createItem("Stone Axe", "STONE_AXE", Material.STONE_AXE, false, true, List.of(), Map.of());
    public static ItemStack IRON_AXE = createItem("Iron Axe", "IRON_AXE", Material.IRON_AXE, false, true, List.of(), Map.of());
    public static ItemStack DIAMOND_AXE = createItem("Diamond Axe", "DIAMOND_AXE", Material.DIAMOND_AXE, false, true, List.of(), Map.of());

    public static ItemStack WOODEN_PICKAXE = createItem("Wooden Pickaxe", "WOODEN_PICKAXE", Material.WOODEN_PICKAXE, false, true, List.of(), Map.of());
    public static ItemStack STONE_PICKAXE = createItem("Stone Pickaxe", "STONE_PICKAXE", Material.STONE_PICKAXE, false, true, List.of(), Map.of());
    public static ItemStack IRON_PICKAXE = createItem("Iron Pickaxe", "IRON_PICKAXE", Material.IRON_PICKAXE, false, true, List.of(), Map.of());
    public static ItemStack DIAMOND_PICKAXE = createItem("Diamond Pickaxe", "DIAMOND_PICKAXE", Material.DIAMOND_PICKAXE, false, true, List.of(), Map.of());


    // potion types:
    // Fire res, Invis, jump, speed

    public static ItemStack FIRE_RESISTENCE_POTION = createPotion("Fire Resistence (60s)", "FIRE_RES_POT", PotionType.FIRE_RESISTANCE, 1200, 1);
    public static ItemStack INVISIBILITY_POTION = createPotion("Invisibility (30s)", "INVIS_POT", PotionType.INVISIBILITY, 600, 1, ChatColor.RED + "WARNING:" + ChatColor.GRAY + " Drinking this potion", ChatColor.GRAY + "temporarily removes your armor!");
    public static ItemStack JUMP_BOOST_POTION = createPotion("Jump Boost (60s)", "JUMP_BOOST_POT", PotionType.JUMP, 1200, 5);
    public static ItemStack SPEED_POTION = createPotion("Speed (60s)", "SPEED_POT", PotionType.SPEED, 1200, 2);
    //todo: IDEA: Grappling hook that works kinda like skyblocks moody grappleshot -- could be a global cap, or someth

    // Utilities:
    // items: Golden apple, bed bug, Bed Protector, fire charge, tnt, ender pearl, water bucket, bridge egg, sponge, popup-tower

    public static ItemStack GOLDEN_APPLE = createItem("Golden Apple", "GOLDEN_APPLE", Material.GOLDEN_APPLE);
    public static ItemStack BED_BUG = createItem("Bed Bug", "BED_BUG", Material.SNOWBALL, ChatColor.GRAY + "The bug contained may", ChatColor.GRAY + "produce collateral damage!");
    public static ItemStack BED_PROTECTOR = createItem("Bed Protector", "BED_PROTECTOR", Material.GHAST_SPAWN_EGG);
    public static ItemStack FIREBALL = createItem("Fireball", "FIREBALL", Material.FIRE_CHARGE);
    public static ItemStack TNT = createItem("TNT", "TNT", Material.TNT);
    public static ItemStack ENDER_PEARL = createItem("Ender Pearl", "ENDER_PEARL", Material.ENDER_PEARL);
    public static ItemStack WATER_BUCKET = createItem("Water Bucket", "WATER_BUCKET", Material.WATER_BUCKET);
    public static ItemStack BRIDGE_EGG = createItem("Bridge Egg", "BRIDGE_EGG", Material.EGG);
    public static ItemStack SPONGE = createItem("Sponge", "SPONGE", Material.SPONGE);
    public static ItemStack POPUP_TOWER = createItem("Popup Tower", "POPUP_TOWER", Material.CHEST);


    private static ItemStack createItem(String displayname, String id, Material type, boolean noMove, boolean noDrop, List<Integer> allowedSlots, Map<Enchantment, Integer> enchants,  String... lore){
        ItemStack item = new ItemStack(type);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.RESET + displayname);
        meta.setLore(List.of(lore));
        meta.addItemFlags(ItemFlag.values());
        enchants.forEach((enchantment, integer) -> meta.addEnchant(enchantment, integer, true));
        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        meta.setUnbreakable(true);
        if(noDrop) {
            pdc.set(NO_DROP, PersistentDataType.BOOLEAN, true);
        }
        if(noMove) {
            pdc.set(MOVE_BLACKLIST, PersistentDataType.BOOLEAN, true);
            pdc.set(ALLOWED_SLOTS, PersistentDataType.INTEGER_ARRAY, allowedSlots.stream().mapToInt(Integer::intValue).toArray());
        }
        pdc.set(NAMESPACE, PersistentDataType.STRING, id);

        item.setItemMeta(meta);
        itemRegistry.put(id, item);
        return item;
    }

    private static ItemStack createItem(String displayname, String id, Material type, String... lore){
        ItemStack item = new ItemStack(type);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.RESET + displayname);
        meta.setLore(List.of(lore));
        meta.addItemFlags(ItemFlag.values());
        
        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        meta.setUnbreakable(true);
        pdc.set(NAMESPACE, PersistentDataType.STRING, id);

        item.setItemMeta(meta);
        itemRegistry.put(id, item);
        return item;
    }

    private static ItemStack createArmor(String name, String id, Material type, Color color) {
        ItemStack item = new ItemStack(type);
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        meta.setColor(color);
        meta.setDisplayName(ChatColor.RESET + name);
        meta.addItemFlags(ItemFlag.values());
        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        meta.setUnbreakable(true);
        pdc.set(NO_DROP, PersistentDataType.BOOLEAN, true);
        pdc.set(MOVE_BLACKLIST, PersistentDataType.BOOLEAN, true);
        pdc.set(ALLOWED_SLOTS, PersistentDataType.INTEGER_ARRAY, Stream.of(36, 37, 38, 39).mapToInt(Integer::intValue).toArray());

        pdc.set(NAMESPACE, PersistentDataType.STRING, id);

        item.setItemMeta(meta);
        itemRegistry.put(id, item);
        return item;
    }

    private static ItemStack createPotion(String name, String id,  PotionType pot, int duration, int amplifier, String... lore) {
        PotionEffect effect = new PotionEffect(pot.getEffectType(), duration, amplifier-1, true, true, true);
        ItemStack item = new ItemStack(Material.POTION);
        PotionMeta meta = (PotionMeta) item.getItemMeta();

        meta.setColor(pot.getEffectType().getColor());

        meta.clearCustomEffects();
        meta.addCustomEffect(effect, true);

        meta.setLore(Arrays.stream(lore).toList());
        meta.setDisplayName(ChatColor.RESET + name);
        meta.addItemFlags(ItemFlag.values());
        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        meta.setUnbreakable(true);


        pdc.set(NAMESPACE, PersistentDataType.STRING, id);

        item.setItemMeta(meta);
        itemRegistry.put(id, item);
        return item;
    }

    public static ItemStack get(String id){
        if(id == null) return new ItemStack(Material.AIR);
        return itemRegistry.get(id);
    }

    public static Set<String> getItemIDs(){
        return itemRegistry.keySet();
    }

    public static ItemStack getTeamMapped(MappableItem mappableItem, Team team) {
        switch (mappableItem){
            case WOOL -> {
                switch (team.color()) {
                    case RED -> {
                        return Items.RED_WOOL;
                    }
                    case GOLD -> {
                        return Items.ORANGE_WOOL;
                    }
                    case YELLOW -> {
                        return Items.YELLOW_WOOL;
                    }
                    case GREEN -> {
                        return Items.LIME_WOOL;
                    }
                    case DARK_GREEN -> {
                        return Items.GREEN_WOOL;
                    }
                    case DARK_AQUA -> {
                        return Items.CYAN_WOOL;
                    }
                    case AQUA -> {
                        return Items.LIGHT_BLUE_WOOL;
                    }
                    case BLUE -> {
                        return Items.BLUE_WOOL;
                    }
                    case DARK_PURPLE -> {
                        return Items.PURPLE_WOOL;
                    }
                    case LIGHT_PURPLE -> {
                        return Items.PINK_WOOL;
                    }
                    case BLACK -> {
                        return Items.BLACK_WOOL;
                    }
                    case WHITE -> {
                        return Items.WHITE_WOOL;
                    }
                    case GRAY -> {
                        return Items.LIGHT_GREY_WOOL;
                    }
                    case DARK_GRAY -> {
                        return Items.GREY_WOOL;
                    }
                    case DARK_RED -> {
                        return Items.MAGENTA_WOOL;
                    }
                    default -> {
                        return null;
                    }
                }
            }
            case GLASS -> {
                switch (team.color()) {
                    case RED -> {
                        return Items.RED_BLAST_PROOF_GLASS;
                    }
                    case GOLD -> {
                        return Items.ORANGE_BLAST_PROOF_GLASS;
                    }
                    case YELLOW -> {
                        return Items.YELLOW_BLAST_PROOF_GLASS;
                    }
                    case GREEN -> {
                        return Items.LIME_BLAST_PROOF_GLASS;
                    }
                    case DARK_GREEN -> {
                        return Items.GREEN_BLAST_PROOF_GLASS;
                    }
                    case DARK_AQUA -> {
                        return Items.CYAN_BLAST_PROOF_GLASS;
                    }
                    case AQUA -> {
                        return Items.LIGHT_BLUE_BLAST_PROOF_GLASS;
                    }
                    case BLUE -> {
                        return Items.BLUE_BLAST_PROOF_GLASS;
                    }
                    case DARK_PURPLE -> {
                        return Items.PURPLE_BLAST_PROOF_GLASS;
                    }
                    case LIGHT_PURPLE -> {
                        return Items.PINK_BLAST_PROOF_GLASS;
                    }
                    case BLACK -> {
                        return Items.BLACK_BLAST_PROOF_GLASS;
                    }
                    case WHITE -> {
                        return Items.WHITE_BLAST_PROOF_GLASS;
                    }
                    case GRAY -> {
                        return Items.LIGHT_GREY_BLAST_PROOF_GLASS;
                    }
                    case DARK_GRAY -> {
                        return Items.GREY_BLAST_PROOF_GLASS;
                    }
                    case DARK_RED -> {
                        return Items.MAGENTA_BLAST_PROOF_GLASS;
                    }
                    default -> {
                        return null;
                    }
                }
            }
            case TERRACOTTA -> {
                switch (team.color()) {
                    case RED -> {
                        return Items.RED_TERRACOTTA;
                    }
                    case GOLD -> {
                        return Items.ORANGE_TERRACOTTA;
                    }
                    case YELLOW -> {
                        return Items.YELLOW_TERRACOTTA;
                    }
                    case GREEN -> {
                        return Items.LIME_TERRACOTTA;
                    }
                    case DARK_GREEN -> {
                        return Items.GREEN_TERRACOTTA;
                    }
                    case DARK_AQUA -> {
                        return Items.CYAN_TERRACOTTA;
                    }
                    case AQUA -> {
                        return Items.LIGHT_BLUE_TERRACOTTA;
                    }
                    case BLUE -> {
                        return Items.BLUE_TERRACOTTA;
                    }
                    case DARK_PURPLE -> {
                        return Items.PURPLE_TERRACOTTA;
                    }
                    case LIGHT_PURPLE -> {
                        return Items.PINK_TERRACOTTA;
                    }
                    case BLACK -> {
                        return Items.BLACK_TERRACOTTA;
                    }
                    case WHITE -> {
                        return Items.WHITE_TERRACOTTA;
                    }
                    case GRAY -> {
                        return Items.LIGHT_GREY_TERRACOTTA;
                    }
                    case DARK_GRAY -> {
                        return Items.GREY_TERRACOTTA;
                    }
                    case DARK_RED -> {
                        return Items.MAGENTA_TERRACOTTA;
                    }
                    default -> {
                        return null;
                    }
                }
            }
        }
        return null;
    }
}
