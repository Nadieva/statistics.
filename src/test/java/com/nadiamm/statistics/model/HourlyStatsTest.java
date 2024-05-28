package com.nadiamm.statistics.model;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

public class HourlyStatsTest {

    private final long customerId =1L;
    private final int time = 1619718000;
    private final int validRequestCount=5;
    private final int invalidRequestCount=4;

    @Test
    public void createHourlyStats(){
        HourlyStats mockStats = new HourlyStats(customerId,time,validRequestCount,invalidRequestCount);

        assertEquals(customerId, mockStats.getCustomerId());
        assertEquals(new Timestamp(time), mockStats.getTime());
        assertEquals(validRequestCount,mockStats.getValidRequestCount());
        assertEquals(invalidRequestCount,mockStats.getInvalidRequestCount());
    }

    @Test
    public void updateHourlyStats(){
        HourlyStats mockStats = new HourlyStats(customerId,time,validRequestCount,invalidRequestCount);
        long id = mockStats.getId();
        long newId = mockStats.getId() + 10L;
        long newCustomerId =5L;
        int newTime = 1556787963;
        int newValidRequestCount=10;
        int newInvalidRequestCount=3;

        assertEquals(id,mockStats.getId());
        assertEquals(customerId, mockStats.getCustomerId());
        assertEquals(new Timestamp(time), mockStats.getTime());
        assertEquals(validRequestCount,mockStats.getValidRequestCount());
        assertEquals(invalidRequestCount,mockStats.getInvalidRequestCount());

        mockStats.setId(newId);
        mockStats.setCustomerId(newCustomerId);
        mockStats.setTime(new Timestamp(newTime));
        mockStats.setValidRequestCount(newValidRequestCount);
        mockStats.setInvalidRequestCount(newInvalidRequestCount);


        assertEquals(newCustomerId, mockStats.getCustomerId());
        assertEquals(new Timestamp(newTime), mockStats.getTime());
        assertEquals(newValidRequestCount,mockStats.getValidRequestCount());
        assertEquals(newInvalidRequestCount,mockStats.getInvalidRequestCount());
    }
}