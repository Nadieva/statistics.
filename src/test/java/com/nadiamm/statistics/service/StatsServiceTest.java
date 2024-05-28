package com.nadiamm.statistics.service;

import com.nadiamm.statistics.repository.CustomerRepository;
import com.nadiamm.statistics.repository.HourlyStatsRepository;
import com.nadiamm.statistics.repository.RequestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StatsServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private RequestRepository requestRepository;

    @Mock
    private HourlyStatsRepository hourlyStatsRepository;

    @Mock
    private FileService fileService;

    @InjectMocks
    private StatsService statsService;

    private final long customerID = 1L;
    private final long tagID = 2L;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindValidCustomerIDs() {
        long id = 1L;
        List<Long> expectedCustomerIDs = Arrays.asList(1L, 2L);
        when(customerRepository.findValidCustomerIDList(id)).thenReturn(expectedCustomerIDs);

        List<Long> customerIDs = statsService.findValidCustomerIDs(id);

        assertEquals(expectedCustomerIDs, customerIDs);
        verify(customerRepository, times(1)).findValidCustomerIDList(id);
        verifyNoMoreInteractions(customerRepository);
    }

    @Test
    public void testGetTotalRequestByCustomerIDAndTagID() throws IOException {
        when(customerRepository.findValidCustomerIDList(customerID)).thenReturn(Collections.emptyList());
        when(requestRepository.count()).thenReturn(0L);

        long totalRequests = statsService.getTotalRequestByCustomerIDAndTagID(customerID, tagID);

        assertEquals(0L, totalRequests);
        verify(customerRepository, times(1)).findValidCustomerIDList(customerID);
        verify(requestRepository, times(1)).count();
        verifyNoMoreInteractions(requestRepository, customerRepository);
    }

    @Test
    public void testClearRequestRepository() {
        statsService.clearRequestRepository();
        verify(requestRepository, times(1)).deleteAll();
        verifyNoMoreInteractions(requestRepository);
    }

    @Test
    public void testClearHourlyStatsRepository() {
        statsService.clearHourlyStatsRepository();
        verify(hourlyStatsRepository, times(1)).deleteAll();
        verifyNoMoreInteractions(hourlyStatsRepository);
    }
}