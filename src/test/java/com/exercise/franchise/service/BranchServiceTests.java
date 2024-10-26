package com.exercise.franchise.service;

import com.exercise.franchise.model.Branch;
import com.exercise.franchise.model.Franchise;
import com.exercise.franchise.repository.BranchRepository;
import com.exercise.franchise.repository.FranchiseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BranchServiceTests {

    @Mock
    private BranchRepository branchRepository;

    @Mock
    private FranchiseRepository franchiseRepository;

    @InjectMocks
    private BranchService branchService;

    @Test
    public void testAddBranch() {
        Franchise franchise = new Franchise();
        franchise.setId(1L);
        franchise.setBranches(new ArrayList<>()); // Inicializar la lista de sucursales en Franchise

        Branch branch = new Branch();
        branch.setId(1L);

        when(franchiseRepository.findById(1L)).thenReturn(Optional.of(franchise));
        when(branchRepository.save(any(Branch.class))).thenReturn(branch);

        Branch savedBranch = branchService.addBranch(1L, branch);

        assertNotNull(savedBranch);
        assertEquals(1L, savedBranch.getId());
        assertEquals(franchise, savedBranch.getFranchise());
        assertTrue(franchise.getBranches().contains(savedBranch)); // Verificar que la sucursal fue añadida a la lista de sucursales de Franchise
    }

    @Test
    public void testAddBranch_FranchiseNotFound() {
        Branch branch = new Branch();
        branch.setId(1L);

        when(franchiseRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            branchService.addBranch(1L, branch);
        });

        assertEquals("Franchise not found", exception.getMessage());
        verify(branchRepository, never()).save(any());
    }

    @Test
    public void testGetBranchById() {
        Franchise franchise = new Franchise();
        franchise.setId(1L);

        Branch branch = new Branch();
        branch.setId(1L);
        branch.setFranchise(franchise);

        when(branchRepository.findById(1L)).thenReturn(Optional.of(branch));

        Optional<Branch> foundBranch = branchService.getBranchById(1L);

        assertTrue(foundBranch.isPresent());
        assertEquals(1L, foundBranch.get().getId());
        assertEquals(franchise, foundBranch.get().getFranchise());
    }

    @Test
    public void testGetBranchById_NotFound() {
        when(branchRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Branch> foundBranch = branchService.getBranchById(1L);

        assertFalse(foundBranch.isPresent());
        verify(branchRepository).findById(1L);
    }
}