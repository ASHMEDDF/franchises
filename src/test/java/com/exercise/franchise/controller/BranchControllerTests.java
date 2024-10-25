package com.exercise.franchise.controller;

import com.exercise.franchise.model.Branch;
import com.exercise.franchise.service.BranchService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(BranchController.class)
public class BranchControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BranchService branchService;

    @Test
    public void testAddBranch() throws Exception {
        Branch branch = new Branch();
        branch.setId(1L);
        branch.setName("New Branch");

        Long franchiseId = 1L;

        given(branchService.addBranch(Mockito.any(Long.class), Mockito.any(Branch.class))).willReturn(branch);

        String branchJson = "{ \"name\": \"New Branch\" }";

        mockMvc.perform(post("/branches/{franchiseId}", franchiseId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(branchJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("New Branch"));
    }
}
