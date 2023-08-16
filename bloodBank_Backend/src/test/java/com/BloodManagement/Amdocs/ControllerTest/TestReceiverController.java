package com.BloodManagement.Amdocs.ControllerTest;

import com.BloodManagement.Amdocs.Controller.ReceiverController;
import com.BloodManagement.Amdocs.Entity.Receiver;
import com.BloodManagement.Amdocs.Repository.ReceiverRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TestReceiverController {
    @InjectMocks
    private ReceiverController receiverController;

    @Mock
    private ReceiverRepository receiverRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddReceiver() {
        Receiver receiverToAdd = new Receiver();
        receiverToAdd.setId("1");
        receiverToAdd.setName("Alice");
        receiverToAdd.setBloodGroup("AB+");

        when(receiverRepository.save(receiverToAdd)).thenReturn(receiverToAdd);

        ResponseEntity<Receiver> response = receiverController.addReceiver(receiverToAdd);

        assertEquals(ResponseEntity.ok(receiverToAdd), response);
    }

    @Test
    public void testDeleteExistingReceiver() {
        String receiverId = "1";
        Optional<Receiver> receiverOptional = Optional.of(new Receiver());

        when(receiverRepository.findById(receiverId)).thenReturn(receiverOptional);

        ResponseEntity<String> response = receiverController.deleteReceiver(receiverId);

        assertEquals(ResponseEntity.ok("Receiver deleted successfully"), response);
        verify(receiverRepository, times(1)).deleteById(receiverId);
    }

    @Test
    public void testDeleteNonExistingReceiver() {
        String receiverId = "1";
        Optional<Receiver> receiverOptional = Optional.empty();

        when(receiverRepository.findById(receiverId)).thenReturn(receiverOptional);

        ResponseEntity<String> response = receiverController.deleteReceiver(receiverId);

        assertEquals(ResponseEntity.notFound().build(), response);
        verify(receiverRepository, never()).deleteById(any());
    }

    @Test
    public void testUpdateExistingReceiver() {
        Receiver receiverToUpdate = new Receiver();
        receiverToUpdate.setId("1");
        receiverToUpdate.setName("Updated Name");
        receiverToUpdate.setBloodGroup("O-");

        Optional<Receiver> receiverOptional = Optional.of(receiverToUpdate);
        when(receiverRepository.findById("1")).thenReturn(receiverOptional);
        when(receiverRepository.save(receiverToUpdate)).thenReturn(receiverToUpdate);

        ResponseEntity<Receiver> response = receiverController.updateReceiver(receiverToUpdate);

        assertEquals(ResponseEntity.ok(receiverToUpdate), response);
    }

    @Test
    public void testUpdateNonExistingReceiver() {
        Receiver receiverToUpdate = new Receiver();
        receiverToUpdate.setId("1");
        receiverToUpdate.setName("Updated Name");
        receiverToUpdate.setBloodGroup("O-");

        Optional<Receiver> receiverOptional = Optional.empty();
        when(receiverRepository.findById("1")).thenReturn(receiverOptional);

        ResponseEntity<Receiver> response = receiverController.updateReceiver(receiverToUpdate);

        assertEquals(ResponseEntity.notFound().build(), response);
        verify(receiverRepository, never()).save(any());
    }
    @Test
    public void testSearchReceiversByNameAndBloodGroup() {
        // Given
        String name = "Keshav";
        String bloodGroup = "B-";
        List<Receiver> expectedReceivers = new ArrayList<>();
        when(receiverRepository.findByNameAndBloodGroup(name, bloodGroup)).thenReturn(expectedReceivers);

        // When
        List<Receiver> actualReceivers = receiverController.searchReceivers(name, bloodGroup);

        // Then
        assertEquals(expectedReceivers, actualReceivers);
    }

    @Test
    public void testSearchReceiversByName() {
        // Given
        String name = "Keshav";
        List<Receiver> expectedReceivers = new ArrayList<>();
        when(receiverRepository.findByName(name)).thenReturn(expectedReceivers);

        // When
        List<Receiver> actualReceivers = receiverController.searchReceivers(name, null);

        // Then
        assertEquals(expectedReceivers, actualReceivers);
    }

    @Test
    public void testSearchReceiversByBloodGroup() {
        // Given
        String bloodGroup = "B-";
        List<Receiver> expectedReceivers = new ArrayList<>();
        when(receiverRepository.findByBloodGroup(bloodGroup)).thenReturn(expectedReceivers);


        List<Receiver> actualReceivers = receiverController.searchReceivers(null, bloodGroup);
        assertEquals(expectedReceivers, actualReceivers);
    }

    @Test
    public void testSearchReceiversWithNoParameters() {
        // Given
        List<Receiver> expectedReceivers = new ArrayList<>();
        when(receiverRepository.findAll()).thenReturn(expectedReceivers);

        // When
        List<Receiver> actualReceivers = receiverController.searchReceivers(null, null);

        // Then
        assertEquals(expectedReceivers, actualReceivers);
    }
}
