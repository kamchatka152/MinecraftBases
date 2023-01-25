package com.sloboiz.minecraftbases.items;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemManager
{
    public static ItemStack assaultLadder;
    public static ItemStack forwardOperatingBase;
    public static ItemStack horseEgg;

    public static ItemStack saddle;

    public static void init()
    {
        createAssaultLadder();
        createForwardOperatingBase();
        createHorseEgg();
        createSaddle();
    }

    private static void createAssaultLadder()
    {
        ItemStack item = new ItemStack(Material.LADDER, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("Assault ladder");
        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        assaultLadder = item;

        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("assault_ladder"), item);
        sr.shape("I I",
                 "ILI",
                 "I I");

        sr.setIngredient('I', Material.IRON_INGOT);
        sr.setIngredient('L', Material.LADDER);
        Bukkit.getServer().addRecipe(sr);
    }

    private static void createForwardOperatingBase()
    {
        ItemStack item = new ItemStack(Material.BLACK_BANNER, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("Forward operating base");
        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        forwardOperatingBase = item;

        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("forward_operating_base"), item);
        sr.shape("III",
                 "III",
                 " S ");

        sr.setIngredient('I', Material.IRON_INGOT);
        sr.setIngredient('S', Material.STICK);
        Bukkit.getServer().addRecipe(sr);
    }

    private static void createHorseEgg()
    {
        ItemStack item = new ItemStack(Material.HORSE_SPAWN_EGG, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("Horse egg");
        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        horseEgg = item;

        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("horse_egg"), item);
        sr.shape("AAA",
                 "WWW",
                 "CCC");

        sr.setIngredient('A', Material.APPLE);
        sr.setIngredient('W', Material.WHEAT);
        sr.setIngredient('C', Material.CARROT);
        Bukkit.getServer().addRecipe(sr);
    }

    private static void createSaddle()
    {
        ItemStack item = new ItemStack(Material.SADDLE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("Saddle");
        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        saddle = item;

        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("horse_saddle"), item);
        sr.shape("LLL",
                 "LLL",
                 " B ");

        sr.setIngredient('L', Material.LEATHER);
        sr.setIngredient('B', Material.IRON_BOOTS);
        Bukkit.getServer().addRecipe(sr);
    }
}
