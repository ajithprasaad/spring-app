package com.test.springapp.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private Integer userId;

    @Autowired
    private UserDAOService userDaoService;


    @RequestMapping(value = "/user/all", method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object getUsers() {
        return userDaoService.findAll();
    }

    /*********** LOGIN HANDLER ************/
    @RequestMapping(value = "user/login", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String authenticate(@RequestBody User user) {
        User existingUser = new User(userId, user.getName(), user.getEmail(), user.getPassword());
        if (userDaoService.authenticate(existingUser)) {
            return "User authentication successful";
        } else {
            return "User could not be authenticated";
        }

    }


    /********* SIGNUP HANDLER ************/
    @RequestMapping(value = "user/add", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String signup(@RequestBody User user) {
        userId = getUserCount();
        User newUser;
        try {
            newUser = new User(userId, user.getName(), user.getEmail(), user.getPassword());
            userDaoService.createUser(newUser);
        } catch (Exception ex) {
            return "Error creating the user: " + ex.toString();
        }
        return "User created with id = " + newUser.getId();
    }


    private Integer getUserCount() {
        return userDaoService.getCount();
    }
}
