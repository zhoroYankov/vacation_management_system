package com.example.vacation_management_system.controller;

import com.example.vacation_management_system.converter.EmployeeConverter;
import com.example.vacation_management_system.converter.VacationConverter;
import com.example.vacation_management_system.dto.EmployeeDto;
import com.example.vacation_management_system.dto.EmployeeSignUpDto;
import com.example.vacation_management_system.dto.VacationBookingDto;
import com.example.vacation_management_system.model.Employee;
import com.example.vacation_management_system.model.Vacation;
import com.example.vacation_management_system.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeConverter employeeConverter;
    private final VacationConverter vacationConverter;

    @Autowired
    public EmployeeController(EmployeeService employeeService, EmployeeConverter employeeConverter, VacationConverter vacationConverter) {
        this.employeeService = employeeService;
        this.employeeConverter = employeeConverter;
        this.vacationConverter = vacationConverter;
    }


    @GetMapping
    public ResponseEntity<List<EmployeeDto>> findAllEmployees() {
        List<Employee> listOfEmployees = employeeService.findAll();

        List<EmployeeDto> employeeDtos = new ArrayList<>();
        for (Employee emp : listOfEmployees){
            employeeDtos.add(employeeConverter.convertToDto(emp));
        }
        return ResponseEntity.ok(employeeDtos);
    }

    @GetMapping(value = "/{email}")
    public ResponseEntity<EmployeeDto> findByEmail(@PathVariable String email) {
        Employee emp = employeeService.findByEmail(email);
        EmployeeDto dto = employeeConverter.convertToDto(emp);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> save(@RequestBody EmployeeSignUpDto employeeSignUpDto) {
        Employee emp = employeeConverter.convertSignUpToEntity(employeeSignUpDto);
       employeeService.save(emp);
       return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping(value = "/{email}")
    public ResponseEntity<HttpStatus> bookVacation(@PathVariable String email, @RequestBody VacationBookingDto vacationBookingDto) {
        Vacation vacation = vacationConverter.convertBookingToEntity(vacationBookingDto);
        employeeService.bookVacation(email, vacation);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(value = "/{email}")
    public ResponseEntity<HttpStatus> deleteVacation(@PathVariable String email){
        employeeService.deleteVacation(email);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }


    //can be updated only by higher rank employee
//    public ResponseEntity<HttpStatus> updateVacation(){
//
//    }


    @DeleteMapping(value = "/{email}")
    public ResponseEntity<HttpStatus> deleteByEmail(@PathVariable String email) {
        employeeService.deleteByEmail(email);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
