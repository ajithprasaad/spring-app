package com.test.springapp.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private Integer userId;

    @Autowired
    private UserDAOService userDaoService;


    @RequestMapping(value = "/user/all", method = RequestMethod.GET)
    @ResponseBody
    public Object getUsers() {
        return userDaoService.findAll();
    }

    @RequestMapping(value = "user/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String authenticate(@RequestBody User user) {
        try {
            User existingUser = new User(userId, user.getName(), user.getEmail(), user.getPassword());
            if (userDaoService.authenticate(existingUser)) {

            }
        } catch (Exception ex) {
            return "Error creating the user: " + ex.toString();
        }
        return "User successfully authenticated";
    }

    private Integer getUserCount() {
        return userDaoService.getCount();
    }
}