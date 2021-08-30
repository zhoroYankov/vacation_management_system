package com.example.vacation_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeeSignUpDto {

    private String username;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

}