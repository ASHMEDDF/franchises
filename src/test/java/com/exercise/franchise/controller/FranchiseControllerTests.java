package com.exercise.franchise.controller;

import com.exercise.franchise.model.Franchise;
import com.exercise.franchise.service.FranchiseService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(FranchiseController.class)
public class FranchiseControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FranchiseService franchiseService;

    @Test
    public void testGetAllFranchises() throws Exception {
        Franchise franchise1 = new Franchise();
        franchise1.setId(1L);
        franchise1.setName("Franchise 1");

        Franchise franchise2 = new Franchise();
        franchise2.setId(2L);
        franchise2.setName("Franchise 2");

        List<Franchise> franchises = Arrays.asList(franchise1, franchise2);

        given(franchiseService.getAllFranchises()).willReturn(franchises);

        mockMvc.perform(MockMvcRequestBuilders.get("/franchises")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Franchise 1"))
                .andExpect(jsonPath("$[1].name").value("Franchise 2"));
    }

    @Test
    public void testAddFranchise() throws Exception {
        Franchise franchise = new Franchise();
        franchise.setId(1L);
        franchise.setName("New Franchise");

        given(franchiseService.addFranchise(Mockito.any(Franchise.class))).willReturn(franchise);

        String franchiseJson = "{ \"name\": \"New Franchise\" }";

        mockMvc.perform(MockMvcRequestBuilders.post("/franchises")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(franchiseJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("New Franchise"));
    }
}
