package it.unisalento.se.api.rest;

import it.unisalento.se.exceptions.EvaluationNotFoundException;
import it.unisalento.se.exceptions.EvaluationRecipientNotSupported;
import it.unisalento.se.exceptions.ExamNotFoundException;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.iservices.IEvaluationService;
import it.unisalento.se.models.EvaluationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/evaluation")
public class EvaluationRestController {

    @Autowired
    private IEvaluationService evaluationService;

    @GetMapping(value = "/document/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public EvaluationModel getDocumentEvaluationbyID(@PathVariable("id") Integer ID) throws UserTypeNotSupported, ExamNotFoundException, EvaluationNotFoundException {
        return evaluationService.getDocumentEvaluationbyID(ID);
    }

    @GetMapping(value = "/lesson/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public EvaluationModel getLessonEvaluationbyID(@PathVariable("id") Integer ID) throws UserTypeNotSupported, ExamNotFoundException, EvaluationNotFoundException {
        return evaluationService.getLessonEvaluationbyID(ID);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public EvaluationModel saveEvaluation(@RequestBody EvaluationModel model) throws UserTypeNotSupported, EvaluationRecipientNotSupported {
        return evaluationService.createEvaluation(model);
    }


}
