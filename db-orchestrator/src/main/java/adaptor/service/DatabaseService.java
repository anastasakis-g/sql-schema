package adaptor.service;

import adaptor.models.Table;
import org.springframework.http.ResponseEntity;

public interface DatabaseService {
    ResponseEntity<?> createTable(Table table);

    ResponseEntity<?> getInfo(String name);
}
