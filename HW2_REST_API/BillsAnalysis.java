package com.acs560.bills_analyzer.services.impl;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.acs560.bills_analyzer.models.Bill;
import com.acs560.bills_analyzer.repositories.BillsRepository;
import com.acs560.bills_analyzer.services.BillsAnalysisService;
import com.acs560.bills_analyzer.DataAnalyzer;

@Service
public class BillsAnalysisServiceImpl implements BillsAnalysisService {

    private DataAnalyzer dataAnalyzer = new DataAnalyzer();

    @Override
    public double calculateAverage(String name, int month) throws NoSuchElementException {
        return BillsRepository.getBills().stream()
                .filter(b -> b.getUtilityName().equals(name) && b.getMonth() == month)
                .mapToDouble(Bill::getAmount)
                .average()
                .orElseThrow();
    }

    @Override
    public double calculateAverage(String name, int month, int range) {
        Set<Integer> months = getRange(month, range);
        return BillsRepository.getBills().stream()
                .filter(b -> b.getUtilityName().equals(name) && months.contains(b.getMonth()))
                .mapToDouble(Bill::getAmount)
                .average()
                .orElseThrow();
    }

    @Override
    public String getMostSellingItemReport() {
        return dataAnalyzer.analyzeData(BillsRepository.getBills());
    }

    private Set<Integer> getRange(int month, int range) {
        Set<Integer> months = new HashSet<>();
        for (int i = month - range; i <= month + range; i++) {
            months.add((i <= 0) ? i + 12 : i);
        }
        return months;
    }
}
