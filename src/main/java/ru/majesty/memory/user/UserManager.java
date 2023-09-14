package ru.majesty.memory.user;

import lombok.SneakyThrows;
import org.bukkit.entity.Player;
import ru.majesty.memory.Memory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserManager {

    private static Map<String, User> users = new ConcurrentHashMap<>();

    @SneakyThrows
    public UserManager() {
        StringBuilder tableConstructor = new StringBuilder();
        tableConstructor.append("CREATE TABLE IF NOT EXISTS ");
        tableConstructor.append("users");
        tableConstructor.append(" (");
        tableConstructor.append("name varchar(64) NOT NULL,");
        tableConstructor.append("win int(4) DEFAULT '0',");
        tableConstructor.append("lose int(4) DEFAULT '0',");
        tableConstructor.append(") ENGINE=InnoDB CHARACTER SET utf8 COLLATE utf8_general_ci");
        Memory.getConnection().prepareStatement(tableConstructor.toString()).execute();
    }
    @SneakyThrows
    public void load(Player player) {
        PreparedStatement preparedStatement = Memory.getConnection().prepareStatement("SELECT * FROM users WHERE name=" + player.getName());
        ResultSet result = preparedStatement.executeQuery();
        if (result.next()) {
            users.put(player.getName(),
                    new User(player,
                            result.getInt("win"),
                            result.getInt("lose")
                    )
            );
        } else {
            users.put(player.getName(), new User(player, 0, 0));
            Memory.getConnection().prepareStatement("INSERT INTO users VALUES (" + player.getName() + ", 0, 0)").execute();
        }
    }

    @SneakyThrows
    public void unload(User user) {
        users.remove(user.getHandle().getName());
        Memory.getConnection().prepareStatement("UPDATE users win=" + user.getWin() + ", lose=" + user.getLose() + " WHERE name=" + user.getHandle().getName()).execute();
    }

    public static User wrap(String name) {
        return users.get(name);
    }

    public static User wrap(Player player) {
        return wrap(player.getName());
    }
}
