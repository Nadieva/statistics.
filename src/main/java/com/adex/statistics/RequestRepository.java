package com.adex.statistics;

import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import com.adex.statistics.entity.Request;

public interface RequestRepository extends CrudRepository<Request,Long>{
	@Transactional
	@Modifying
	@Query(value="UPDATE REQUESTS R set R.VALID=false where R.USER_ID IN( \r\n" + 
			"			SELECT USERAGENT FROM USER_AGENT_BLACKLIST) \r\n" + 
			"			or REGEXP_REPLACE(r.IP, '\\.', '')  IN (\r\n" + 
			"			SELECT IP FROM IP_BLACKLIST\r\n" + 
			"			)", nativeQuery = true)
	void updateValidityFromBlacklist();
	
	@Query(value="SELECT TIME FROM REQUESTS ORDER BY TIME", nativeQuery=true)
	List<Integer> getTimestampList();
	
	@Query(value="SELECT  COUNT(*) from REQUESTS WHERE TIME=:time  AND VALID=true GROUP BY TIME",nativeQuery=true)
	String getTotalValidRequestByTimestamp(@Param("time") int time);
	
	@Query(value="SELECT  COUNT(*) from REQUESTS WHERE TIME=:time  AND VALID=false GROUP BY TIME",nativeQuery=true)
	String getTotalInvalidRequestByTimestamp(@Param("time") int time);
	
}
