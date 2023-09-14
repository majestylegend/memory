package ru.majesty.memory.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import ru.majesty.memory.Memory;
import ru.majesty.memory.user.UserManager;

/**
 * Created by M4JESTY on 14.09.2023.
 */
public class PlayerListener implements Listener {

    @EventHandler
    public void on(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Memory.getUserManager().load(player);
    }
    @EventHandler
    public void on(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        // Проверяем есть ли игрок в очереди
        if (Memory.getQueue().contains(player)) {
            Memory.getQueue().removeFromQueue(player);
        }

        Memory.getUserManager().unload(UserManager.wrap(player));
    }

    @EventHandler
    public void on(PlayerKickEvent event) {
        Player player = event.getPlayer();

        // Проверяем есть ли игрок в очереди
        if (Memory.getQueue().contains(player)) {
            Memory.getQueue().removeFromQueue(player);
        }

        Memory.getUserManager().unload(UserManager.wrap(player));
    }
}
