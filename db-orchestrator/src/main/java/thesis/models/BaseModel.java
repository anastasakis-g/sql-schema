package thesis.models;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class BaseModel {

    public String toJsonString() throws IOException {
        return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
    }
}
