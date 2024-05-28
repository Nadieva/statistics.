package com.nadiamm.statistics.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nadiamm.statistics.model.HourlyStats;

import com.nadiamm.statistics.model.RequestList;
import com.nadiamm.statistics.repository.CustomerRepository;
import com.nadiamm.statistics.repository.HourlyStatsRepository;
import com.nadiamm.statistics.repository.RequestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static com.nadiamm.statistics.service.FileService.readFile;

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
		private HourlyStatsRepository hourlyStatsRepository;

		private static final Logger LOGGER = LoggerFactory.getLogger(StatsService.class);

		public void loadRequestByTagIDAndByCustomerIDFromJSONFile(long customerID, long tagID) throws IOException {
			LOGGER.info("Loading requests from file...");
			String contentFile = readFile(jsonFilename);
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
		}


		public long getTotalRequestByCustomerIDAndTagID(long customerID, long tagID) throws IOException {
			LOGGER.info("Calculating total request by customer ID and tag ID...");
			List<Long> validCustomerIDs=findValidCustomerIDs(customerID);
			if(validCustomerIDs.contains(customerID)) {
				loadRequestByTagIDAndByCustomerIDFromJSONFile(customerID, tagID);
			}
			return requestRepository.count();
		}

		public List<Long> findValidCustomerIDs(long id) {
			LOGGER.info("Finding valid customer IDs...");
			return customerRepository.findValidCustomerIDList(id);
		}


		public void clearRequestRepository() {
			LOGGER.info("Cleaning request repository...");
			requestRepository.deleteAll();
		}

		public void clearHourlyStatsRepository() {
			LOGGER.info("Cleaning hourly stats repository...");
			hourlyStatsRepository.deleteAll();
		}

		public List<Integer> getTimestampList() {
			LOGGER.info("Getting timestamp list...");
			return requestRepository.getTimestampList();
		}

		public int findTotalValidRequestByTimestamp(int time) {
			LOGGER.info("Calculating total valid request by timestamp...");
			return requestRepository.getTotalValidRequestByTimestamp(time) == null ? 0:Integer.parseInt(requestRepository.getTotalValidRequestByTimestamp(time));
		}

		public int findTotalInvalidRequestByTimestamp(int time) {
			LOGGER.info("Calculating total invalid request by timestamp...");
			return requestRepository.getTotalInvalidRequestByTimestamp(time) == null ? 0:Integer.parseInt(requestRepository.getTotalInvalidRequestByTimestamp(time));
		}


		public void save(HourlyStats hourlyStats) {
			hourlyStatsRepository.save(hourlyStats);
		}

		public List<HourlyStats> findHourlyStats() {
			LOGGER.info("Finding hourly stats...");
			return hourlyStatsRepository.getAllHourlyStats();
		}

	}