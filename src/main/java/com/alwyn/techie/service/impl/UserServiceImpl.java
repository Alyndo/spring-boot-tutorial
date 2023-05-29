package com.alwyn.techie.service.impl;

import com.alwyn.techie.model.User;
import com.alwyn.techie.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User getUserById(String id) {
        return getUsers().stream().filter(user1 -> user1.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public void deleteUserById(String id) {

        User user = getUserById(id);
        if(user == null){
            throw new RuntimeException(String.format("User with id %s not found", id));
        }
    }

    @Override
    public User createUser(User user) {
        return user;
    }

    @Override
    public User updateUser(String id, User user) {
        return user;
    }

    @Override
    public List<User> getUsersByFirstName(String firstName) {

        return getUsers().stream().filter(user1 -> user1.getFirstName().equalsIgnoreCase(firstName)).collect(Collectors.toList());

    }

    private User getDemoUser(String id, String username, String firstName, String lastName, int age){

        return User.builder()
                .id(id)
                .username(username)
                .firstName(firstName)
                .lastName(lastName)
                .age(age)
                .roles(Arrays.asList("DEVELOPER", "MAINTAINER", "ADMIN"))
                .build();
    }

    private List<User> getUsers(){
        List<User> users = new ArrayList<>();
        users.add(getDemoUser("1","Lolo","Alwyn", "Munatsi", 28));
        users.add(getDemoUser("2","Shamy","Sharmaine", "Marisa", 22));
        users.add(getDemoUser("3","Tawaz","Tawanda", "Dada", 28));
        return users;
    }
}
