package com.example.vacation_management_system.service;

import com.example.vacation_management_system.converter.VacationConverter;
import com.example.vacation_management_system.model.Vacation;
import com.example.vacation_management_system.repository.VacationRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacationService {

    private final VacationRepository vacationRepository;
    private final VacationConverter vacationConverter;

    @Autowired
    public VacationService(VacationRepository vacationRepository, VacationConverter vacationConverter) {
        this.vacationRepository = vacationRepository;
        this.vacationConverter = vacationConverter;

    }

    public List<Vacation> findAll() {
       return vacationRepository.findAll();
    }


    public void save(@NonNull Vacation vacation){
        vacationRepository.save(vacation);
    }


    public void delete(@NonNull Vacation vacation) {
        vacationRepository.delete(vacation);
    }
}
