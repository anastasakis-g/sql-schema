package adaptor.service;

import adaptor.models.Column;
import adaptor.models.TableDto;
import adaptor.utils.ColumnType;
import org.jooq.DSLContext;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.impl.DSL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DatabaseExe implements DatabaseService {
    Logger log = LoggerFactory.getLogger(DatabaseExe.class);

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
            Table<?> table = tables.get(0);

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
            return new ResponseEntity<>(tableDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Table " + name + " does not exist.", HttpStatus.NOT_FOUND);
        }
    }

}
