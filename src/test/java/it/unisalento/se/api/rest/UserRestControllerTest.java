package it.unisalento.se.api.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.unisalento.se.dao.User;
import it.unisalento.se.dao.UserType;
import it.unisalento.se.dto.UserModel;
import it.unisalento.se.dto.UserTypeModel;
import it.unisalento.se.iservice.IUserService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import static org.mockito.Matchers.isNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(MockitoJUnitRunner.class)
public class UserRestControllerTest {

    private MockMvc mockMvc;
    @Mock private IUserService userServiceMock;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(new UserRestController(userServiceMock))
                .setViewResolvers(viewResolver())
                .build();
    }

    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/templates/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
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
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.surname", Matchers.is("Rossi")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Mario")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is("mario.rossi@test.it")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userType", Matchers.is("STUDENT")));

        verify(userServiceMock, times(1)).getUserByID(1);
        verifyNoMoreInteractions(userServiceMock);
    }

    /*@Test
    public void saveUser() throws Exception {
        UserModel u = new UserModel();
        u.setUserType(UserTypeModel.SECRETARIAT);
        u.setSurname("Rossi");
        u.setName("Mario");
        u.setEmail("mario.rossi@test.it");

        UserModel returned = new UserModel();
        u.setUserType(UserTypeModel.SECRETARIAT);
        u.setSurname("Rossi");
        u.setName("Mario");
        u.setEmail("mario.rossi@test.it");
        u.setId(1);

        when(userServiceMock.createUser(any(UserModel.class)))
                .thenReturn(returned);

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        byte[] json = mapper.writeValueAsBytes(u);
        mockMvc.perform(
                post("/user")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json)
        )
                .andExpect(MockMvcResultMatchers.status().isOk());
                //.andDo(MockMvcResultHandlers.print())
                /*.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.surname", Matchers.is("Rossi")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Mario")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is("mario.rossi@test.it")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userType", Matchers.is("SECRETARIAT")));*/
        /*verify(userServiceMock, times(1)).createUser(json);
        verifyNoMoreInteractions(userServiceMock);

        ArgumentCaptor<UserModel> varArgs = ArgumentCaptor.forClass(UserModel.class);
        System.out.print(varArgs.getAllValues());
        /*verify(userServiceMock).varArgMethod(varArgs.capture());
        4
        List expected = asList(new Person("John"), new Person("Jane"));
        5
        assertEquals(expected, varArgs.getAllValues());*/

    //}

}
