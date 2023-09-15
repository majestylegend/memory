package ru.majesty.memory.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import ru.majesty.memory.user.UserDataMapper;

/**
 * Created by M4JESTY on 14.09.2023.
 */
public class DatabaseManager {
    @Getter
    private Jdbi jdbi;

    public DatabaseManager(FileConfiguration configuration) {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setUsername(configuration.getString("username"));
        config.setPassword(configuration.getString("password"));
        config.setJdbcUrl(String.format(
                "jdbc:mysql://%s:%s/%s?useUnicode=true&characterEncoding=UTF-8&rewriteBatchedStatements=true&useSSL=false&serverTimezone=UTC",
                configuration.getString("host"), configuration.getString("port"), configuration.getString("database")
        ));
        config.setMaximumPoolSize(10);

        jdbi = Jdbi.create(new HikariDataSource(config));
        jdbi.installPlugin(new SqlObjectPlugin());
        jdbi.registerColumnMapper(new UserDataMapper());
    }
}
