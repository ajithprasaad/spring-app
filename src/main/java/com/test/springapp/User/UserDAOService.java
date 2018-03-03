package com.test.springapp.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDAOService {

    @Autowired
    private UserRepository userRepository;
    private List<User> userList= new ArrayList<>();

    void createUser(User user) {
        userRepository.save(user);
    }

    Object findAll() {
        return userRepository.findAll();
    }

    Integer getCount() {
        Integer count = (int) (long) userRepository.count();
        if (count == 0) {
            return 1;
        } else {
            count = count + 1;
        }
        return count;
    }

    boolean authenticate(User existingUser) {
        userList.clear();
        userRepository.findAll().forEach(user -> userList.add(user));
        if(userList.contains(existingUser.getEmail()) && userList.contains(existingUser.getPassword())){
            return true;
        } else return false;
    }
}