package com.example.vacation_management_system.controller;

import com.example.vacation_management_system.converter.RoleConverter;
import com.example.vacation_management_system.dto.RoleDto;
import com.example.vacation_management_system.model.Role;
import com.example.vacation_management_system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/roles")
public class RoleController {

    private final RoleService roleService;
    private final RoleConverter roleConverter;

    @Autowired
    public RoleController(RoleService roleService, RoleConverter roleConverter) {
        this.roleService = roleService;
        this.roleConverter = roleConverter;
    }

    @GetMapping
    public List<RoleDto> findAllRoles(){
       List<Role> roles = roleService.findAll();

       List<RoleDto> roleDto = new ArrayList<>();
       for (Role role : roles){
           roleDto.add(roleConverter.convertToDto(role));
       }

       return roleDto;
    }





}
