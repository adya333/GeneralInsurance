package com.insurance.general_insurance.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository // This annotation tells Spring to manage this interface as a Repository bean.
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}

