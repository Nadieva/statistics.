package com.nadiamm.statistics.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RequestTest {

    private final long mockCustomerID = 5L;
    private final int mockTagID =1;
    private final int mockTime = 1559989898;


    @Test
    public void testSetRequestValid(){
        Request request = new Request(mockCustomerID,mockTagID,"aaaaa22","123.X78",mockTime);
        boolean isValid = request.getValid();
        request.setValid(!isValid);
        assertNotEquals(isValid,request.getValid());
    }

    @Test
    public void testInvalidUSerIDAndRemoteIP(){
        Request request = new Request(mockCustomerID,mockTagID,"aaaaa22","123.X78",mockTime);
        assertFalse(request.isValid());
    }

    @Test
    public void testInvalidUserID(){
        Request request = new Request(mockCustomerID,mockTagID,"aaaaa22","123.234.56.78",mockTime);
        assertFalse(request.isValid());
    }

    @Test
    public void testInvalidRemoteIP(){
        Request request = new Request(mockCustomerID,mockTagID,"aaaaaaaa-bbbb-cccc-1111-222222222222","123.X78",mockTime);
        assertFalse(request.isValid());
    }

    @Test
    public void testValidRequest(){
        Request request = new Request(mockCustomerID,mockTagID,"aaaaaaaa-bbbb-cccc-1111-222222222222","123.234.56.78",mockTime);
        assertTrue(request.isValid());
    }
}