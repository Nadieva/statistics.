package com.nadiamm.statistics.repository;

import java.util.List;

import com.nadiamm.statistics.model.UserAgentBlacklist;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserAgentBlacklistRepository extends CrudRepository<UserAgentBlacklist,Long> {
	
	@Query(value="select userAgent from USER_AGENT_BLACKLIST", nativeQuery = true)
	List<String> findInvalidUserAgentList();

}
