package adaptor.models;

import adaptor.utils.ColumnType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.validation.constraints.NotNull;

@JsonPropertyOrder({
        "name",
        "type",
        "primaryKey"
})
public class Column {
    @NotNull
    @JsonProperty("name")
    private String name;

    @NotNull
    @JsonProperty("type")
    private ColumnType type;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonProperty("primaryKey")
    private boolean primaryKey = false;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ColumnType getType() {
        return type;
    }

    public void setType(ColumnType type) {
        this.type = type;
    }

    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }
}
