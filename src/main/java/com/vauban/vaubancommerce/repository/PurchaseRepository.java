package com.vauban.vaubancommerce.repository;

import org.springframework.data.repository.CrudRepository;

import com.vauban.vaubancommerce.model.Purchase;

public interface PurchaseRepository extends CrudRepository<Purchase, Long> {

}
