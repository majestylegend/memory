package ru.majesty.memory.game;

import com.google.common.collect.Lists;
import org.bukkit.entity.Player;
import ru.majesty.memory.Memory;
import ru.majesty.memory.util.ChatUtil;

import java.util.List;

/**
 * Created by M4JESTY on 14.09.2023.
 */
public class Queue {
    private final List<Player> queue = Lists.newArrayList();

    public void queue(Player player) {
        if (contains(player)) {
            removeFromQueue(player);
            player.sendMessage(ChatUtil.colorize("&cВы покинули очередь на игру."));
            return;
        }

        queue.add(player);
        player.sendMessage(ChatUtil.colorize("&aВы были добавлены в очередь на игру. Ожидаем второго игрока..."));

        // Проверяем возможно ли запустить игру
        if (queue.size() == 2) {
            Player firstPlayer = queue.remove(0);
            Player secondPlayer = queue.remove(0);

            // Создаём новую игру
            Memory.getGameManager().create(firstPlayer, secondPlayer);
        }
    }

    public void removeFromQueue(Player player) {
        queue.remove(player);
    }

    public boolean contains(Player player) {
        return queue.contains(player);
    }
}
