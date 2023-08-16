package com.BloodManagement.Amdocs.Controller;


import com.BloodManagement.Amdocs.Entity.Donor;
import com.BloodManagement.Amdocs.Repository.DonorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Donors")
@CrossOrigin
public class DonorController {
    @Autowired
    private DonorRepository donorRepository;
    @PostMapping("/add")
    public ResponseEntity<?> addDonor(@RequestBody Donor donor){
        Donor existingDonor = donorRepository.findById(donor.getId()).orElse(null);

        if (existingDonor != null) {
            return ResponseEntity.badRequest().body("Donor with ID " + donor.getId() + " already exists");
        }
        else
        {
            Donor savedDonor = donorRepository.save(donor);
            return ResponseEntity.ok(savedDonor);
        }
    }

    @DeleteMapping("/delete/{donorId}")
    public ResponseEntity<String> deleteDonor(@PathVariable String donorId) {
        Optional<Donor> donorOptional = donorRepository.findById(donorId);
        if (donorOptional.isPresent()) {
            donorRepository.deleteById(donorId);
            return ResponseEntity.ok("Donor deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Donor> updateDonor(@RequestBody Donor donor) {
        Optional<Donor> donorOptional = donorRepository.findById(donor.getId());
        if (donorOptional.isPresent()) {
            Donor updatedDonor = donorRepository.save(donor);
            return ResponseEntity.ok(updatedDonor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public List<Donor> searchDonors(@RequestParam(value = "name", required = false) String name,
                                    @RequestParam(value = "bloodGroup", required = false) String bloodGroup) {
        if (name != null && bloodGroup != null) {
            return donorRepository.findByNameAndBloodGroup(name, bloodGroup);
        } else if (name != null) {
            return donorRepository.findByName(name);
        } else if (bloodGroup != null) {
            return donorRepository.findByBloodGroup(bloodGroup);
        } else {
            return donorRepository.findAll();
        }
    }

    @GetMapping("/findAll")
    public List<Donor> displayAll(){
        return donorRepository.findAll();
    }


}