package adaptor.service;

import adaptor.models.TableDto;
import org.springframework.http.ResponseEntity;

public interface DatabaseService {
    ResponseEntity<?> createTable(TableDto table);

    ResponseEntity<?> getInfo(String name);
}
