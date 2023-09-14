package ru.majesty.memory.game;

import com.google.common.collect.Lists;
import org.bukkit.entity.Player;
import ru.majesty.memory.Memory;

import java.util.List;

/**
 * Created by M4JESTY on 14.09.2023.
 */
public class Queue {

    private final List<Player> queue = Lists.newArrayList();

    public void queue(Player player) {
        if (queue.contains(player)) {
            removeFromQueue(player);
            player.sendMessage("&cВы покинули очередь на игру.");
            return;
        }

        queue.add(player);
        player.sendMessage("&aВы были добавлены в очередь на игру. Ожидаем второго игрока...");

        // Проверяем возможно ли запустить игру
        if (queue.size() == 2) {
            Player firstPlayer = queue.get(0);
            Player secondPlayer = queue.get(1);

            // Создаём новую игру
            Memory.getGameManager().create(firstPlayer, secondPlayer);

            // Очищаем очередь
            removeFromQueue(firstPlayer);
            removeFromQueue(secondPlayer);
        }
    }

    public void removeFromQueue(Player player) {
        queue.remove(player);
    }

    public boolean contains(Player player) {
        return queue.contains(player);
    }
}
