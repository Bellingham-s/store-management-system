package services;

import models.User;
import poller.UsersPoller;
import repositories.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {

    private final UsersPoller usersPoller;

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.usersPoller = new UsersPoller(userRepository);
    }

    public void saveUser(User user) {
        if (usersPoller.findUserInCache(user.getName()) == null) {
            if (userRepository.findByName(user.getName()) == null) {
                this.userRepository.save(user);
            } else {
                System.out.println("user already exists");
            }
        } else {
            System.out.println("user already exists");
        }
    }


    public List<User> getAllUsers() {
        return this.usersPoller.getUsers();
    }

    public User findByUserName(String userName) {
        return this.usersPoller.findUserInCache(userName);
    }

}
