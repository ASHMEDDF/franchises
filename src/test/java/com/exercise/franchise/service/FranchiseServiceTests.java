package com.exercise.franchise.service;

import com.exercise.franchise.model.Franchise;
import com.exercise.franchise.repository.FranchiseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FranchiseServiceTests {

    @Mock
    private FranchiseRepository franchiseRepository;

    @InjectMocks
    private FranchiseService franchiseService;

    @Test
    public void testAddFranchise() {
        Franchise franchise = new Franchise();
        franchise.setName("New Franchise");

        when(franchiseRepository.save(Mockito.any(Franchise.class))).thenReturn(franchise);

        Franchise savedFranchise = franchiseService.addFranchise(franchise);

        assertEquals("New Franchise", savedFranchise.getName());
    }

    @Test
    public void testGetAllFranchises() {
        Franchise franchise1 = new Franchise();
        franchise1.setId(1L);
        franchise1.setName("Franchise 1");

        Franchise franchise2 = new Franchise();
        franchise2.setId(2L);
        franchise2.setName("Franchise 2");

        List<Franchise> franchises = Arrays.asList(franchise1, franchise2);

        when(franchiseRepository.findAll()).thenReturn(franchises);

        List<Franchise> result = franchiseService.getAllFranchises();

        assertEquals(2, result.size());
        assertEquals("Franchise 1", result.get(0).getName());
        assertEquals("Franchise 2", result.get(1).getName());
    }

    @Test
    public void testGetFranchiseById() {
        Franchise franchise = new Franchise();
        franchise.setId(1L);
        franchise.setName("Franchise 1");

        when(franchiseRepository.findById(1L)).thenReturn(Optional.of(franchise));

        Optional<Franchise> foundFranchise = franchiseService.getFranchiseById(1L);

        assertTrue(foundFranchise.isPresent());
        assertEquals("Franchise 1", foundFranchise.get().getName());
    }
}
