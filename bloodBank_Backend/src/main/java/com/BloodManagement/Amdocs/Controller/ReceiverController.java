package com.BloodManagement.Amdocs.Controller;


import com.BloodManagement.Amdocs.Entity.Donor;
import com.BloodManagement.Amdocs.Entity.Receiver;
import com.BloodManagement.Amdocs.Repository.ReceiverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Receivers")
@CrossOrigin
public class ReceiverController {
    @Autowired
    private ReceiverRepository receiverRepository;
    @PostMapping("/add")
    public ResponseEntity<Receiver> addReceiver(@RequestBody Receiver receiver) {
        Receiver existingReceiver = receiverRepository.findById(receiver.getId()).orElse(null);

        if (existingReceiver != null) {
            return ResponseEntity.badRequest().body(existingReceiver);
        }
        else {
            Receiver savedReceiver = receiverRepository.save(receiver);
            return ResponseEntity.ok(savedReceiver);
        }
    }

    @DeleteMapping("/delete/{receiverId}")
    public ResponseEntity<String> deleteReceiver(@PathVariable String receiverId) {
        Optional<Receiver> receiverOptional = receiverRepository.findById(receiverId);
        if (receiverOptional.isPresent()) {
            receiverRepository.deleteById(receiverId);
            return ResponseEntity.ok("Receiver deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Receiver> updateReceiver(@RequestBody Receiver receiver) {
        Optional<Receiver> receiverOptional = receiverRepository.findById(receiver.getId());
        if (receiverOptional.isPresent()) {
            Receiver updatedReceiver = receiverRepository.save(receiver);
            return ResponseEntity.ok(updatedReceiver);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public List<Receiver> searchReceivers(@RequestParam(value = "name", required = false) String name,
                                    @RequestParam(value = "bloodGroup", required = false) String bloodGroup) {
        if (name != null && bloodGroup != null) {
            return receiverRepository.findByNameAndBloodGroup(name, bloodGroup);
        } else if (name != null) {
            return receiverRepository.findByName(name);
        } else if (bloodGroup != null) {
            return receiverRepository.findByBloodGroup(bloodGroup);
        } else {
            return receiverRepository.findAll();
        }
    }





    @GetMapping("/findAll")
    public List<Receiver> displayAll(){
        return receiverRepository.findAll();
    }

}