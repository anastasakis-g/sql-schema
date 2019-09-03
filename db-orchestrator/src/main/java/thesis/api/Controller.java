package thesis.api;

import thesis.models.TableDto;
import thesis.service.DatabaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1.0/schema/tables")
public class Controller {

    private Logger log = LoggerFactory.getLogger(Controller.class);

    @Autowired
    @Qualifier("databaseExe")
    private DatabaseService service;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity createTable(@RequestBody @Valid TableDto table) throws IOException {
        log.debug("------------------  REQUESTED TABLE\n {} ", table.toJsonString());
        return service.createTable(table);
    }

    @GetMapping(path = "/{name}")
    public ResponseEntity describe(@PathVariable String name) {
        return service.getInfo(name);
    }

    @DeleteMapping(path = "/{name}")
    public ResponseEntity dropTable(@PathVariable String name) {
        return service.dropTable(name);
    }
}
