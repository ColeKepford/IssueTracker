package com.bugtracker.alpha.repositories;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.bugtracker.alpha.entities.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
  
  @Query("Select c FROM Role c WHERE c.company=?1")
  Optional<List<Role>> findByCompany(int id);
  
  
  @Query("Select c FROM Role c WHERE c.name LIKE ?1")
  Optional<List<Role>> findByName(String name);
  
  @Transactional
  @Modifying
  @Query("DELETE FROM Role WHERE role_id=?1")
  void deleteRole(int id);

  
  @Transactional
  @Modifying
  @Query("UPDATE Role SET company=?1, name=?2 WHERE role_id=?3")
  void updateRole(String name, int id);
}
