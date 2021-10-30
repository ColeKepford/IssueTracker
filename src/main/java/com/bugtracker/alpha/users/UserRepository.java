package com.bugtracker.alpha.users;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bugtracker.alpha.companies.*;
import com.bugtracker.alpha.roles.*;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
  
    @Query("SELECT c FROM Client c WHERE c.email = ?1")
    Optional<User> findUserByEmail(String email);

    @Query("Select c FROM Client c WHERE c.email = ?1 AND c.password = ?2")
    Optional<User> findUser(String email, String password);

    @Query("Select c FROM Client c WHERE c.last_name LIKE ?1%")
    Optional<List<User>> findUsersByLastName(String lastName);

    @Transactional
    @Modifying
    @Query("UPDATE User SET email = ?1, password = ?2, first_name = ?3, last_name = ?4, company = ?5, address = ?6, postal_code = ?7, phone_num = ?8, created_time = ?9, role = ?10")
    void updateUser(String email, String password, String first_name, String last_name, Company company, String address, String postal_code, String phone_num, LocalDate created_time, Role role);

    @Transactional
    @Modifying
    @Query("DELETE FROM Users WHERE user_id=?1")
    void deleteUser(int id);
}