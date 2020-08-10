package com.adex.statistics;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.adex.statistics.entity.HourlyStats;

@RestController
public class StatsController {
	
	@Autowired
    private StatsService statsService;
	
	@GetMapping(path="/total")
	public ResponseEntity<Long> getRequestRepositorySize(@RequestParam String customerID, @RequestParam String tagID ){
		statsService.clearRequestRepository();
		 return ResponseEntity.ok(statsService.getTotalRequestByCustomerIDAndTagID(Integer.valueOf(customerID),Integer.valueOf(tagID)));
	}	
	
	@GetMapping(path="/statistics")
	public List<HourlyStats> getValidCustomerIDList(@RequestParam String customerID,@RequestParam String tagID){
        List<Long> validCustomerIDs=statsService.findValidCustomerIDs(Long.valueOf(customerID));
        if(validCustomerIDs.contains(Long.valueOf(customerID))) {
        	statsService.loadRequestByTagIDAndByCustomerIDFromJSONFile(Long.valueOf(customerID),Long.valueOf(tagID));
        	List<Integer> timestampList=statsService.getTimestampList();
        	timestampList.forEach(time -> {
        		int validRequestCount=statsService.findTotalValidRequestByTimestamp(time);
        		int invalidRequestCount=statsService.findTotalInvalidRequestByTimestamp(time);
        		HourlyStats hourlyStats=new HourlyStats(Long.valueOf(customerID),time,validRequestCount,invalidRequestCount);
        		statsService.save(hourlyStats);
        	});
        }
		 return statsService.findHourlyStats();
	}
	
}
