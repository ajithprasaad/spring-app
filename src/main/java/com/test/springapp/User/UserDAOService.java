package com.test.springapp.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDAOService {

    @Autowired
    private UserRepository userRepository;

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
        return true;
    }
}