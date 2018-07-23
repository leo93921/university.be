package it.unisalento.se.api.rest;

import it.unisalento.se.common.CommonUtils;
import it.unisalento.se.common.Constants;
import it.unisalento.se.dto.DocumentDto;
import it.unisalento.se.exceptions.ScoreNotValidException;
import it.unisalento.se.models.DocumentModel;
import it.unisalento.se.models.EvaluationModel;
import it.unisalento.se.models.LessonModel;
import it.unisalento.se.models.UserModel;
import it.unisalento.se.services.EvaluationService;
import it.unisalento.se.test.utils.TestUtils;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class EvaluationRestControllerTest {

    private MockMvc mockMvc;
    @InjectMocks
    private EvaluationRestController controller;
    @Mock
    private EvaluationService evaluationService;
    @Captor
    private ArgumentCaptor<LessonModel> lessonModel;
    @Captor
    private ArgumentCaptor<DocumentDto> documentDto;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = TestUtils.getMockMvc(controller);
    }

    @Test
    public void getEvaluationbyID_Fail() throws Exception {

        mockMvc.perform(get("/evaluation/invalid/{id}", 19))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void getEvaluationByID_Document() throws Exception, ScoreNotValidException {
        EvaluationModel model = getDocumentEvaluation();
        when(evaluationService.getDocumentEvaluationbyID(any(Integer.class))).thenReturn(model);

        mockMvc.perform(get("/evaluation/document/{id}", 12))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(model.getID())))
                .andExpect(jsonPath("$.note", Matchers.is(model.getNote())))
                .andExpect(jsonPath("$.score", Matchers.is(model.getScore())))
                .andExpect(jsonPath("$.recipientType", Matchers.is(model.getRecipientType())));

        verify(evaluationService, times(1)).getDocumentEvaluationbyID(12);
        verifyNoMoreInteractions(evaluationService);
    }

    private EvaluationModel getDocumentEvaluation() {
        EvaluationModel model = new EvaluationModel();
        model.setID(2);
        model.setNote("A note");
        model.setScore(3);
        model.setRecipient(new DocumentModel());
        model.setRecipientType(Constants.DOCUMENT);
        model.setSender(new UserModel());
        return model;
    }

    @Test
    public void getEvaluationByID_Lesson() throws Exception, ScoreNotValidException {
        EvaluationModel model = getLessonEvaluation();
        when(evaluationService.getLessonEvaluationbyID(any(Integer.class))).thenReturn(model);

        mockMvc.perform(get("/evaluation/lesson/{id}", 12))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(model.getID())))
                .andExpect(jsonPath("$.note", Matchers.is(model.getNote())))
                .andExpect(jsonPath("$.score", Matchers.is(model.getScore())))
                .andExpect(jsonPath("$.recipientType", Matchers.is(model.getRecipientType())));

        verify(evaluationService, times(1)).getLessonEvaluationbyID(12);
        verifyNoMoreInteractions(evaluationService);
    }

    private EvaluationModel getLessonEvaluation() {
        EvaluationModel model = new EvaluationModel();
        model.setID(2);
        model.setNote("A note");
        model.setScore(3);
        model.setRecipient(new LessonModel());
        model.setRecipientType(Constants.LESSON);
        model.setSender(new UserModel());
        return model;
    }

    @Test
    public void getByLesson_OK() throws ScoreNotValidException, Exception {

        List<EvaluationModel> list = new ArrayList<>();
        list.add(getLessonEvaluation());
        EvaluationModel lm2 = getLessonEvaluation();
        lm2.setID(32);
        lm2.setScore(2);
        lm2.setNote("A new note");
        list.add(lm2);

        when(evaluationService.getEvaluationsByLesson(any(LessonModel.class))).thenReturn(list);

        mockMvc.perform(
                post("/evaluation/get-by-lesson")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(CommonUtils.toJson(new LessonModel()))
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", Matchers.is(list.get(0).getID())))
                .andExpect(jsonPath("$[0].note", Matchers.is(list.get(0).getNote())))
                .andExpect(jsonPath("$[0].score", Matchers.is(list.get(0).getScore())))
                .andExpect(jsonPath("$[0].recipientType", Matchers.is(list.get(0).getRecipientType())))
                .andExpect(jsonPath("$[1].id", Matchers.is(list.get(1).getID())))
                .andExpect(jsonPath("$[1].note", Matchers.is(list.get(1).getNote())))
                .andExpect(jsonPath("$[1].score", Matchers.is(list.get(1).getScore())))
                .andExpect(jsonPath("$[1].recipientType", Matchers.is(list.get(1).getRecipientType())));

        verify(evaluationService, times(1)).getEvaluationsByLesson(lessonModel.capture());
        verifyNoMoreInteractions(evaluationService);
    }

    @Test
    public void getByDocument_OK() throws ScoreNotValidException, Exception {
        List<EvaluationModel> list = new ArrayList<>();
        list.add(getDocumentEvaluation());
        EvaluationModel lm2 = getDocumentEvaluation();
        lm2.setID(32);
        lm2.setScore(2);
        lm2.setNote("A new note");
        list.add(lm2);

        when(evaluationService.getEvaluationsByDocument(any(DocumentDto.class))).thenReturn(list);

        mockMvc.perform(
                post("/evaluation/get-by-document")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(CommonUtils.toJson(new DocumentDto()))
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", Matchers.is(list.get(0).getID())))
                .andExpect(jsonPath("$[0].note", Matchers.is(list.get(0).getNote())))
                .andExpect(jsonPath("$[0].score", Matchers.is(list.get(0).getScore())))
                .andExpect(jsonPath("$[0].recipientType", Matchers.is(list.get(0).getRecipientType())))
                .andExpect(jsonPath("$[1].id", Matchers.is(list.get(1).getID())))
                .andExpect(jsonPath("$[1].note", Matchers.is(list.get(1).getNote())))
                .andExpect(jsonPath("$[1].score", Matchers.is(list.get(1).getScore())))
                .andExpect(jsonPath("$[1].recipientType", Matchers.is(list.get(1).getRecipientType())));

        verify(evaluationService, times(1)).getEvaluationsByDocument(documentDto.capture());
        verifyNoMoreInteractions(evaluationService);
    }
}