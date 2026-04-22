package com.bookedNbusy.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookedNbusy.Entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findUserByPhoneNumber(String number);

    Optional<User> findUserByUserEmail(String mail);
}
