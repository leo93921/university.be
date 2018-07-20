package it.unisalento.se.api.rest;

import it.unisalento.se.exceptions.*;
import it.unisalento.se.iservices.IEvaluationService;
import it.unisalento.se.models.DocumentModel;
import it.unisalento.se.models.EvaluationModel;
import it.unisalento.se.models.LessonModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/evaluation")
public class EvaluationRestController {

    @Autowired
    private IEvaluationService evaluationService;

    @GetMapping(value = "/{type}/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public EvaluationModel getEvaluationbyID(@PathVariable("type") String type, @PathVariable("id") Integer ID)
            throws UserTypeNotSupported, EvaluationNotFoundException, EvaluationRecipientNotSupported, ScoreNotValidException {
        if (type.equals("document")) {
            return evaluationService.getDocumentEvaluationbyID(ID);
        }
        if (type.equals("lesson")) {
            return evaluationService.getLessonEvaluationbyID(ID);
        } else {
            throw new EvaluationRecipientNotSupported("Cannot evaluate this type : " + type);
        }
    }

    @PostMapping(value = "/get-by-lesson", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<EvaluationModel> getByLesson(@RequestBody LessonModel lesson) throws UserTypeNotSupported, EvaluationRecipientNotSupported, ScoreNotValidException {
        return evaluationService.getEvaluationsByLesson(lesson);
    }

    @PostMapping(value = "/get-by-document", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<EvaluationModel> getByDocument(@RequestBody DocumentModel document) throws UserTypeNotSupported, EvaluationRecipientNotSupported, ScoreNotValidException, NodeNotSupportedException {
        return evaluationService.getEvaluationsByDocument(document);
    }




    /*@GetMapping(value = "/lesson/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public EvaluationModel getLessonEvaluationbyID(@PathVariable("id") Integer ID) throws UserTypeNotSupported, ExamNotFoundException, EvaluationNotFoundException {
        return evaluationService.getLessonEvaluationbyID(ID);
    }*/



    /*
    @RequestMapping(value = "/{app}/conf/{fnm}", method=RequestMethod.GET)
public ResponseEntity<?> getConf(@PathVariable("app") String app, @PathVariable("fnm") String fnm) {
   log.debug("AppName:" + app);
   log.debug("fName:" + fnm);
           ...
           return ...
  }
     */

    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public EvaluationModel saveEvaluation(@RequestBody EvaluationModel model) throws UserTypeNotSupported, EvaluationRecipientNotSupported, ScoreNotValidException, NodeNotSupportedException {
        return evaluationService.createEvaluation(model);
    }


}
