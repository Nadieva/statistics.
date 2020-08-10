package com.adex.statistics;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.adex.statistics.entity.IpBlacklist;

public interface IpBlacklistRepository extends CrudRepository <IpBlacklist,Long>{
	@Query(value="SELECT IP FROM IP_BLACKLIST ", nativeQuery = true)
	List<Long> getInvalidIPList();
}