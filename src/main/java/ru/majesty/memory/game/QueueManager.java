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
public class QueueManager {
    private final Memory instance;
    private final List<User> queue = Lists.newArrayList();

    public void queue(User user) {
        if (contains(user)) {
            removeFromQueue(user);
            user.sendMessage("&cВы покинули очередь на игру.");
            return;
        }

        queue.add(user);
        user.sendMessage("&aВы были добавлены в очередь на игру. Ожидаем второго игрока...");

        // Проверяем возможно ли запустить игру
        if (queue.size() == 2) {
            User firstPlayer = queue.remove(0);
            User secondPlayer = queue.remove(0);

            // Создаём новую игру
            instance.getGameManager().create(firstPlayer, secondPlayer);
        }
    }

    public void removeFromQueue(User user) {
        queue.remove(user);
    }

    public boolean contains(User user) {
        return queue.contains(user);
    }
}
