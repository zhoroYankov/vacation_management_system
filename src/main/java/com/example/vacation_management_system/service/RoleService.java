package com.example.vacation_management_system.service;

import com.example.vacation_management_system.exception.DataAlreadyExistsException;
import com.example.vacation_management_system.exception.DataNotFoundException;
import com.example.vacation_management_system.model.Role;
import com.example.vacation_management_system.repository.RoleRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> findAll() {
        List<Role> allRoles = roleRepository.findAll();
        return allRoles;
    }


    public Role findByName(@NonNull String name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new DataNotFoundException(String.format("Role with name %s does not exist.", name)));
    }

    public void add(@NonNull String name){
        if (roleRepository.findByName(name).isPresent()){
            throw new DataAlreadyExistsException(String.format("Role with this name %s already exist.", name));
        } else {
            Role newRole = Role.builder()
                    .name(name)
                    .build();
            roleRepository.save(newRole);
        }
    }

    public void deleteByName(@NonNull String name) {
        roleRepository.deleteByName(name);
    }

}
