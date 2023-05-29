package com.alwyn.techie.service.impl;

import com.alwyn.techie.dto.UserUpdateRecord;
import com.alwyn.techie.model.User;
import com.alwyn.techie.repository.UserRepository;
import com.alwyn.techie.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(String id) {

        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteUserById(String id) {

        User user = getUserById(id);
        if(user == null){
            throw new RuntimeException(String.format("User with id %s not found", id));
        }
        userRepository.delete(user);
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public UserUpdateRecord updateUser(String id, UserUpdateRecord userUpdateRecord) {
        User user = getUserById(id);
        if(user == null){
            throw new RuntimeException(String.format("User with id %s not found", id));
        }
        user.setFirstName(userUpdateRecord.firstName());
        user.setLastName(userUpdateRecord.lastName());
        user.setAge(userUpdateRecord.age());

        User saved = userRepository.save(user);

        return new UserUpdateRecord(
                saved.getFirstName(),
                saved.getLastName(),
                saved.getAge(),
                saved.getId());
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
