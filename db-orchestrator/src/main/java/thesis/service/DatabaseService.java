package thesis.service;

import thesis.models.TableDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DatabaseService {
    ResponseEntity<?> createTable(TableDto table);

    ResponseEntity<?> getInfo(String name);

    ResponseEntity dropTable(String name);

    List<TableDto> showTables();
}
