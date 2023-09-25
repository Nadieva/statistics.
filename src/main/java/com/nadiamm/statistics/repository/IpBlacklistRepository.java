package com.nadiamm.statistics.repository;

import java.util.List;

import com.nadiamm.statistics.model.IpBlacklist;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IpBlacklistRepository extends CrudRepository <IpBlacklist,Long>{
	@Query(value="SELECT IP FROM IP_BLACKLIST ", nativeQuery = true)
	List<Long> getInvalidIPList();
}