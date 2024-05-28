package com.nadiamm.statistics.repository;

import java.util.List;

import com.nadiamm.statistics.model.HourlyStats;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HourlyStatsRepository extends CrudRepository<HourlyStats, Long> {

	@Query(value="SELECT * FROM HOURLY_STATS", nativeQuery=true)
	List<HourlyStats> getAllHourlyStats();
	
}
