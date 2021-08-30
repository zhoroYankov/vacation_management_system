package com.example.vacation_management_system.converter;

import com.example.vacation_management_system.dto.EmployeeDto;
import com.example.vacation_management_system.dto.RoleDto;
import com.example.vacation_management_system.model.Employee;
import com.example.vacation_management_system.model.Role;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoleConverter {
    private final EmployeeConverter employeeConverter;

    public RoleConverter(EmployeeConverter employeeConverter) {
        this.employeeConverter = employeeConverter;
    }

    public Role convertToEntity(RoleDto roleDto){
        List<EmployeeDto> employeeDtos = roleDto.getEmployees();
        List<Employee> employees = new ArrayList<>();
        for (EmployeeDto empDto : employeeDtos){
            employees.add(employeeConverter.convertToEntity(empDto));
        }

        Role role = Role.builder()
                .name(roleDto.getName())
                .employees(employees)
                .build();

        return role;
    }


    public RoleDto convertToDto(Role role){
        List<Employee> employees = role.getEmployees();
        List<EmployeeDto> employeeDtos = new ArrayList<>();
        for (Employee emp : employees){
            employeeDtos.add(employeeConverter.convertToDto(emp));
        }


        RoleDto roleDto = RoleDto.builder()
                .name(role.getName())
                .employees(employeeDtos)
                .build();

        return roleDto;
    }

}
