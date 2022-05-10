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

    public void saveUser(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("please enter the name of the user you want to save");
        String name = scanner.nextLine();
        User user = new User();
        user.setName(name);

        userService.saveUser(user);
    }

    public List<User> getUsers (){
        return this.userService.getAllUsers();
    }
}
