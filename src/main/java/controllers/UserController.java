package controllers;

import models.User;
import services.UserService;

import java.util.List;
import java.util.Scanner;

public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public void saveUser(String name){
        User user = new User();
        user.setName(name);

        userService.saveUser(user);
    }

    public List<User> getUsers (){
        return this.userService.getAllUsers();
    }

    public User findByUserName(String userName) {
        return this.userService.findByUserName(userName);
    }
}
