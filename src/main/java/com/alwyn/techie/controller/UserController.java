package com.alwyn.techie.controller;

import com.alwyn.techie.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<User> getUserById(@PathVariable("id") String id){

        User user = getUsers().stream().filter(user1 -> user1.getId().equals(id)).findFirst().orElse(null);

        if(user == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(user);
    }

    @DeleteMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<User> deleteUserById(@PathVariable("id") String id){
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") String id, @RequestBody User user){

        //first find record by id

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
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
