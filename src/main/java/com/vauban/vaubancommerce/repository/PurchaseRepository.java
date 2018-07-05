package com.vauban.vaubancommerce.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.vauban.vaubancommerce.model.Purchase;
import com.vauban.vaubancommerce.model.User;

public interface PurchaseRepository extends CrudRepository<Purchase, Long> {

	List<Purchase> findAllByBuyer( User buyer);

}
