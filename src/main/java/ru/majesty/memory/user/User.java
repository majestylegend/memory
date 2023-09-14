package ru.majesty.memory.user;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import ru.majesty.memory.game.Card;

/**
 * Created by M4JESTY on 14.09.2023.
 */
@Getter
@Setter
public class User {

    private Player handle;

    private Card lastCard;
    private int score;

    public User(Player player) {
        this.handle = player;
    }

    public boolean hasCard() {
        return lastCard != null;
    }

    public void addScore() {
        score++;
    }
}
