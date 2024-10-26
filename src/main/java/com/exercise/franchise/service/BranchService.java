package com.exercise.franchise.service;

import com.exercise.franchise.model.Branch;
import com.exercise.franchise.model.Franchise;
import com.exercise.franchise.repository.BranchRepository;
import com.exercise.franchise.repository.FranchiseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class BranchService {

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private FranchiseRepository franchiseRepository;

    public Branch addBranch(Long franchiseId, Branch branch) {
        Franchise franchise = franchiseRepository.findById(franchiseId)
                .orElseThrow(() -> new RuntimeException("Franchise not found"));
        branch.setFranchise(franchise);

        if (!franchise.getBranches().contains(branch)) {
            franchise.getBranches().add(branch);
        }

        return branchRepository.save(branch);
    }

    public Optional<Branch> getBranchById(Long id) {
        return branchRepository.findById(id);
    }
}

