package ru.majesty.memory.util;

import lombok.experimental.UtilityClass;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Created by M4JESTY on 14.09.2023.
 */
@UtilityClass
public class Constants {

    public final String DEFAULT_PREFIX = "Игра";
    public final ItemStack COVER = new ItemStack(Material.BARRIER);
    public final ItemStack BORDER = new ItemStack(Material.GLASS_PANE);
    public final ItemStack[] DEFAULT_PAIRS =
            new ItemStack[] {
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
