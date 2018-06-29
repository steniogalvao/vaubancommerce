package com.vauban.vaubancommerce.repository;

import org.springframework.data.repository.CrudRepository;

import com.vauban.vaubancommerce.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
