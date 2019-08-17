package adaptor.utils;

public enum ColumnType {
    STRING(" VARCHAR(255)"),
    INTEGER(" INT NOT NULL"),
    BOOLEAN(" TINYINT(1) DEFAULT false"),
    LONG(" BIGINT(20) NOT NULL"),
    DOUBLE(" DOUBLE NOT NULL");

    private String sql;

    ColumnType(String sql) {
        this.sql = sql;
    }

    public String getSql() {
        return this.sql;
    }
}
