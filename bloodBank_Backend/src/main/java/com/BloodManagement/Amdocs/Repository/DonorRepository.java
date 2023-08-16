package com.BloodManagement.Amdocs.Repository;

import com.BloodManagement.Amdocs.Entity.Donor;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DonorRepository extends MongoRepository<Donor, String> {
    List<Donor> findByName(String name);
    List<Donor> findByBloodGroup(String bloodGroup);
    List<Donor> findByNameAndBloodGroup(String name, String bloodGroup);
}