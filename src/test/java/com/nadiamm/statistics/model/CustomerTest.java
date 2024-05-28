package com.nadiamm.statistics.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {

    private final String name = "John Doe";
    private final Customer mockCustomer =  new Customer(name);

    @Test
    public void createActiveCustomer() {
        assertNotNull(mockCustomer);
        assertEquals(name, mockCustomer.getName());
        assertTrue(mockCustomer.isActive());
    }

    @Test
    public void createInactiveCustomer() {
        Customer mockInactiveCustomer = mockCustomer;
        mockInactiveCustomer.setActive(false);
        assertNotNull(mockInactiveCustomer);
        assertEquals(name, mockInactiveCustomer.getName());
        assertFalse(mockInactiveCustomer.isActive());
    }

    @Test
    public void updateCustomer() {
        Customer updatedCustomer = mockCustomer;
        long newId = 5L;
        String newName = "Jean Renaud";

        assertEquals(mockCustomer.getId(), updatedCustomer.getId());
        assertEquals(mockCustomer.getName(), updatedCustomer.getName());
        assertTrue(updatedCustomer.isActive());

        updatedCustomer.setId(newId);
        updatedCustomer.setName(newName);
        updatedCustomer.setActive(false);

        assertEquals(newId, updatedCustomer.getId());
        assertEquals(newName, updatedCustomer.getName());
        assertFalse(updatedCustomer.isActive());
    }
}