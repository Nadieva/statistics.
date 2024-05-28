package com.nadiamm.statistics.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserAgentBlacklistTest {
    @Test
    public void testUserAgentBlacklistConstructor() {
        String userAgent = "Test User Agent";
        UserAgentBlacklist userAgentBlacklist = new UserAgentBlacklist(userAgent);
        assertEquals(userAgent, userAgentBlacklist.getUserAgent());
    }
}