package com.nadiamm.statistics;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nadiamm.statistics.model.HourlyStats;
import com.nadiamm.statistics.model.RequestList;
import com.nadiamm.statistics.repository.CustomerRepository;
import com.nadiamm.statistics.repository.HourlyStatsRepository;
import com.nadiamm.statistics.repository.RequestRepository;
import com.nadiamm.statistics.service.StatsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
public class StatsController {
	
	@Autowired
    private StatsService statsService;
	
	@GetMapping(path="/total")
	public ResponseEntity<Long> getRequestRepositorySize(@RequestParam String customerID, @RequestParam String tagID ) throws IOException {
		statsService.clearRequestRepository();
		 return ResponseEntity.ok(statsService.getTotalRequestByCustomerIDAndTagID(Integer.parseInt(customerID),Long.parseLong(tagID)));
	}	
	
	@GetMapping(path="/statistics")
	public List<HourlyStats> getValidCustomerIDList(@RequestParam String customerID, @RequestParam String tagID) throws IOException {
        List<Long> validCustomerIDs=statsService.findValidCustomerIDs(Long.parseLong(customerID));
        if(validCustomerIDs.contains(Long.valueOf(customerID))) {
        	statsService.loadRequestByTagIDAndByCustomerIDFromJSONFile(Long.parseLong(customerID),Long.parseLong(tagID));
        	List<Integer> timestampList=statsService.getTimestampList();
        	timestampList.forEach(time -> {
        		int validRequestCount=statsService.findTotalValidRequestByTimestamp(time);
        		int invalidRequestCount=statsService.findTotalInvalidRequestByTimestamp(time);
        		HourlyStats hourlyStats=new HourlyStats(Long.parseLong(customerID),time,validRequestCount,invalidRequestCount);
        		statsService.save(hourlyStats);
        	});
        }
		 return statsService.findHourlyStats();
	}

}
