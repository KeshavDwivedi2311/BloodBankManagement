package com.BloodManagement.Amdocs.EntityTest;

import com.BloodManagement.Amdocs.Entity.Donor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testDonor {

    @Test
    public void testGetAndSetId() {
        Donor donor= new Donor();
        String id="Donor_110";
        donor.setId(id);
        String retrievedId= donor.getId();

        assertEquals(id, retrievedId);
    }

    @Test
    public void testGetAndSetDonorName() {
        Donor donor= new Donor();
        String name="Donor_Testing";

        donor.setName(name);
        String retrivedDonorName= donor.getName();
        assertEquals(name, retrivedDonorName);
    }
    @Test
    public void testGetAndSetBloodGroup() {
        Donor donor= new Donor();
        String bloodGroup= "O+";
        donor.setBloodGroup(bloodGroup);
        String retrievedBloodGroup= donor.getBloodGroup();
        assertEquals(bloodGroup,retrievedBloodGroup);
    }
}
