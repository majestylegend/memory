package ru.majesty.memory.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;


public class Constants {
    public static final ItemStack COVER = new ItemStack(Material.BARRIER);
    public static final ItemStack BORDER = new ItemStack(Material.GLASS_PANE);
    public static final ItemStack[] DEFAULT_PAIRS =
            new ItemStack[]{
                    new ItemStack(Material.DIAMOND),
                    new ItemStack(Material.GOLD_INGOT),
                    new ItemStack(Material.IRON_INGOT),
                    new ItemStack(Material.LEATHER),
                    new ItemStack(Material.GLOWSTONE_DUST),
                    new ItemStack(Material.DIRT),
                    new ItemStack(Material.FLINT),
                    new ItemStack(Material.GUNPOWDER),
                    new ItemStack(Material.BONE),
                    new ItemStack(Material.ARROW),
                    new ItemStack(Material.EMERALD),
                    new ItemStack(Material.NETHER_STAR),
                    new ItemStack(Material.GHAST_TEAR),
                    new ItemStack(Material.SUGAR),
            };
}
