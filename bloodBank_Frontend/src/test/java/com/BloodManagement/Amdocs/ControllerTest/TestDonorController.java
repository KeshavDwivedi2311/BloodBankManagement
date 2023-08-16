package com.BloodManagement.Amdocs.ControllerTest;

import com.BloodManagement.Amdocs.Controller.DonorController;
import com.BloodManagement.Amdocs.Entity.Donor;
import com.BloodManagement.Amdocs.Repository.DonorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class TestDonorController {
    @InjectMocks
    private DonorController donorController;
    @Mock
    private DonorRepository donorRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testAddDonor() {
        Donor donorToAdd = new Donor();
        donorToAdd.setId("1");
        donorToAdd.setName("Animesh Rajput");
        donorToAdd.setBloodGroup("Negative");

        when(donorRepository.save(donorToAdd)).thenReturn(donorToAdd);

        ResponseEntity<?> response = donorController.addDonor(donorToAdd);

        assertEquals(ResponseEntity.ok(donorToAdd), response);
    }

    @Test
    public void testDeleteExistingDonor() {
        String donorId = "1";
        Optional<Donor> donorOptional = Optional.of(new Donor());

        when(donorRepository.findById(donorId)).thenReturn(donorOptional);

        ResponseEntity<String> response = donorController.deleteDonor(donorId);

        assertEquals(ResponseEntity.ok("Donor deleted successfully"), response);
        verify(donorRepository, times(1)).deleteById(donorId);
    }

    @Test
    public void testDeleteNonExistingDonor() {
        String donorId = "1";
        Optional<Donor> donorOptional = Optional.empty();

        when(donorRepository.findById(donorId)).thenReturn(donorOptional);

        ResponseEntity<String> response = donorController.deleteDonor(donorId);

        assertEquals(ResponseEntity.notFound().build(), response);
        verify(donorRepository, never()).deleteById(any());
    }

    @Test
    public void testUpdateExistingDonor() {
        Donor donorToUpdate = new Donor();
        donorToUpdate.setId("1");
        donorToUpdate.setName("Updated Name");
        donorToUpdate.setBloodGroup("B+");

        Optional<Donor> donorOptional = Optional.of(donorToUpdate);
        when(donorRepository.findById("1")).thenReturn(donorOptional);
        when(donorRepository.save(donorToUpdate)).thenReturn(donorToUpdate);

        ResponseEntity<Donor> response = donorController.updateDonor(donorToUpdate);

        assertEquals(ResponseEntity.ok(donorToUpdate), response);
    }

    @Test
    public void testUpdateNonExistingDonor() {
        Donor donorToUpdate = new Donor();
        donorToUpdate.setId("1");
        donorToUpdate.setName("Updated Name");
        donorToUpdate.setBloodGroup("B+");

        Optional<Donor> donorOptional = Optional.empty();
        when(donorRepository.findById("1")).thenReturn(donorOptional);

        ResponseEntity<Donor> response = donorController.updateDonor(donorToUpdate);

        assertEquals(ResponseEntity.notFound().build(), response);
        verify(donorRepository, never()).save(any());
    }
    @Test
    public void testSearchDonorsByNameAndBloodGroup() {
        // Given
        String name = "Keshav";
        String bloodGroup = "A+";
        List<Donor> expectedDonors = new ArrayList<>();
        when(donorRepository.findByNameAndBloodGroup(name, bloodGroup)).thenReturn(expectedDonors);

        // When
        List<Donor> actualDonors = donorController.searchDonors(name, bloodGroup);

        // Then
        assertEquals(expectedDonors, actualDonors);
    }

    @Test
    public void testSearchDonorsByName() {
        // Given
        String name = "Keshav";
        List<Donor> expectedDonors = new ArrayList<>();
        when(donorRepository.findByName(name)).thenReturn(expectedDonors);

        // When
        List<Donor> actualDonors = donorController.searchDonors(name, null);

        // Then
        assertEquals(expectedDonors, actualDonors);
    }

    @Test
    public void testSearchDonorsByBloodGroup() {
        // Given
        String bloodGroup = "A+";
        List<Donor> expectedDonors = new ArrayList<>();
        when(donorRepository.findByBloodGroup(bloodGroup)).thenReturn(expectedDonors);

        // When
        List<Donor> actualDonors = donorController.searchDonors(null, bloodGroup);

        // Then
        assertEquals(expectedDonors, actualDonors);
    }

    @Test
    public void testSearchDonorsWithNoParameters() {
        // Given
        List<Donor> expectedDonors = new ArrayList<>();
        when(donorRepository.findAll()).thenReturn(expectedDonors);

        // When
        List<Donor> actualDonors = donorController.searchDonors(null, null);

        // Then
        assertEquals(expectedDonors, actualDonors);
    }
}
