package thesis.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.validation.constraints.NotNull;
import java.util.LinkedList;
import java.util.List;

@JsonPropertyOrder({
        "name",
        "columns"
})
public class TableDto extends BaseModel{
    @NotNull
    @JsonProperty("name")
    private String name;

    @JsonProperty("columns")
    private List<Column> columns;

    public TableDto() {
        this.columns = new LinkedList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public void setColumn(Column column) {
        this.columns.add(column);
    }
}
