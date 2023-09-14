package ru.majesty.memory.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseManager {
    private HikariConfig config;
    private HikariDataSource dataSource;

    public DatabaseManager() {
        config = new HikariConfig();
        config.setJdbcUrl( "127.0.0.1" );
        config.setUsername( "root" );
        config.setPassword( "pass" );
        config.addDataSourceProperty( "cachePrepStmts" , "true" );
        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
        dataSource = new HikariDataSource( config );
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
