package adaptor.service;

import adaptor.models.Column;
import adaptor.models.Table;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

@Service
public class DatabaseExe implements DatabaseService {

    @Autowired
    private DbUtils dbUtils;

    @Override
    public ResponseEntity<?> createTable(Table table) {
        String tableName = table.getName();

        Optional<Column> optionalOfColumn = table.getColumns().stream().filter(Column::isPrimaryKey).findAny();
        if (optionalOfColumn.isPresent()) {
            Column primaryKey = optionalOfColumn.get();
            String primaryKeyName = primaryKey.getName();

            try {
                final Statement statement = dbUtils.open().createStatement();

                statement.executeUpdate("CREATE TABLE " + tableName + " (" + primaryKeyName + primaryKey.getType().getSql() + " AUTO_INCREMENT"
                        + ",PRIMARY KEY(" + primaryKeyName + ") )");

                table.getColumns().stream().filter(column -> !column.isPrimaryKey())
                        .forEach(o -> {
                            String sql = dbUtils.generateInsertColumnQuery(tableName, o.getName(), o.getType());
                            try {
                                statement.executeUpdate(sql);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        });
            } catch (SQLException e) {
                e.printStackTrace();
            }
            dbUtils.close();
            return new ResponseEntity<>(table, HttpStatus.OK);
        }
        return new ResponseEntity<>("Table " + tableName + " has no primary key.", HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<?> getInfo(String name) {

        try (Connection connection = dbUtils.open()) {

            DSL.using(connection, SQLDialect.MYSQL_8_0)
                    .meta().getTables().stream()
                    .filter(table -> table.getName().equals(name))
                    .forEach(table -> {

                        System.out.println("----- SIZE ----- " + table.getPrimaryKey().getFields().size());

                        table.getPrimaryKey().getFields().forEach(tableField ->
                        {
                            System.out.println("----- PRIMARY KEY NAME ----- " + tableField.getName());
                            System.out.println("----- PRIMARY KEY VALUE ----- " + tableField.getType().getSimpleName());
                        });

                        table.fieldStream().forEach(field -> {
                            System.out.println("----- NAME ----- " + field.getName());
                            System.out.println("----- TYPE ----- " + field.getType().getSimpleName().toUpperCase());
                        });
                    });

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
