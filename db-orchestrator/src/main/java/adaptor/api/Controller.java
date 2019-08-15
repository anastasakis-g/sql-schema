package adaptor.api;

import adaptor.models.Table;
import adaptor.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
