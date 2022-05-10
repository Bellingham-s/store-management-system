package poller;


import models.User;
import org.apache.log4j.Logger;
import repositories.UserRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsersPoller implements Runnable {
    //in memory cache for users
    private Map<String, User> userMap;

    private List<User> users;

    private UserRepository userRepository;

    private static final Logger log = Logger.getLogger(UsersPoller.class);

    private long reloadTime = 60000;

    public UsersPoller(UserRepository userRepository) {
        log.info("Starting thread to fetch users regularly from the database");
        this.userRepository = userRepository;
        this.userMap = new HashMap<>();
        this.users = new ArrayList<>();

        Thread reloadThread = new Thread(this);
        reloadThread.start();
        log.info("Started thread to fetch users regularly from the database");
    }

    /**
     * This is the run method of the thread
     */
    @Override
    public void run() {
        while (true) {
            try {
                log.info("We want to fetch the users");
                List<User> userList = this.userRepository.findAll();

                if (!userList.isEmpty()) {
                    for (User user : userList) {
                        this.userMap.put(user.getName(), user);
                        this.users.add(user);
                    }
                }
                log.info("We have added the users to the cache. size: " + this.userMap.size());
                Thread.sleep(reloadTime);
            } catch (InterruptedException ex) {
                log.info("Our thread experienced an interrupted exception!");
            } catch (Exception ex) {
                log.info("Our thread experienced an exception!");
            }
        }
    }

    /**
     * This is a method  to fetch user from the in memory cache
     * @param userName
     * @return
     */
    public User findUserInCache(String userName) {
        if(this.userMap.get(userName) != null) {
            log.info("User was found in cache");
            return this.userMap.get(userName);
        }

        return null;
    }

    public List<User> getUsers() {
        return users;
    }
}
