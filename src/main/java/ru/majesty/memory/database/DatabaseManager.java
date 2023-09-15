package ru.majesty.memory.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import ru.majesty.memory.user.UserDataMapper;

/**
 * Created by M4JESTY on 14.09.2023.
 */
public class DatabaseManager {
    private final int MAXIMUM_POOL_SIZE = 10;
    @Getter
    private Jdbi jdbi;

    public DatabaseManager() {
        // Настраиваем HikariConfig
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setUsername(System.getenv("DATABASE_USERNAME"));
        config.setPassword(System.getenv("DATABASE_PASSWORD"));
        config.setJdbcUrl(String.format(
                "jdbc:mysql://%s:%s/%s?useUnicode=true&characterEncoding=UTF-8&rewriteBatchedStatements=true&useSSL=false&serverTimezone=UTC",
                System.getenv("DATABASE_HOST"), System.getenv("DATABASE_PORT"), System.getenv("DATABASE_NAME")
        ));
        config.setMaximumPoolSize(MAXIMUM_POOL_SIZE);

        // Подключаем JDBI
        jdbi = Jdbi.create(new HikariDataSource(config));
        jdbi.installPlugin(new SqlObjectPlugin());
        jdbi.registerColumnMapper(new UserDataMapper());
    }
}
