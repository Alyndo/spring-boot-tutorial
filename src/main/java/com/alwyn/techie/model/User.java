package com.alwyn.techie.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class User {

    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private Integer age;
    private List<String> roles;

}
