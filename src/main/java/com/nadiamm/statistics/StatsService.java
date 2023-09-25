package com.nadiamm.statistics;

import java.io.*;
import java.nio.file.Files;
import java.util.List;

import com.nadiamm.statistics.model.HourlyStats;
import com.nadiamm.statistics.model.RequestList;
import com.nadiamm.statistics.repository.CustomerRepository;
import com.nadiamm.statistics.repository.HourlyStatsRespository;
import com.nadiamm.statistics.repository.RequestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
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

	private static final Logger LOGGER = LoggerFactory.getLogger(StatsController.class);

	public void loadRequestByTagIDAndByCustomerIDFromJSONFile(long customerID, long tagID) throws IOException {
		LOGGER.info("File reading...");
		String contentFile = readDataFile(jsonFilename);
		clearRequestRepository();
		clearHourlyStatsRepository();

		Gson gson = new GsonBuilder().create();
		RequestList result = gson.fromJson(contentFile, RequestList.class);
		result.getRequestList().forEach(request -> {
			if (request.getCustomerID() == customerID && request.getTagID() == tagID) {
				request.setValid(request.isValid());
				requestRepository.save(request);
			}
		});
			requestRepository.updateValidityFromBlacklist();
		LOGGER.info("File read successfully.");

   }
	

	public long getTotalRequestByCustomerIDAndTagID(long customerID, int tagID) throws IOException {
        List<Long> validCustomerIDs=findValidCustomerIDs(customerID);
        if(validCustomerIDs.contains(customerID)) {
        	loadRequestByTagIDAndByCustomerIDFromJSONFile(customerID, tagID);
        }
		return requestRepository.count();
	}

	public List<Long> findValidCustomerIDs(long id) {
		return customerRepository.findValidCustomerIDList(id);
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
		return requestRepository.getTotalValidRequestByTimestamp(time) == null ? 0:Integer.parseInt(requestRepository.getTotalValidRequestByTimestamp(time));
	}
	
	public int findTotalInvalidRequestByTimestamp(int time) {
		return requestRepository.getTotalInvalidRequestByTimestamp(time) == null ? 0:Integer.parseInt(requestRepository.getTotalInvalidRequestByTimestamp(time));
	}


	public void save(HourlyStats hourlyStats) {
		hourlyStatsRepository.save(hourlyStats);
	}
	
	public List<HourlyStats> findHourlyStats() {
		return hourlyStatsRepository.getAllHourlyStats();
	}

	public static String readDataFile(String filename) throws IOException {
		File resource = new ClassPathResource(
				filename).getFile();
		return new String(Files.readAllBytes(resource.toPath()));
	}
}
