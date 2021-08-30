package com.example.vacation_management_system.controller;

import com.example.vacation_management_system.converter.VacationConverter;
import com.example.vacation_management_system.dto.VacationDto;
import com.example.vacation_management_system.model.Employee;
import com.example.vacation_management_system.model.Vacation;
import com.example.vacation_management_system.service.EmployeeService;
import com.example.vacation_management_system.service.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/vacations")
public class VacationController {

    private final VacationConverter vacationConverter;
    private final VacationService vacationService;
    private final EmployeeService employeeService;

    @Autowired
    public VacationController(VacationConverter vacationConverter, VacationService vacationService, EmployeeService employeeService) {
        this.vacationConverter = vacationConverter;
        this.vacationService = vacationService;
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<VacationDto>> findAllVacations() {
        List<Vacation> vacationList = vacationService.findAll();
        List<VacationDto> vacationDtos = new ArrayList<>();
        for (Vacation vac : vacationList){
            vacationDtos.add(vacationConverter.convertToDto(vac));
        }
        return ResponseEntity.ok(vacationDtos);
    }

    @GetMapping(value = "/{email}")
    public ResponseEntity<VacationDto> findByEmployee(@PathVariable String email){
        Employee emp = employeeService.findByEmail(email);
        Vacation vac = emp.getVacation();
        VacationDto vacDto = vacationConverter.convertToDto(vac);
        return ResponseEntity.ok(vacDto);
    }


}
