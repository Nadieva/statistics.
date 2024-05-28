package com.nadiamm.statistics.repository;

import java.util.List;

import com.nadiamm.statistics.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer,Long>{
	@Query(value="SELECT ID FROM CUSTOMERS WHERE ACTIVE=true or ID=:id", nativeQuery = true)
	List<Long> findValidCustomerIDList(@Param("id") long id);
}
