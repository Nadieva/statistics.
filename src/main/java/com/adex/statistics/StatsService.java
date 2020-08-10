package com.adex.statistics;

import java.io.*;
import java.util.List;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;
import com.adex.statistics.entity.HourlyStats;
import com.adex.statistics.entity.RequestList;
import com.google.gson.*;

@Service
public class StatsService {
	
	@Autowired
	@Value("${jsondata.path}")
	private String jsonFilename;

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private RequestRepository requestRepository;
	
	@Autowired
	private HourlyStatsRespository hourlyStatsRepository;
	
	public void loadRequestByTagIDAndByCustomerIDFromJSONFile(long customerID, long tagID) {
	    BufferedReader reader = null;
		clearRequestRepository();
	    clearHourlyStatsRepository();
	    try{
	        reader = new BufferedReader(new FileReader(jsonFilename));
	        Gson gson = new GsonBuilder().create();
	        RequestList result= gson.fromJson(reader, RequestList.class);
	        result.getRequestList().forEach(request -> { 
	        	if(request.getCustomerID()==Long.valueOf(customerID) && request.getTagID()== Long.valueOf(tagID)){
	        		request.setValid(request.isValid());
	        		requestRepository.save(request);
		        }
	        });   
	        requestRepository.updateValidityFromBlacklist();
	        }catch(Exception e){
	        	e.printStackTrace();
	        }finally{
	            if(reader != null){
	                try{
	                    reader.close();
	                }catch(Exception e){
	                    e.printStackTrace();
	                }

	            }
	        }

   }
	

	public long getTotalRequestByCustomerIDAndTagID(long customerID, int tagID) {
        List<Long> validCustomerIDs=findValidCustomerIDs(Long.valueOf(customerID));
        if(validCustomerIDs.contains(Long.valueOf(customerID))) {
        	loadRequestByTagIDAndByCustomerIDFromJSONFile(Long.valueOf(customerID),Integer.valueOf(tagID));
        }
		return requestRepository.count();
	}

	public List<Long> findValidCustomerIDs(long id) {
		return customerRepository.findValidCustomerIDList(Long.valueOf(id));
	}


	public void clearRequestRepository() {
		requestRepository.deleteAll();
	}

	public void clearHourlyStatsRepository() {
		hourlyStatsRepository.deleteAll();
	}

	public List<Integer> getTimestampList() {
		return requestRepository.getTimestampList();
	}

	public int findTotalValidRequestByTimestamp(int time) {
		return requestRepository.getTotalValidRequestByTimestamp(time) == null ? 0:Integer.valueOf(requestRepository.getTotalValidRequestByTimestamp(time));
	}
	
	public int findTotalInvalidRequestByTimestamp(int time) {
		return requestRepository.getTotalInvalidRequestByTimestamp(time) == null ? 0:Integer.valueOf(requestRepository.getTotalInvalidRequestByTimestamp(time));
	}


	public void save(HourlyStats hourlyStats) {
		hourlyStatsRepository.save(hourlyStats);
	}
	
	public List<HourlyStats> findHourlyStats() {
		return hourlyStatsRepository.getAllHourlyStats();
	}
}
