package com.example.vacation_management_system.converter;

import com.example.vacation_management_system.dto.VacationBookingDto;
import com.example.vacation_management_system.dto.VacationDto;
import com.example.vacation_management_system.model.Vacation;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class VacationConverter {


    public Vacation convertBookingToEntity(VacationBookingDto vacationBookingDto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate startDate = LocalDate.parse(vacationBookingDto.getStartDate(), formatter);
        LocalDate endDate = LocalDate.parse(vacationBookingDto.getEndDate(), formatter);

        Vacation vacation = Vacation.builder()
                .startDate(startDate)
                .endDate(endDate)
                .build();

        return vacation;
    }

    public Vacation convertToEntity(VacationDto vacationDto){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate startDate = LocalDate.parse(vacationDto.getStartDate(), formatter);
        LocalDate endDate = LocalDate.parse(vacationDto.getEndDate(), formatter);

        Vacation vacation = Vacation.builder()
                .startDate(startDate)
                .endDate(endDate)
                .build();

        return vacation;
    }


    public VacationDto convertToDto(Vacation vacation) {
        String employee = null;
        if (vacation.getEmployee() != null){
            employee = vacation.getEmployee().getFirstName() + " " + vacation.getEmployee().getLastName();
        }

        VacationDto vacationDto = VacationDto.builder()
                .startDate(vacation.getStartDate().toString())
                .endDate(vacation.getEndDate().toString())
                .employee(employee)
                .build();
        return vacationDto;
    }


}
