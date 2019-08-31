package adaptor.service;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.Table;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DbUtils {
    @Value("${datasource.url}")
    private String url;
    @Value("${datasource.username}")
    private String username;
    @Value("${datasource.password}")
    private String password;

    private Connection connection = null;

    private Table<?> table;

    public DSLContext dslContext() {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return DSL.using(connection, SQLDialect.MYSQL_8_0);
    }

    public boolean requestedTaleExists(String name) {
        List<Table> tables = dslContext()
                .meta().getTables().stream()
                .filter(table -> table.getName().equals(name)).collect(Collectors.toList());
        if (tables.size() == 1) {
            table = tables.get(0);
            return true;
        } else return false;
    }

    public Table getRequestedTable() {
        return this.table;
    }

    public void closeJdbcResource() {
        if (connection != null) try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
