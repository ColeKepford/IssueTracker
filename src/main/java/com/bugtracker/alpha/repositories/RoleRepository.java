package com.bugtracker.alpha.repositories;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.bugtracker.alpha.entities.Role;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends PagingAndSortingRepository<Role, Long> {
  
  @Query("SELECT c FROM Role c WHERE c.name LIKE ?1")
  Optional<List<Role>> findByName(String name);
  
  @Transactional
  @Modifying
  @Query("DELETE FROM Role WHERE role_id=?1")
  void deleteRole(long id);

  
  @Transactional
  @Modifying
  @Query("UPDATE Role SET name=?1 WHERE roleId=?2")
  void updateRole(String name, long id);
}
