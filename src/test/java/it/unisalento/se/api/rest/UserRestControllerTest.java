package it.unisalento.se.api.rest;

import it.unisalento.se.common.CommonUtils;
import it.unisalento.se.test.utils.TestUtils;
import it.unisalento.se.dto.UserModel;
import it.unisalento.se.dto.UserTypeModel;
import it.unisalento.se.iservice.IUserService;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(MockitoJUnitRunner.class)
public class UserRestControllerTest {

    private MockMvc mockMvc;
    @Mock private IUserService userServiceMock;
    @InjectMocks private UserRestController controller;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .setViewResolvers(CommonUtils.getCommonUtils().getCommonResolver())
                .build();
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
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.surname", Matchers.is("Rossi")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Mario")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is("mario.rossi@test.it")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userType", Matchers.is("SECRETARIAT")));


        verify(userServiceMock, times(1)).createUser(refEq(u));
        verifyNoMoreInteractions(userServiceMock);

    }

}
