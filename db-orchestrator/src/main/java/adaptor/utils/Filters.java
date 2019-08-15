package adaptor.utils;

import adaptor.models.Column;

import java.util.function.Predicate;

import static adaptor.utils.ColumnType.PRIMARY_KEY;

public class Filters {

    public static Predicate<Column> isPrimaryKey = (column) -> column.getType().equals(PRIMARY_KEY);
}
