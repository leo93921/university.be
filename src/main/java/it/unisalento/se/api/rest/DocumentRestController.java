package it.unisalento.se.api.rest;

import it.unisalento.se.dto.DocumentDto;
import it.unisalento.se.exceptions.*;
import it.unisalento.se.iservices.IDocumentService;
import it.unisalento.se.iservices.IStorageService;
import it.unisalento.se.models.DocumentModel;
import it.unisalento.se.models.LessonModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/document")
public class DocumentRestController {

    @Autowired
    private IDocumentService service;
    @Autowired
    private IStorageService storageService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public DocumentModel getDocumentByID(@PathVariable("id") Integer ID) throws DocumentNotFoundException, UserTypeNotSupported {
        return service.getDocumentByID(ID);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public DocumentModel saveDocument(
            @RequestParam("file") MultipartFile file,
            @RequestParam("name") String documentName,
            @RequestParam("note") String documentNote,
            @RequestParam("publishDate") String publishDate,
            @RequestParam("lesson-id") String lessonId
    ) throws UserTypeNotSupported, StorageException, LessonNotFoundException, ParseException, NodeNotSupportedException {

        return service.saveDocument(file, documentName, documentNote, publishDate, lessonId);
    }

    @PostMapping(value = "/find-by-lesson", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<DocumentModel> getDocumentsByLesson(@RequestBody LessonModel lesson) throws UserTypeNotSupported {
        return service.getDocumentsByLesson(lesson);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Boolean deleteDocument(@PathVariable("id") Integer ID) throws EntityNotDeletableException {
        return service.deleteDocument(ID);
    }

    @PostMapping(value = "/download")
    public ResponseEntity<Resource> downloadFile(@RequestBody DocumentDto documentDto, HttpServletRequest request) throws FileNotFoundException, StorageException {
        // Load file as resource
        Resource resource = storageService.loadAsResource(documentDto.getLink());

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            System.err.println("not possible to determine contentType");
        }

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
