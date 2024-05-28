package com.nadiamm.statistics.model;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RequestListTest {

    @Mock
    Request mockRequest1;
    @Mock
    Request mockRequest2;

    @Test
    public void testGetRequestList() {
        List<Request> mockRequests = new ArrayList<>();
        mockRequests.add(mockRequest1);
        mockRequests.add(mockRequest2);

        RequestList requestList = new RequestList();
        requestList.setRequestList(mockRequests);

        assertEquals(mockRequests, requestList.getRequestList());
        assertEquals(mockRequests.size(), requestList.getRequestList().size());
    }

}