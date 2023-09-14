package ru.majesty.memory.util;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by M4JESTY on 14.09.2023.
 */
public class ItemBuilder {

    private String name;
    private int amount;
    private Material type;
    private int subID;
    private List<String> description;
    private HashMap<Enchantment, Integer> enchantments;

    public ItemBuilder() {
        this.amount = 1;
        this.subID = 0;
        this.description = new ArrayList<>();
        this.enchantments = new HashMap<>();
    }

    public ItemStack getItem() {
        ItemStack item = new ItemStack(type, amount, (short) subID);
        ItemMeta meta = item.getItemMeta();
        if (name != null) meta.setDisplayName(name);
        if (description.size() != 0) meta.setLore(description);
        if (enchantments.size() != 0) {
            for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                Enchantment key = entry.getKey();
                Integer value = entry.getValue();
                meta.addEnchant(key, (value + 1), true);
            }
        }

        item.setItemMeta(meta);
        return item;
    }

    public ItemBuilder enchant(Enchantment enchantment, int level) {
        enchantments.put(enchantment, level);
        return this;
    }

    public ItemBuilder name(String name) {
        this.name = "§r" + name;
        return this;
    }

    public ItemBuilder description(String description) {
        this.description.add("§r" + description);
        return this;
    }

    public ItemBuilder amount(int amount) {
        this.amount = amount;
        return this;
    }

    public ItemBuilder type(Material type) {
        this.type = type;
        return this;
    }

    public ItemBuilder subID(int subID) {
        this.subID = subID;
        return this;
    }
}
