package com.BloodManagement.Amdocs.Repository;

import com.BloodManagement.Amdocs.Entity.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdminRepository extends MongoRepository<Admin, String> {
    Admin findByUsername(String username);
}
