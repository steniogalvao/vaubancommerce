package com.vauban.vaubancommerce.repository;

import org.springframework.data.repository.CrudRepository;

import com.vauban.vaubancommerce.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
