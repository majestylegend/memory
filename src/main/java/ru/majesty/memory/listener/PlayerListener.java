package ru.majesty.memory.listener;

import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import ru.majesty.memory.Memory;
import ru.majesty.memory.user.User;
import ru.majesty.memory.user.UserManager;

/**
 * Created by M4JESTY on 14.09.2023.
 */
@RequiredArgsConstructor
public class PlayerListener implements Listener {
    private final Memory instance;

    @EventHandler
    public void on(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        // Загружаем данные игрока
        instance.getUserManager().load(player.getName());
    }

    @EventHandler
    public void on(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        User user = UserManager.wrap(player);

        // Проверяем есть ли игрок в очереди и удаляем
        if (instance.getQueueManager().contains(user)) {
            instance.getQueueManager().removeFromQueue(user);
        }

        // Сохраняем и удаляем данные игрока
        instance.getUserManager().unload(user);
    }

    @EventHandler
    public void on(PlayerKickEvent event) {
        Player player = event.getPlayer();
        User user = UserManager.wrap(player);

        // Проверяем есть ли игрок в очереди и удаляем
        if (instance.getQueueManager().contains(user)) {
            instance.getQueueManager().removeFromQueue(user);
        }

        // Сохраняем и удаляем данные игрока
        instance.getUserManager().unload(user);
    }
}
