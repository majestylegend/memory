package ru.majesty.memory.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;


public class Constants {
    public static final ItemStack COVER = new ItemBuilder().name("?").type(Material.STAINED_GLASS_PANE).getItem();
    public static final ItemStack BORDER = new ItemBuilder().name("").type(Material.STAINED_GLASS_PANE).subID(15).getItem();
    public static final ItemStack[] DEFAULT_PAIRS =
        new ItemStack[]{
                new ItemBuilder().type(Material.INK_SACK).subID(13).getItem(),
                new ItemBuilder().type(Material.INK_SACK).subID(8).getItem(),
                new ItemBuilder().type(Material.INK_SACK).subID(1).getItem(),
                new ItemBuilder().type(Material.SULPHUR).getItem(),
                new ItemBuilder().type(Material.GLOWSTONE_DUST).getItem(),
                new ItemBuilder().type(Material.INK_SACK).subID(2).getItem(),
                new ItemBuilder().type(Material.FLINT).getItem(),
                new ItemBuilder().type(Material.INK_SACK).subID(10).getItem(),
                new ItemBuilder().type(Material.INK_SACK).subID(12).getItem(),
                new ItemBuilder().type(Material.INK_SACK).subID(14).getItem(),
                new ItemBuilder().type(Material.INK_SACK).subID(9).getItem(),
                new ItemBuilder().type(Material.INK_SACK).subID(5).getItem(),
                new ItemBuilder().type(Material.INK_SACK).subID(4).getItem(),
                new ItemBuilder().type(Material.SUGAR).getItem(),
        };
}
