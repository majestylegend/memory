package ru.majesty.memory.user;

import org.jdbi.v3.core.mapper.ColumnMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by M4JESTY on 15.09.2023.
 */
public class UserDataMapper implements ColumnMapper<UserData> {

    @Override
    public UserData map(ResultSet rs, int columnNumber, StatementContext ctx) throws SQLException {
        return new UserData(
                rs.getInt("win"),
                rs.getInt("lose")
        );
    }
}