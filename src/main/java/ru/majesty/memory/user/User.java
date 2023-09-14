package ru.majesty.memory.user;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import ru.majesty.memory.Memory;
import ru.majesty.memory.game.Card;

/**
 * Created by M4JESTY on 14.09.2023.
 */
@Getter
@Setter
public class User {

    private final Player handle;
    protected int win;
    protected int lose;
    private Card lastCard;
    private int score;

    public User(Player player, int win, int lose) {
        this.handle = player;
        this.win = win;
        this.lose = lose;
    }

    public boolean hasCard() {
        return lastCard != null;
    }

    public void addScore() {
        score++;
    }

    public void addWin() {
        win++;
    }

    public void addLose() {
        lose++;
    }

    public static User wrap(Player player) {
        return wrap(player.getName());
    }
    public static User wrap(String name) {
        return UserManager.wrap(name);
    }
}
