package adaptor.service;

import adaptor.models.Column;
import adaptor.models.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import static adaptor.utils.Filters.isPrimaryKey;

@Service
public class DatabaseExe implements DatabaseService {

    @Autowired
    private DbUtils dbUtils;

    @Override
    public ResponseEntity<?> createTable(Table table) {
        String tableName = table.getName();

        Optional<Column> optionalOfColumn = table.getColumns().stream().filter(isPrimaryKey).findAny();
        if (optionalOfColumn.isPresent()) {
            Column primaryKey = optionalOfColumn.get();
            String primaryKeyName = primaryKey.getName();

            try {
                final Statement statement = dbUtils.open().createStatement();

                statement.executeUpdate("CREATE TABLE " + tableName + " (" + primaryKeyName + primaryKey.getType().getSql()
                        + ",PRIMARY KEY(" + primaryKeyName + ") )");

                table.getColumns().stream().filter(isPrimaryKey.negate())
                        .forEach(o -> {
                            String sql = dbUtils.getInsertColumnQuery(tableName, o.getName(), o.getType());
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
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>("Table " + tableName + " has no primary key.", HttpStatus.BAD_REQUEST);
    }
}
