package services;

import models.User;
import repositories.UserRepository;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(User user){
        if(userRepository.findByName(user.getName()) == null) {
            this.userRepository.save(user);
        }
        else{
            System.out.println("user already exists");
        }
    }


}
