package com.alwyn.techie.controller;

import com.alwyn.techie.dto.UserUpdateRecord;
import com.alwyn.techie.exception.NotFoundException;
import com.alwyn.techie.model.User;
import com.alwyn.techie.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Find user by id",
            description = "REST endpoint that finds user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User found"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<User> getUserById(@PathVariable("id") String id){

        User user = userService.getUserById(id);

        if(user == null){
            throw new NotFoundException(String.format("User with id %s does not exist", id));
        }
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "Delete a user",
            description = "REST endpoint that deletes a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Deleted user"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })
    @DeleteMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<User> deleteUserById(@PathVariable("id") String id){

        userService.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Create a new user",
            description = "REST endpoint that creates a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created user"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })
    @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
    }

    @Operation(summary = "Update a user",
            description = "REST endpoint that updates a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Updated user"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })
    @PutMapping(value = "/{id}")
    public ResponseEntity<UserUpdateRecord> updateUser(@PathVariable("id") String id, @RequestBody UserUpdateRecord userUpdateRecord){

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.updateUser(id, userUpdateRecord));
    }

    @Operation(summary = "Find user by first name",
            description = "REST endpoint that finds a user by first name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User found by first name"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })
    @GetMapping(value = "/search-by-first-name", produces = {MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<List<User>> getUsersByFirstName(@RequestParam String firstName, @RequestHeader("X-API-KEY") String apiKey){

        return ResponseEntity.ok(userService.getUsersByFirstName(firstName));
    }
}
