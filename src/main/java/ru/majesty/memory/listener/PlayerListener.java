package ru.majesty.memory.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import ru.majesty.memory.Memory;

/**
 * Created by M4JESTY on 14.09.2023.
 */
public class PlayerListener implements Listener {

    @EventHandler
    public void on(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        // Проверяем есть ли игрок в очереди
        if (Memory.getQueue().contains(player)) {
            Memory.getQueue().removeFromQueue(player);
        }
    }
}
