package ru.majesty.memory.game;

import org.bukkit.inventory.ItemStack;

/**
 * Created by M4JESTY on 14.09.2023.
 */
public class Card {

    private int slot;
    private ItemStack item;

    public Card(int slot, ItemStack item) {
        this.slot = slot;
        this.item = item;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public ItemStack getItem() {
        return item;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Card) {
            Card card = (Card) obj;
            ItemStack cardItem = card.getItem();

            return cardItem.getType() == item.getType()
                    && cardItem.getDurability() == item.getDurability();
        }

        return false;
    }
}
