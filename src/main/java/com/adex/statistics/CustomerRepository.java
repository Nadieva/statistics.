package com.adex.statistics;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.adex.statistics.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer,Long>{	
	@Query(value="SELECT ID FROM CUSTOMERS WHERE ACTIVE=true or ID=:id", nativeQuery = true)
	List<Long> findValidCustomerIDList(@Param("id") long id);
}
