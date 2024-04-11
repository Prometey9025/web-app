package com.graduate.graduatework.dto;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Email;

@Data
public class AccountDTO {
    private Long id;
    @ToString.Exclude
    private String password;
    @Email
    private String email;
    private String firstName;
    private String lastName;

}
