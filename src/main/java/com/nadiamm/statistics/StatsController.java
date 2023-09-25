package com.nadiamm.statistics;

import java.io.IOException;
import java.util.List;

import com.nadiamm.statistics.model.HourlyStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StatsController {
	
	@Autowired
    private StatsService statsService;
	
	@GetMapping(path="/total")
	public ResponseEntity<Long> getRequestRepositorySize(@RequestParam String customerID, @RequestParam String tagID ) throws IOException {
		statsService.clearRequestRepository();
		 return ResponseEntity.ok(statsService.getTotalRequestByCustomerIDAndTagID(Integer.parseInt(customerID),Integer.parseInt(tagID)));
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
