package com.adex.statistics;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.adex.statistics.entity.UserAgentBlacklist;

public interface UserAgentBlacklistRepository extends CrudRepository<UserAgentBlacklist,Long> {
	
	@Query(value="select userAgent from USER_AGENT_BLACKLIST", nativeQuery = true)
	List<String> findInvalidUserAgentList();

}
