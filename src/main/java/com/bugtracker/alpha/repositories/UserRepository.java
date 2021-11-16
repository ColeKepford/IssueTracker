package com.bugtracker.alpha.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.bugtracker.alpha.entities.*;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
  
    
    @Query("SELECT u FROM User u WHERE u.email = ?1")
    Optional<User> findUserByEmail(String email);

    @Query("Select u FROM User u WHERE u.email = ?1 AND u.password = ?2")
    Optional<User> findUser(String email, String password);

    @Query("Select u FROM User u WHERE u.lastName LIKE ?1%")
    Optional<List<User>> findUsersByLastName(String lastName);

    @Query("SELECT u FROM User u WHERE u.company=?1")
    Optional<List<User>> findUsersByCompany(long id);

    @Query(value = "SELECT Issue FROM issue_assigned_users iu WHERE iu.user=?1", nativeQuery = true)
    Optional<List<Issue>> getAllIssues(long id);

    @Transactional
    @Modifying
    @Query("UPDATE User SET email = ?1, password = ?2, first_name = ?3, last_name = ?4, company = ?5, address = ?6, postal_code = ?7, province=?8, country=?9, phone_num = ?10, created_time = ?11, role = ?12 WHERE user_id = ?13")
    void updateUser(String email, String password, String firstName, String lastName, Company company, String address, String postalCode, String province, String country, String phoneNum, LocalDateTime createdTime, Role role, long id);

    @Transactional
    @Modifying
    @Query("DELETE FROM User WHERE user_id=?1")
    void deleteUser(long id);
}