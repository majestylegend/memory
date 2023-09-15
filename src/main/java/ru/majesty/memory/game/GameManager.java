package ru.majesty.memory.game;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import ru.majesty.memory.Memory;

import java.util.List;

/**
 * Created by M4JESTY on 14.09.2023.
 */
@RequiredArgsConstructor
public class GameManager {
    private final Memory instance;
    private List<Game> games = Lists.newArrayList();

    public void create(Player firstPlayer, Player secondPlayer) {
        Game game = new Game(instance, firstPlayer, secondPlayer);
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
