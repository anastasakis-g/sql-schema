package adaptor.service;

import adaptor.models.Column;
import adaptor.models.Table;
import adaptor.utils.ColumnType;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DatabaseExe implements DatabaseService {

    @Autowired
    private DbUtils dbUtils;

    @Override
    public ResponseEntity<?> createTable(Table table) {
        DSLContext dslContext = dbUtils.dslContext();
        String name = table.getName();

        List<Column> pkColumns = table.getColumns().stream().filter(Column::isPrimaryKey).collect(Collectors.toList());

        if (pkColumns.size() == 1) {
            Column pkColumn = pkColumns.get(0);
            String pkField = pkColumn.getName();
            ColumnType pkType = pkColumn.getType();

            dslContext.createTable(name)
                    .column(pkField, pkType.getDataType().identity(true))
                    .constraint(DSL.primaryKey(pkField))
                    .execute();

            table.getColumns().stream().filter(column -> !column.isPrimaryKey())
                    .forEach(o -> dslContext.alterTable(name)
                            .addColumn(o.getName(), o.getType().getDataType())
                            .execute());

            dbUtils.closeJdbcResource();
            return new ResponseEntity<>(table, HttpStatus.CREATED);

        } else if (pkColumns.size() == 0) {
            return new ResponseEntity<>("NO PRIMARY_KEY defined for table " + name, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("Multiple PRIMARY_KEYS in a single table.", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> getInfo(String name) {

        dbUtils.dslContext()
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
        return null;
    }
}
