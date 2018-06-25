package it.unisalento.se.api.rest;

import it.unisalento.se.exceptions.DocumentNotFoundException;
import it.unisalento.se.iservices.IDocumentService;
import it.unisalento.se.models.DocumentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/document")
public class DocumentRestController {

    @Autowired
    private IDocumentService service;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public DocumentModel getDocumentByID(@PathVariable("id") Integer ID) throws DocumentNotFoundException {
        return service.getDocumentByID(ID);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public DocumentModel saveDocument(@RequestBody DocumentModel model) {
        return service.saveDocument(model);
    }
}
