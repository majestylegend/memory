package ru.majesty.memory.game;

import com.google.common.collect.Lists;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Created by M4JESTY on 14.09.2023.
 */
public class GameManager {

    private final List<Game> games = Lists.newArrayList();

    public void create(Player firstPlayer, Player secondPlayer) {
        Game game = new Game(firstPlayer, secondPlayer);
        games.add(game);
    }

    public void end(Game game) {
        games.remove(game);
    }

    public Game getGame(Player player) {
        for (Game game : games) {
            if (game.getPlayers().contains(player))
                return game;
        }

        return null;
    }
}
