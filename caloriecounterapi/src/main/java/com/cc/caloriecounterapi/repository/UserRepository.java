package com.cc.caloriecounterapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cc.caloriecounterapi.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
