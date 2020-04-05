package thesis.service;

import org.jooq.DSLContext;
import org.jooq.Table;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import thesis.models.Column;
import thesis.models.ColumnType;
import thesis.models.TableDto;
import thesis.utils.Constants;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DatabaseExe implements DatabaseService {

    @Autowired
    private DbUtils dbUtils;

    @Override
    public ResponseEntity<?> createTable(TableDto table) {
        DSLContext dslContext = dbUtils.dslContext();
        String name = table.getName();

        List<Column> pkColumns = table.getColumns().stream().filter(Column::isPrimaryKey).collect(Collectors.toList());

        if (pkColumns.size() == 1) {
            Column pkColumn = pkColumns.get(0);
            String pkName = pkColumn.getName();
            ColumnType pkType = pkColumn.getType();

            dslContext.createTable(name)
                    .column(pkName, pkType.getDataType().identity(true))
                    .constraint(DSL.primaryKey(pkName))
                    .execute();

            table.getColumns().stream().filter(column -> !column.isPrimaryKey())
                    .forEach(column -> dslContext.alterTable(name)
                            .addColumn(column.getName(), column.getType().getDataType())
                            .execute());

            dbUtils.closeJdbcResource();
            return new ResponseEntity<>(table, HttpStatus.CREATED);

        } else if (pkColumns.size() == 0) {
            return new ResponseEntity<>("NO PRIMARY_KEY defined for table " + name, HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>("Multiple PRIMARY_KEYS in a single table.", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> getInfo(String name) {

        List<Table> tables = dbUtils.dslContext()
                .meta().getTables().stream()
                .filter(table -> table.getName().equals(name)).collect(Collectors.toList());

        if (tables.size() == 1) {
            Table table = tables.get(0);
            TableDto tableDto = dbUtils.getTableDto(table);

            dbUtils.closeJdbcResource();
            return new ResponseEntity<>(tableDto, HttpStatus.OK);

        } else return new ResponseEntity<>("Table " + name + " does not exist.", HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity dropTable(String name) {
        List<Table> tables = dbUtils.dslContext()
                .meta().getTables().stream()
                .filter(table -> table.getName().equals(name)).collect(Collectors.toList());

        if (tables.size() == 1) {
            dbUtils.dslContext().dropTable(tables.get(0).getName()).execute();
            dbUtils.closeJdbcResource();
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        } else return new ResponseEntity<>("Table " + name + " does not exist.", HttpStatus.NOT_FOUND);
    }

    @Override
    public List<TableDto> showTables() {
        return dbUtils.dslContext()
                .meta().getTables().stream().filter(table -> !Constants.ignoredTables.contains(table.getName()))
                .map(table -> dbUtils.getTableDto(table))
                .collect(Collectors.toList());
    }
}
