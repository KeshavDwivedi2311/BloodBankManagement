package com.BloodManagement.Amdocs.Repository;

import com.BloodManagement.Amdocs.Entity.Donor;
import com.BloodManagement.Amdocs.Entity.Receiver;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReceiverRepository extends MongoRepository<Receiver,String> {
    List<Receiver> findByName(String name);
    List<Receiver> findByBloodGroup(String bloodGroup);
    List<Receiver> findByNameAndBloodGroup(String name, String bloodGroup);
}