package thesis.service;

import thesis.models.TableDto;
import org.springframework.http.ResponseEntity;

public interface DatabaseService {
    ResponseEntity<?> createTable(TableDto table);

    ResponseEntity<?> getInfo(String name);

    ResponseEntity dropTable(String name);
}
