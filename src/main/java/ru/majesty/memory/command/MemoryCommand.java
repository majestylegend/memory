package ru.majesty.memory.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.majesty.memory.Memory;

/**
 * Created by M4JESTY on 14.09.2023.
 */
public class MemoryCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Memory.getQueue().queue((Player) sender);
        }
        return true;
    }
}
