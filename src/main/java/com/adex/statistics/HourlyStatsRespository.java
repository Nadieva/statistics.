package com.adex.statistics;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.adex.statistics.entity.HourlyStats;

public interface HourlyStatsRespository extends CrudRepository<HourlyStats, Long> {

	@Query(value="SELECT * FROM HOURLY_STATS", nativeQuery=true)
	List<HourlyStats> getAllHourlyStats();
	
}
