package thesis.service;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import thesis.models.Column;
import thesis.models.ColumnType;
import thesis.models.TableDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Service
public class DbUtils {
    @Value("${datasource.url}")
    private String url;
    @Value("${datasource.username}")
    private String username;
    @Value("${datasource.password}")
    private String password;

    private Connection connection = null;

    public DSLContext dslContext() {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return DSL.using(connection, SQLDialect.MYSQL_8_0);
    }

    public TableDto getTableDto(Table<?> table) {

        TableDto tableDto = new TableDto();
        tableDto.setName(table.getName());

        TableField pkField = table.getPrimaryKey().getFields().get(0);
        String pkName = pkField.getName();
        String pkType = pkField.getType().getSimpleName().toUpperCase();

        tableDto.setColumn(new Column(pkName, ColumnType.valueOf(pkType)).setPrimaryKey(true));

        table.fieldStream().filter(field -> !field.getName().equals(pkName)).forEach(field -> {
            String columnName = field.getName();
            String columnType = field.getType().getSimpleName().toUpperCase();

            tableDto.setColumn(new Column(columnName, ColumnType.valueOf(columnType)));
        });
        return tableDto;
    }

    public void closeJdbcResource() {
        if (connection != null) try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
