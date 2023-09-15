package ru.majesty.memory.user;

import lombok.SneakyThrows;
import org.bukkit.entity.Player;
import ru.majesty.memory.Memory;
import ru.majesty.memory.dao.UserDao;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserManager {
    private static final Map<String, User> users = new ConcurrentHashMap<>();
    private final UserDao userDao;

    public UserManager(Memory instance) {
        userDao = instance.getDatabaseManager().getJdbi().onDemand(UserDao.class);

        // Создаём таблицу, если её нет
        userDao.createTable();
    }

    public static User wrap(String name) {
        return users.get(name);
    }

    public static User wrap(Player player) {
        return wrap(player.getName());
    }

    @SneakyThrows
    public void load(String name) {
        UserData data = new UserData(0, 0);
        UserData newData = userDao.select(name);
        if (newData != null) {
            users.put(name, new User(name, newData));
            return;
        }

        userDao.insert(name, data);
        users.put(name, new User(name, data));
    }

    @SneakyThrows
    public void unload(User user) {
        users.remove(user.getHandle().getName());
        userDao.update(user);
    }
}
