package adaptor.api;

import adaptor.models.Table;
import adaptor.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1.0")
public class Controller {

    @Autowired
    private DatabaseService service;

    @PostMapping(path = "/schema/tables", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity createTable(@RequestBody @Valid Table table) {
        return service.createTable(table);
    }

    @GetMapping(path = "/schema/tables/{name}")
    public void getInfo(@PathVariable String name) {
        service.getInfo(name);
    }

}
