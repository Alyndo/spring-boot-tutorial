package com.alwyn.techie.controller;

import com.alwyn.techie.model.User;
import com.alwyn.techie.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<User> getUserById(@PathVariable("id") String id){

        User user = userService.getUserById(id);
        if(user == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(user);
    }

    @DeleteMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<User> deleteUserById(@PathVariable("id") String id){

        userService.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") String id, @RequestBody User user){

        //first find record by id

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.updateUser(id, user));
    }

    @GetMapping(value = "/search-by-first-name", produces = {MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<List<User>> getUsersByFirstName(@RequestParam String firstName, @RequestHeader("X-API-KEY") String apiKey){

        return ResponseEntity.ok(userService.getUsersByFirstName(firstName));
    }
}
