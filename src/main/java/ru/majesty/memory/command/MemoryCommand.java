package ru.majesty.memory.command;

import lombok.RequiredArgsConstructor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.majesty.memory.Memory;
import ru.majesty.memory.user.UserManager;

/**
 * Created by M4JESTY on 14.09.2023.
 */
@RequiredArgsConstructor
public class MemoryCommand implements CommandExecutor {
    private final Memory instance;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            instance.getQueueManager().queue(UserManager.wrap((Player) sender));
        }
        return true;
    }
}
