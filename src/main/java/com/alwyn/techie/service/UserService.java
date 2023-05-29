package com.alwyn.techie.service;

import com.alwyn.techie.model.User;

import java.util.List;

public interface UserService {

    User getUserById(String id);
    void deleteUserById(String id);
    User createUser(User user);
    User updateUser(String id, User user);
    List<User> getUsersByFirstName(String firstName);
}
