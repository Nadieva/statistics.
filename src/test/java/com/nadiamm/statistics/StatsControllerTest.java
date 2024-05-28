package com.nadiamm.statistics;

import com.nadiamm.statistics.model.HourlyStats;
import com.nadiamm.statistics.service.StatsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Arrays;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class StatsControllerTest {

    @Mock
    private StatsService statsService;

    @InjectMocks
    private StatsController statsController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetRequestRepositorySize() throws IOException {

        long expectedSize = 10;
        when(statsService.getTotalRequestByCustomerIDAndTagID(anyLong(), anyLong())).thenReturn(expectedSize);

        ResponseEntity<Long> response = statsController.getRequestRepositorySize("123", "456");

        assertEquals(expectedSize, response.getBody());
    }

    @Test
    public void testGetValidCustomerIDList() throws IOException {

        long customerId = 123;
        long tagId = 456;
        List<HourlyStats> expectedStats = Arrays.asList(new HourlyStats(customerId, 1, 5, 2),
                new HourlyStats(customerId, 2, 3, 1));
        when(statsController.getValidCustomerIDList(String.valueOf(customerId), String.valueOf(tagId))).thenReturn(expectedStats);

        List<HourlyStats> response = statsController.getValidCustomerIDList(Long.toString(customerId), Long.toString(tagId));

        assertEquals(expectedStats.size(), response.size());
        assertEquals(expectedStats.get(0).getValidRequestCount(), response.get(0).getValidRequestCount());
        assertEquals(expectedStats.get(1).getInvalidRequestCount(), response.get(1).getInvalidRequestCount());
    }
}