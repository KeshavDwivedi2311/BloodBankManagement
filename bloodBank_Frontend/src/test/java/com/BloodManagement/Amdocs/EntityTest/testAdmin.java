package com.BloodManagement.Amdocs.EntityTest;

import com.BloodManagement.Amdocs.Entity.Admin;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class testAdmin {
    @Test
    public void testGetAndSetId() {
        // Given
        Admin admin = new Admin();
        String id = "123";

        // When
        admin.setId(id);
        String retrievedId = admin.getId();

        // Then
        assertEquals(id, retrievedId);
    }

    @Test
    public void testGetAndSetUsername() {
        // Given
        Admin admin = new Admin();
        String username = "admin";

        // When
        admin.setUsername(username);
        String retrievedUsername = admin.getUsername();

        // Then
        assertEquals(username, retrievedUsername);
    }

    @Test
    public void testGetAndSetPassword() {
        // Given
        Admin admin = new Admin();
        String password = "secret";

        // When
        admin.setPassword(password);
        String retrievedPassword = admin.getPassword();

        // Then
        assertEquals(password, retrievedPassword);
    }
    @Test
    public void testNotEquals() {
        // Given
        Admin admin1 = new Admin();
        admin1.setId("1");
        admin1.setUsername("admin");
        admin1.setPassword("secret");

        Admin admin2 = new Admin();
        admin2.setId("2");
        admin2.setUsername("admin");
        admin2.setPassword("secret");

        // Then
        assertNotEquals(admin1, admin2);
        assertNotEquals(admin1.hashCode(), admin2.hashCode());
    }
}
