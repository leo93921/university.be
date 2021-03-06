package it.unisalento.se.api.rest;

import it.unisalento.se.exceptions.InvalidCredentialsException;
import it.unisalento.se.exceptions.UserAlreadyRegisteredException;
import it.unisalento.se.exceptions.UserNotFoundException;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.iservices.IUserService;
import it.unisalento.se.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserRestController {

    @Autowired
    private IUserService userService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserModel getUserById(@PathVariable("id") Integer ID) throws UserTypeNotSupported, UserNotFoundException {
        return userService.getUserByID(ID);
    }

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserModel saveUser(@RequestBody UserModel user) throws UserTypeNotSupported {
        return userService.createUser(user);
    }

    @PostMapping(
            value = "/login",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserModel login(@RequestBody UserCredentials credentials) throws InvalidCredentialsException, UserTypeNotSupported {
        return userService.checkCredentials(credentials);
    }

    @GetMapping(value = "/all-professors", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<UserModel> getAllProfessors() throws UserTypeNotSupported {
        return userService.getAllProfessors();
    }

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserModel register(@RequestBody RegistrationRequest request) throws UserTypeNotSupported, UserAlreadyRegisteredException {
        return userService.register(request);
    }

    @PostMapping(value = "/fcm-token", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public FCMTokenRegistration registerToken(@RequestBody FCMTokenRegistration request) {
        return userService.registerFCMToken(request);
    }

    @PostMapping(value = "/unsubscribe-fcm-token", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public boolean unsubscribeFromFCM(@RequestBody UserModel user) throws UserTypeNotSupported {
        return userService.deleteFcmToken(user);
    }

    @PostMapping(value = "/get-students", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<UserModel> getStudentsByCourseOfStudy(@RequestBody CourseOfStudyModel courseOfStudy) throws UserTypeNotSupported {
        return userService.getStudentsByCourseOfStudy(courseOfStudy);
    }
}
