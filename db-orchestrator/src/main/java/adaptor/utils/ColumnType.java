package adaptor.utils;

public enum ColumnType {
    PRIMARY_KEY(" BIGINT(20) NOT NULL AUTO_INCREMENT"),
    STRING(" VARCHAR(255)"),
    INTEGER(" INT NOT NULL"),
    BOOLEAN(" TINYINT(1) DEFAULT false"),
    LONG(" BIGINT NOT NULL"),
    DOUBLE(" DOUBLE NOT NULL");

    private String sql;

    ColumnType(String sql) {
        this.sql = sql;
    }

    public String getSql() {
        return this.sql;
    }
}
