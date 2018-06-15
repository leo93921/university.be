package it.unisalento.se.api.rest;

import it.unisalento.se.dto.UserModel;
import it.unisalento.se.exceptions.UserNotFoundException;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.iservice.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/user")
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
}
