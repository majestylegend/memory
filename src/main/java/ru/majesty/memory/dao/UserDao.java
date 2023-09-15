package ru.majesty.memory.dao;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import ru.majesty.memory.user.User;
import ru.majesty.memory.user.UserData;

/**
 * Created by M4JESTY on 15.09.2023.
 */
public interface UserDao {

    @SqlUpdate("CREATE TABLE IF NOT EXISTS memory_users (player_name TEXT, win INT, lose INT)")
    void createTable();

    @SqlQuery("SELECT win, lose FROM memory_users WHERE player_name = :name")
    UserData select(@Bind String name);

    @SqlUpdate("INSERT INTO memory_users (player_name, win, lose) VALUES (:name, :win, :lose)")
    void insert(@BindBean String name, @BindBean UserData userData);

    @SqlUpdate("UPDATE memory_users SET win = :win, lose = :lose WHERE player_name = :name")
    void update(@BindBean User user);
}