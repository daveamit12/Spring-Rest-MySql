package com.dave.springBootRest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dave.springBootRest.model.UserModel;

@Repository
public interface UserRepo extends JpaRepository<UserModel, Long> {

}
