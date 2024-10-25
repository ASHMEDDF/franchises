package com.exercise.franchise.controller;

import com.exercise.franchise.model.Branch;
import com.exercise.franchise.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/branches")
public class BranchController {

    @Autowired
    private BranchService branchService;

    @PostMapping("/{franchiseId}")
    public Branch addBranch(@PathVariable Long franchiseId, @RequestBody Branch branch) {
        return branchService.addBranch(franchiseId, branch);
    }

}
