package com.exercise.franchise.service;

import com.exercise.franchise.model.Franchise;
import com.exercise.franchise.repository.FranchiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FranchiseService {

    @Autowired
    private FranchiseRepository franchiseRepository;

    public Franchise addFranchise(Franchise franchise) {
        return franchiseRepository.save(franchise);
    }

    public List<Franchise> getAllFranchises() {
        return franchiseRepository.findAll();
    }

    public Optional<Franchise> getFranchiseById(Long id) {
        return franchiseRepository.findById(id);
    }
}
