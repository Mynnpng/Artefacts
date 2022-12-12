package me.mynnpng.powerfulswords;

import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting;
import io.github.thebusybiscuit.slimefun4.api.items.settings.IntRangeSetting;
import io.github.thebusybiscuit.slimefun4.core.attributes.Radioactive;
import io.github.thebusybiscuit.slimefun4.core.attributes.Radioactivity;
import io.github.thebusybiscuit.slimefun4.core.attributes.WitherProof;
import io.github.thebusybiscuit.slimefun4.core.handlers.EntityKillHandler;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import jdk.jfr.Category;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import java.util.Locale;

public class PowerfulSwords extends JavaPlugin implements SlimefunAddon {

    public static SlimefunItemStack soulSword; //мечь души
    public static SlimefunItemStack omegaSword;   //меч омеги
    public static SlimefunItemStack astraSword;   //меч астры

    public static SlimefunItemStack  xpSword;    //меч икс

    public static SlimefunItemStack  atlantSword; // меч атланта

    public static SlimefunItemStack  ariesSword;  //меч ариеса

    public static SlimefunItemStack powerfulRune;

    public static SlimefunItemStack demonHeart;

    // public static SlimefunItemStack  legendSword;   //будующии планы

    // public static SlimefunItemStack  dragonSword;   //будующии планы




    @Override
    public void onEnable() {
        // Read something from your config.yml
        Config cfg = new Config(this); //конфиг

        if (cfg.getBoolean("options.auto-update")) {
            // авто апдейт
        }


        NamespacedKey swordsCategoryId = new NamespacedKey(this, "swords_category"); //ID категории
        CustomItemStack swordsCategoryItem = new CustomItemStack(SlimefunItems.BLADE_OF_VAMPIRES, "&4Powerful Swords"); //Название, описание категории
        ItemGroup swordGod = new ItemGroup(swordsCategoryId, swordsCategoryItem); //регистрация группы


        soulSword = new SlimefunItemStack("SOULS", Material.IRON_SWORD, "&6Souls Sword", "", "&fСлужит для добычи душ для крафта остальный мечей"); //Меч душ
        omegaSword = new SlimefunItemStack("OMEGA", Material.WOODEN_SWORD, "&9Omega Sword", "", "&fРапира давно утерянной цивилизации"); //Меч омеги
        astraSword = new SlimefunItemStack("ASTRA", Material.WOODEN_SWORD, "&3Astra Sword", "", "&fМечь в котором хранится история?"); //Меч астры
        xpSword = new SlimefunItemStack("XP", Material.WOODEN_SWORD, "&cX Sword", "", "&f Меч который даст вам мудрость"); //Икспи меч
        atlantSword = new SlimefunItemStack("ATLANT", Material.IRON_SWORD, "&bAtlant Sword", "", "&f "); //Атлант меч
        ariesSword = new SlimefunItemStack("ARIES", Material.DIAMOND_SWORD, "&5Aries Sword", "", "&fОн когда-нибудь выйдет?"); //Меч Ариеса
        powerfulRune = new SlimefunItemStack("POWERRUNE", SlimefunItems.LIGHTNING_RUNE, "&ePowerfulRune", "Самое мощное, что существует во вселенной(наверное)"); //Руна мощи
        demonHeart = new SlimefunItemStack("DEMONHEART", Material.SPIDER_EYE, "&4Demon Heart", "Сердце демона, оно истекает кровью"); //Сердце демона


        omegaSword.addUnsafeEnchantment(Enchantment.LOOT_BONUS_MOBS, 6); //чары омега меча
        astraSword.addUnsafeEnchantment(Enchantment.DURABILITY,10); //чары астра меча
        xpSword.addUnsafeEnchantment(Enchantment.DURABILITY,6); //чары икспи меча
        atlantSword.addUnsafeEnchantment(Enchantment.DURABILITY,6); //чары атлант меча
        ariesSword.addUnsafeEnchantment(Enchantment.DURABILITY,10);
        ariesSword.addUnsafeEnchantment(Enchantment.VANISHING_CURSE,1000);
        //чары меча ариес
        soulSword.addUnsafeEnchantment(Enchantment.LOOT_BONUS_MOBS, 5); //чары меча душ


        //рецепт меча душ
        ItemStack[] soulsSwordRecipe = {
                SlimefunItems.CARBON, SlimefunItems.CARBON, SlimefunItems.CARBON,
                SlimefunItems.CARBON, SlimefunItems.CARBON, SlimefunItems.CARBON,
                SlimefunItems.CARBON, SlimefunItems.CARBON, SlimefunItems.CARBON
        };
        //рецепт меча омеги
        ItemStack[] omegaSwordRecipe = {
                powerfulRune, SlimefunItems.CARBON, powerfulRune,
                SlimefunItems.CARBON, SlimefunItems.CARBON, SlimefunItems.CARBON,
                powerfulRune, SlimefunItems.CARBON, powerfulRune
        };
        //рецепт меча икспи
        ItemStack[] xpSwordRecipe  = {
                SlimefunItems.RAW_CARBONADO, new ItemStack(Material.NETHER_STAR), SlimefunItems.RAW_CARBONADO,
                SlimefunItems.BOOSTED_URANIUM, new ItemStack(Material.NETHER_STAR), SlimefunItems.BOOSTED_URANIUM,
                null , SlimefunItems.NECROTIC_SKULL, null
        };
        //рецепт меча атланта
        ItemStack[] atlantSwordRecipe  = {
                SlimefunItems.RAW_CARBONADO, new ItemStack(Material.NETHER_STAR), SlimefunItems.RAW_CARBONADO,
                SlimefunItems.BOOSTED_URANIUM, new ItemStack(Material.NETHER_STAR), SlimefunItems.BOOSTED_URANIUM,
                null , SlimefunItems.NECROTIC_SKULL, null
        };
        //рецепт ариес меча
        ItemStack[] ariesSwordRecipe  = {
                SlimefunItems.RAW_CARBONADO, new ItemStack(Material.NETHER_STAR), SlimefunItems.RAW_CARBONADO,
                SlimefunItems.BOOSTED_URANIUM, new ItemStack(Material.NETHER_STAR), SlimefunItems.BOOSTED_URANIUM,
                null , SlimefunItems.NECROTIC_SKULL, null
        };
        //руна ощи
        ItemStack[] powerfulRuneRecipe = {
                new ItemStack(Material.EMERALD), new ItemStack(Material.REDSTONE), null,
                null, null, null,
                null, null, null
        };
        //рецепт меча астры
        ItemStack[] astraSwordRecipe = {
                SlimefunItems.CARBON, SlimefunItems.CARBON, SlimefunItems.CARBON,
                SlimefunItems.CARBON, SlimefunItems.CARBON, SlimefunItems.CARBON,
                SlimefunItems.CARBON, SlimefunItems.CARBON, SlimefunItems.CARBON
        };
        //рецепт сердца демона
        ItemStack[] demonHeartRecipe = {
                powerfulRune, powerfulRune, powerfulRune,
                SlimefunItems.NECROTIC_SKULL, new ItemStack(Material.FERMENTED_SPIDER_EYE), SlimefunItems.NECROTIC_SKULL,
                powerfulRune, SlimefunItems.NECROTIC_SKULL, powerfulRune
        };


        //меч душ
        SlimefunItem soulSwordItem = new SlimefunItem(swordGod, soulSword, RecipeType.ENHANCED_CRAFTING_TABLE, soulsSwordRecipe); // хайд
        soulSwordItem.register(this);
        //меч омеги
        SlimefunItem omegaSwordItem = new SlimefunItem(swordGod, omegaSword, RecipeType.ENHANCED_CRAFTING_TABLE, omegaSwordRecipe); // боб
        omegaSwordItem.register(this);
        // икспи меч
        SlimefunItem xpSwordItem = new SlimefunItem(swordGod, xpSword, RecipeType.ENHANCED_CRAFTING_TABLE, xpSwordRecipe);
        xpSwordItem.register(this);
        //меч атлант
        SlimefunItem atlantSwordItem = new SlimefunItem(swordGod, atlantSword, RecipeType.ENHANCED_CRAFTING_TABLE, atlantSwordRecipe);
        atlantSwordItem.register(this);
        //меч ариес
        SlimefunItem ariesSwordItem = new SlimefunItem(swordGod, ariesSword, RecipeType.ENHANCED_CRAFTING_TABLE, ariesSwordRecipe);
        ariesSwordItem.register(this);
        //меч астры
        SlimefunItem astraSwordItem = new SlimefunItem(swordGod, astraSword, RecipeType.ENHANCED_CRAFTING_TABLE, astraSwordRecipe);
        astraSwordItem.register(this);
        //руна силы
        SlimefunItem powerfulRuneItem = new SlimefunItem(swordGod, powerfulRune, RecipeType.ANCIENT_ALTAR, powerfulRuneRecipe);
        powerfulRuneItem.register(this);
        //сердце демона
        SlimefunItem demonHeartItem = new SlimefunItem(swordGod, demonHeart, RecipeType.ANCIENT_ALTAR, demonHeartRecipe);
        demonHeartItem.register(this);


    }

    @Override
    public void onDisable() {
        // Logic for disabling the plugin...
    }

    @Override
    public String getBugTrackerURL() {
        // You can return a link to your Bug Tracker instead of null here
        return null;
    }

    @Override
    public JavaPlugin getJavaPlugin() {
        /*
         * You will need to return a reference to your Plugin here.
         * If you are using your main class for this, simply return "this".
         */
        return this;
    }


}
