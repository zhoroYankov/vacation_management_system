package com.example.vacation_management_system.converter;

import com.example.vacation_management_system.dto.EmployeeDto;
import com.example.vacation_management_system.dto.EmployeeSignUpDto;
import com.example.vacation_management_system.dto.VacationDto;
import com.example.vacation_management_system.model.Employee;
import com.example.vacation_management_system.model.Role;
import com.example.vacation_management_system.model.Vacation;
import com.example.vacation_management_system.repository.RoleRepository;
import com.example.vacation_management_system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeConverter {

    private final VacationConverter vacationConverter;
    private final RoleService roleService;

    @Autowired
    public EmployeeConverter(VacationConverter vacationConverter, RoleService roleService) {
        this.vacationConverter = vacationConverter;
        this.roleService = roleService;
    }

    public Employee convertSignUpToEntity(EmployeeSignUpDto employeeSignUpDto) {

        Employee employee = Employee.builder()
                .username(employeeSignUpDto.getUsername())
                .firstName(employeeSignUpDto.getFirstName())
                .lastName(employeeSignUpDto.getLastName())
                .email(employeeSignUpDto.getEmail())
                .password(employeeSignUpDto.getEmail())
                .vacation(null)
                .build();

        return employee;
    }


    public Employee convertToEntity(EmployeeDto employeeDto){
        Vacation vacation = new Vacation();
        if (employeeDto.getVacationDto() == null){
            vacation = null;
        }else{
            vacation = vacationConverter.convertToEntity(employeeDto.getVacationDto());
        }
        Role role = roleService.findByName(employeeDto.getRoleName());

        Employee employee = Employee.builder()
                .id(employeeDto.getId())
                .username(employeeDto.getUsername())
                .firstName(employeeDto.getFirstName())
                .lastName(employeeDto.getLastName())
                .email(employeeDto.getEmail())
                .role(role)
                .vacation(vacation)
                .build();

        return employee;
    }



    public EmployeeDto convertToDto(Employee employee){
        VacationDto vacationDto = new VacationDto();
        if (employee.getVacation() == null){
            vacationDto = null;
        }else{
            vacationDto = vacationConverter.convertToDto(employee.getVacation());
        }

        EmployeeDto empDto = EmployeeDto.builder()
                .id(employee.getId())
                .username(employee.getUsername())
                .email(employee.getEmail())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .roleName(employee.getRole().getName())
                .vacationDto(vacationDto)
                .build();

        return empDto;
    }

}
