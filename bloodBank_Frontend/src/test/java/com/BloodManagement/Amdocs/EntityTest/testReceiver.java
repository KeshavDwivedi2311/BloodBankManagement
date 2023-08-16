package com.BloodManagement.Amdocs.EntityTest;

import com.BloodManagement.Amdocs.Entity.Donor;
import com.BloodManagement.Amdocs.Entity.Receiver;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testReceiver {
    @Test
    public void testGetAndSetId() {
        Receiver receiver= new Receiver();
        String id="Receiver_110";
        receiver.setId(id);
        String retrievedId= receiver.getId();

        assertEquals(id, retrievedId);
    }

    @Test
    public void testGetAndSetReceiverName() {
        Receiver receiver= new Receiver();
        String name="Receiver_Testing";

        receiver.setName(name);
        String retrivedDonorName= receiver.getName();
        assertEquals(name, retrivedDonorName);
    }
    @Test
    public void testGetAndSetBloodGroup() {
        Receiver receiver= new Receiver();
        String bloodGroup= "O+";
        receiver.setBloodGroup(bloodGroup);
        String retrievedBloodGroup= receiver.getBloodGroup();
        assertEquals(bloodGroup,retrievedBloodGroup);
    }
}
