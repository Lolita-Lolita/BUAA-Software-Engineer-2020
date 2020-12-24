package com.example.credit.service;

import com.example.credit.entity.Credit;
import com.example.credit.repository.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CreditService {

    @Autowired
    private CreditRepository creditRepository;

    public Optional<Credit> findById(Integer id) {
        return creditRepository.findById(id);
    }

    public List<Credit> findByDishID(Integer did) {
        return creditRepository.findByDishID(did);
    }

    public Double CalAverageCredit(List<Credit> credits) {
        Double averageCredit = CalAverageLookCredit(credits)
                +CalAverageSmellCredit(credits)
                +CalAverageTasteCredit(credits);
        return averageCredit;
    }

    public Double CalAverageLookCredit(List<Credit> credits) {
        Double averageLookCredit = 0.0;
        for(Credit c : credits) {
               averageLookCredit += c.getLookCredit();
        }
        averageLookCredit /= credits.size();
        return averageLookCredit;
    }

    public Double CalAverageSmellCredit(List<Credit> credits) {
        Double averageSmellCredit = 0.0;
        for(Credit c : credits) {
            averageSmellCredit += c.getSmellCredit();
        }
        averageSmellCredit /= credits.size();
        return averageSmellCredit;
    }

    public Double CalAverageTasteCredit(List<Credit> credits) {
        Double averageTasteCredit = 0.0;
        for(Credit c : credits) {
            averageTasteCredit += c.getTasteCredit();
        }
        averageTasteCredit /= credits.size();
        return averageTasteCredit;
    }
}
