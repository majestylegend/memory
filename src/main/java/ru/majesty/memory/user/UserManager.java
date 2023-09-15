package ru.majesty.memory.user;

import lombok.SneakyThrows;
import org.bukkit.entity.Player;
import ru.majesty.memory.Memory;
import ru.majesty.memory.dao.UserDao;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by M4JESTY on 14.09.2023.
 */
public class UserManager {
    private static final Map<String, User> users = new ConcurrentHashMap<>();
    private final UserDao userDao;

    public UserManager(Memory instance) {
        userDao = instance.getDatabaseManager().getJdbi().onDemand(UserDao.class);

        // Создаём таблицу, если её нет
        userDao.createTable();
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

    public User get(String name) {
        return users.get(name);
    }

    public User get(Player player) {
        return get(player.getName());
    }
}
