package com.BloodManagement.Amdocs.Controller;


import com.BloodManagement.Amdocs.Entity.Admin;
import com.BloodManagement.Amdocs.Repository.AdminRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {
    @Autowired
    private AdminRepository adminRepository;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Admin admin, HttpSession httpSession){
        Admin existingAdmin= adminRepository.findByUsername((admin.getUsername()));
        if(existingAdmin==null || !new BCryptPasswordEncoder().matches(admin.getPassword(),existingAdmin.getPassword())){
            return ResponseEntity.badRequest().body("Invalid Admin Email id or Password");

        }
        httpSession.setAttribute("admin",existingAdmin);
        return ResponseEntity.ok("User Authenticated Successfully");
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Admin admin){
        Admin existingAdmin= adminRepository.findByUsername((admin.getUsername()));
        if(existingAdmin!=null ){
            return ResponseEntity.badRequest().body("Admin already exists, please login with valid credentials");
        }
        admin.setPassword(new BCryptPasswordEncoder().encode(admin.getPassword()));
        adminRepository.save(admin);
        return ResponseEntity.ok("Admin registered successfully by management");
    }
}
