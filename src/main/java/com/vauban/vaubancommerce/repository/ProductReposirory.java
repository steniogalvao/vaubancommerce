package com.vauban.vaubancommerce.repository;

import org.springframework.data.repository.CrudRepository;

import com.vauban.vaubancommerce.model.Product;

public interface ProductReposirory extends CrudRepository<Product, Long> {

}
