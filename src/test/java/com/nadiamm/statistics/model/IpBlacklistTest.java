package com.nadiamm.statistics.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IpBlacklistTest {

    private final long IP = 2130706433L;
    private final IpBlacklist mockIpBlacklist  = new IpBlacklist(IP);
    
    @Test
    public void getRemoteIP() {
        assertEquals(IP,mockIpBlacklist.getRemoteIP());
    }

    @Test
    public void updateRemoteIP() {
        long newIP = 5L;
        assertEquals(IP,mockIpBlacklist.getRemoteIP());

        mockIpBlacklist .setRemoteIP(newIP);
        assertEquals(newIP,mockIpBlacklist.getRemoteIP());
    }
}