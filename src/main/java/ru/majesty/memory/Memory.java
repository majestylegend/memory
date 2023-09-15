package ru.majesty.memory;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import ru.majesty.memory.command.MemoryCommand;
import ru.majesty.memory.database.DatabaseManager;
import ru.majesty.memory.game.GameManager;
import ru.majesty.memory.game.QueueManager;
import ru.majesty.memory.listener.GameListener;
import ru.majesty.memory.listener.PlayerListener;
import ru.majesty.memory.user.UserManager;

/**
 * Created by M4JESTY on 14.09.2023.
 */
public class Memory extends JavaPlugin {

    @Getter
    private static Memory instance;
    @Getter
    private QueueManager queueManager;
    @Getter
    private GameManager gameManager;
    @Getter
    private DatabaseManager databaseManager;
    @Getter
    private UserManager userManager;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        // Инициализируем менеджеры
        databaseManager = new DatabaseManager(getConfig());
        userManager = new UserManager(this);
        queueManager = new QueueManager(this);
        gameManager = new GameManager(this);

        registerListeners(
                new PlayerListener(this),
                new GameListener()
        );

        tryRegisterCommand("memory", new MemoryCommand(this));
    }

    private void registerListeners(Listener... listeners) {
        for (Listener listener : listeners) {
            Bukkit.getPluginManager().registerEvents(listener, this);
        }
    }

    private void tryRegisterCommand(String cmdKey, CommandExecutor cmd) {
        PluginCommand pluginCmd = instance.getCommand(cmdKey);
        if (pluginCmd == null) {
            instance.getLogger().info("Failed to load command " + cmdKey + ". Please, add it into your plugin.yml.");
        } else {
            pluginCmd.setExecutor(cmd);
            if (cmd instanceof TabCompleter)
                pluginCmd.setTabCompleter((TabCompleter) cmd);
        }
    }
}
