package ru.majesty.memory.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;


public class Constants {
    public static final ItemStack COVER = new ItemBuilder().name("?").type(Material.BARRIER).getItem();
    public static final ItemStack BORDER = new ItemBuilder().name("").type(Material.GLASS_PANE).getItem();
    public static final ItemStack[] DEFAULT_PAIRS =
        new ItemStack[]{
                new ItemBuilder().type(Material.DIAMOND).getItem(),
                new ItemBuilder().type(Material.GOLD_INGOT).getItem(),
                new ItemBuilder().type(Material.IRON_INGOT).getItem(),
                new ItemBuilder().type(Material.LEATHER).getItem(),
                new ItemBuilder().type(Material.GLOWSTONE_DUST).getItem(),
                new ItemBuilder().type(Material.DIRT).getItem(),
                new ItemBuilder().type(Material.FLINT).getItem(),
                new ItemBuilder().type(Material.GUNPOWDER).getItem(),
                new ItemBuilder().type(Material.BONE).getItem(),
                new ItemBuilder().type(Material.ARROW).getItem(),
                new ItemBuilder().type(Material.EMERALD).getItem(),
                new ItemBuilder().type(Material.NETHER_STAR).getItem(),
                new ItemBuilder().type(Material.GHAST_TEAR).getItem(),
                new ItemBuilder().type(Material.SUGAR).getItem(),
        };
}
