package ru.majesty.memory.game;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import ru.majesty.memory.Memory;
import ru.majesty.memory.user.User;

import java.util.List;

/**
 * Created by M4JESTY on 14.09.2023.
 */
@RequiredArgsConstructor
public class GameManager {
    private final Memory instance;
    private final List<Game> games = Lists.newArrayList();

    public void create(User firstPlayer, User secondPlayer) {
        Game game = new Game(instance, firstPlayer, secondPlayer);
        games.add(game);
    }

    public void end(Game game) {
        games.remove(game);
    }
}
