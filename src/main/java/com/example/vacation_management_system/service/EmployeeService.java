package com.example.vacation_management_system.service;

import com.example.vacation_management_system.exception.DataNotFoundException;
import com.example.vacation_management_system.model.Employee;
import com.example.vacation_management_system.model.Role;
import com.example.vacation_management_system.model.Vacation;
import com.example.vacation_management_system.repository.EmployeeRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final VacationService vacationService;
    private final RoleService roleService;


    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, RoleService roleService, VacationService vacationService) {
        this.employeeRepository = employeeRepository;
        this.roleService = roleService;
        this.vacationService = vacationService;
    }

    //find all employees
    public List<Employee> findAll() {
        List<Employee> listOfEmployees = employeeRepository.findAll();

        return listOfEmployees;
    }


    //find employee by email
    public Employee findByEmail(@NonNull String email) {
        Employee current = employeeRepository.findByEmail(email)
                .orElseThrow(() -> new DataNotFoundException(String.format("Employee with email: %s does not exist.", email)));
        return current;
    }


    //find employee by username
    public Employee findByUsername(@NonNull String username) {
        Employee current = employeeRepository.findByUsername(username)
                .orElseThrow(() -> new DataNotFoundException(String.format("Employee with username: %s does not exist.", username)));
        return current;
    }


    //save new employee
    public void save(@NonNull Employee employee) {
        employee.setRole(roleService.findByName("EMPLOYEE"));
        employeeRepository.save(employee);
    }


    //update the role of the employee
    public void updateRole(@NonNull String email, String roleName) {
        Employee currentEmployee = employeeRepository.findByEmail(email)
                .orElseThrow(() -> new DataNotFoundException(String.format("Employee with email: %s does not exist.", email)));
        Role currentRole = roleService.findByName(roleName);
        currentEmployee.setRole(currentRole);
    }


    //book a vacation via employee account
    public void bookVacation(@NonNull String email, Vacation vacation) {
        Employee current = employeeRepository.findByEmail(email)
                .orElseThrow(() -> new DataNotFoundException(String.format("Employee with email: %s does not exist.", email)));
        vacation.setEmployee(current);
        vacationService.save(vacation);

        current.setVacation(vacation);
        employeeRepository.save(current);
    }


    //delete employees vacation
    public void deleteVacation(@NonNull String email) {
        Employee current = employeeRepository.findByEmail(email)
                .orElseThrow(()-> new DataNotFoundException(String.format("Employee with email: %s does not exist.", email)));

        if (current.getVacation() == null) {
            return;
        } else{
            Vacation vacation = current.getVacation();
            vacationService.delete(vacation);
            current.setVacation(null);
        }
        employeeRepository.save(current);
    }


    //delete employee account by email
    public void deleteByEmail(@NonNull String email) {
        employeeRepository.deleteByEmail(email);
    }
}
