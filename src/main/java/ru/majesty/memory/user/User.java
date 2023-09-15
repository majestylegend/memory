package ru.majesty.memory.user;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import ru.majesty.memory.game.Card;
import ru.majesty.memory.util.ChatUtil;

/**
 * Created by M4JESTY on 14.09.2023.
 */
@Getter
@Setter
public class User {
    private final String name;
    private final Player handle;
    private int win;
    private int lose;
    private Card lastCard;
    private int score;

    public User(String name, UserData data) {
        this.name = name;
        this.handle = Bukkit.getPlayerExact(name);
        this.win = data.getWin();
        this.lose = data.getLose();
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

    public void sendMessage(String message, Object... args) {
        handle.sendMessage(ChatUtil.prefixed(message, args));
    }
}
