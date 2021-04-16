package com.gvdw.springbootjwt.repositories;

import com.gvdw.springbootjwt.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

/**
 * Created By Gullian on Apr, 2021
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByUsername(String username);
}
