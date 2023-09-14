package ru.majesty.memory.game;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.inventory.ItemStack;

/**
 * Created by M4JESTY on 14.09.2023.
 */
@AllArgsConstructor
@Getter
@Setter
public class Card {

    private int slot;
    private ItemStack item;

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
