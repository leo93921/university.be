package it.unisalento.se.api.rest;

import it.unisalento.se.common.Constants;
import it.unisalento.se.exceptions.InvalidCredentialsException;
import it.unisalento.se.exceptions.UserAlreadyRegisteredException;
import it.unisalento.se.exceptions.UserNotFoundException;
import it.unisalento.se.iservices.IUserService;
import it.unisalento.se.models.RegistrationRequest;
import it.unisalento.se.models.UserCredentials;
import it.unisalento.se.models.UserModel;
import it.unisalento.se.models.UserTypeModel;
import it.unisalento.se.test.utils.TestUtils;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class UserRestControllerTest {

    private MockMvc mockMvc;
    @Mock
    private IUserService userServiceMock;
    @InjectMocks
    private UserRestController controller;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = TestUtils.getMockMvc(controller);
    }

    @Test
    public void findUserById() throws Exception {
        UserModel u = new UserModel();
        u.setUserType(UserTypeModel.STUDENT);
        u.setSurname("Rossi");
        u.setName("Mario");
        u.setId(1);
        u.setEmail("mario.rossi@test.it");

        when(userServiceMock.getUserByID(1)).thenReturn(u);

        // Do call and test
        mockMvc.perform(get("/user/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.surname", Matchers.is("Rossi")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Mario")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is("mario.rossi@test.it")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userType", Matchers.is(Constants.STUDENT)));

        verify(userServiceMock, times(1)).getUserByID(1);
        verifyNoMoreInteractions(userServiceMock);
    }

    @Test
    public void findByUserIdWithInvalidId() throws Exception {

        when(userServiceMock.getUserByID(10)).thenThrow(new UserNotFoundException());

        mockMvc.perform(get("/user/{id}", 10))
                .andExpect(status().isNotFound());

        verify(userServiceMock, times(1)).getUserByID(10);
        verifyNoMoreInteractions(userServiceMock);
    }

    @Test
    public void saveUser() throws Exception {
        UserModel u = new UserModel();
        u.setUserType(UserTypeModel.SECRETARIAT);
        u.setSurname("Rossi");
        u.setName("Mario");
        u.setEmail("mario.rossi@test.it");
        u.setId(1);

        when(userServiceMock.createUser(any(UserModel.class)))
                .thenReturn(u);

        mockMvc.perform(
                post("/user")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(TestUtils.toJson(u))
        )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.surname", Matchers.is("Rossi")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Mario")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is("mario.rossi@test.it")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userType", Matchers.is(Constants.SECRETARIAT)));


        verify(userServiceMock, times(1)).createUser(refEq(u));
        verifyNoMoreInteractions(userServiceMock);

    }

    @Test
    public void login_withValidCredentials() throws Exception {
        UserCredentials credentials = new UserCredentials();
        credentials.setUsername("valid");
        credentials.setPassword("test");

        UserModel u = new UserModel();
        u.setUserType(UserTypeModel.SECRETARIAT);
        u.setSurname("Rossi");
        u.setName("Mario");
        u.setEmail("mario.rossi@test.it");
        u.setId(1);

        when(userServiceMock.checkCredentials(any(UserCredentials.class))).thenReturn(u);

        mockMvc.perform(
                post("/user/login")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(TestUtils.toJson(credentials))
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.surname", Matchers.is("Rossi")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Mario")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is("mario.rossi@test.it")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userType", Matchers.is(Constants.SECRETARIAT)));

        verify(userServiceMock, times(1)).checkCredentials(refEq(credentials));
        verifyNoMoreInteractions(userServiceMock);
    }

    @Test
    public void login_withInvalidCredentials() throws Exception {
        UserCredentials credentials = new UserCredentials();
        credentials.setUsername("invalid");
        credentials.setPassword("test");

        when(userServiceMock.checkCredentials(any(UserCredentials.class))).thenThrow(new InvalidCredentialsException());

        mockMvc.perform(
                post("/user/login")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(TestUtils.toJson(credentials))
        )
                .andExpect(status().isForbidden());

        verify(userServiceMock, times(1)).checkCredentials(refEq(credentials));
        verifyNoMoreInteractions(userServiceMock);
    }

    @Test
    public void getAllProfessors() throws Exception {
        UserModel u = new UserModel();
        u.setUserType(UserTypeModel.STUDENT);
        u.setSurname("Rossi");
        u.setName("Mario");
        u.setId(1);
        u.setEmail("mario.rossi@test.it");
        List<UserModel> models = new ArrayList<>();
        models.add(u);

        when(userServiceMock.getAllProfessors()).thenReturn(models);


        // Do call and test
        mockMvc.perform(get("/user/all-professors", 1))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].surname", Matchers.is("Rossi")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("Mario")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email", Matchers.is("mario.rossi@test.it")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].userType", Matchers.is(Constants.STUDENT)));


        verify(userServiceMock, times(1)).getAllProfessors();
        verifyNoMoreInteractions(userServiceMock);
    }

    @Test
    public void registerUser() throws Exception, UserAlreadyRegisteredException {
        UserModel u = new UserModel();
        u.setUserType(UserTypeModel.PROFESSOR);
        u.setSurname("Rossi");
        u.setName("Mario");
        u.setId(1);
        u.setEmail("mario.rossi@test.it");

        RegistrationRequest req = new RegistrationRequest();
        req.setUserType(UserTypeModel.PROFESSOR);
        req.setSurname("Rossi");
        req.setName("Mario");
        req.setId(1);
        req.setEmail("mario.rossi@test.it");
        req.setPassword("Password");

        when(userServiceMock.register(any(RegistrationRequest.class))).thenReturn(u);

        mockMvc.perform(
                post("/user/register")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(TestUtils.toJson(req))
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.surname", Matchers.is("Rossi")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Mario")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is("mario.rossi@test.it")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userType", Matchers.is(Constants.PROFESSOR)));

        verify(userServiceMock, times(1)).register(refEq(req));
        verifyNoMoreInteractions(userServiceMock);
    }


}
