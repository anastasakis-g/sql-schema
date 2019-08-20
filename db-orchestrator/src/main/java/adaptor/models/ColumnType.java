package adaptor.models;

import org.jooq.DataType;
import org.jooq.impl.SQLDataType;

public enum ColumnType {
    STRING(SQLDataType.VARCHAR.length(255).nullable(true)),
    INTEGER(SQLDataType.INTEGER.length(11).nullable(false)),
    BOOLEAN(SQLDataType.BOOLEAN.defaultValue(false).nullable(false)),
    LONG(SQLDataType.BIGINT.length(20).nullable(false)),
    DOUBLE(SQLDataType.DOUBLE.nullable(false));

    private DataType dataType;

    ColumnType(DataType dataType) {
        this.dataType = dataType;
    }

    public DataType getDataType() {
        return this.dataType;
    }
}
