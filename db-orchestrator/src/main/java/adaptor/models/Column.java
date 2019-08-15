package adaptor.models;

import adaptor.utils.ColumnType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.validation.constraints.NotNull;

@JsonPropertyOrder({
        "name",
        "type"
})
public class Column {
    @NotNull
    @JsonProperty("name")
    private String name;

    @NotNull
    @JsonProperty("type")
    private ColumnType type;

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
}
