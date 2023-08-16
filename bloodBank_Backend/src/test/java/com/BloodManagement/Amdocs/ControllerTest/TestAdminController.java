package com.BloodManagement.Amdocs.ControllerTest;

import com.BloodManagement.Amdocs.Controller.AdminController;
import com.BloodManagement.Amdocs.Entity.Admin;
import com.BloodManagement.Amdocs.Repository.AdminRepository;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TestAdminController {

    @InjectMocks
    private AdminController adminController;

    @Mock
    private AdminRepository adminRepository;

    @Mock
    private HttpSession httpSession;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testLoginSuccess() {
        Admin existingAdmin = new Admin();
        existingAdmin.setUsername("admin");
        existingAdmin.setPassword(new BCryptPasswordEncoder().encode("password"));

        when(adminRepository.findByUsername("admin")).thenReturn(existingAdmin);
        when(httpSession.getAttribute("admin")).thenReturn(existingAdmin);

        Admin admin = new Admin();
        admin.setUsername("admin");
        admin.setPassword("password");

        ResponseEntity<?> response = adminController.login(admin, httpSession);

        assertEquals(ResponseEntity.ok("User Authenticated Successfully"), response);
    }

    @Test
    public void testLoginFailure() {
        when(adminRepository.findByUsername("admin")).thenReturn(null);

        Admin admin = new Admin();
        admin.setUsername("admin");
        admin.setPassword("invalidPassword");

        ResponseEntity<?> response = adminController.login(admin, httpSession);

        assertEquals(ResponseEntity.badRequest().body("Invalid Admin Email id or Password"), response);
    }

    @Test
    public void testRegisterNewAdmin() {
        // Given
        Admin adminToRegister = new Admin();
        adminToRegister.setUsername("newadmin");
        adminToRegister.setPassword("newpassword");

        when(adminRepository.findByUsername(adminToRegister.getUsername())).thenReturn(null);

        // When
        ResponseEntity<?> response = adminController.register(adminToRegister);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Admin registered successfully by management", response.getBody());
        verify(adminRepository, times(1)).save(any(Admin.class));
    }

    @Test
    public void testRegisterExistingAdmin() {
        // Given
        Admin existingAdmin = new Admin();
        existingAdmin.setUsername("existingadmin");

        Admin adminToRegister = new Admin();
        adminToRegister.setUsername("existingadmin");
        adminToRegister.setPassword("newpassword");

        when(adminRepository.findByUsername(adminToRegister.getUsername())).thenReturn(existingAdmin);

        // When
        ResponseEntity<?> response = adminController.register(adminToRegister);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Admin already exists, please login with valid credentials", response.getBody());
        verify(adminRepository, never()).save(any(Admin.class));
    }

}