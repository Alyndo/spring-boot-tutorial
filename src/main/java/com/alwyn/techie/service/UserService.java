package com.alwyn.techie.service;

import com.alwyn.techie.dto.UserUpdateRecord;
import com.alwyn.techie.model.User;

import java.util.List;

public interface UserService {

    User getUserById(String id);
    void deleteUserById(String id);
    User createUser(User user);
    UserUpdateRecord updateUser(String id, UserUpdateRecord user);
    List<User> getUsersByFirstName(String firstName);
}
