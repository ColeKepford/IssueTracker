package com.bugtracker.alpha.roles;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

  @Query("Select c FROM Company_Roles c WHERE c.company_id=?1")
  Optional<List<Role>> findByCompanyId(int id);

  @Query("Select c FROM Company_Roles c WHERE c.name LIKE ?1")
  Optional<List<Role>> findByName(String name);

  @Transactional
  @Modifying
  @Query("DELETE FROM Company_roles WHERE role_id=?1")
  void deleteRole(int id);

  @Transactional
  @Modifying
  @Query("UPDATE Company_roles SET company_id=?1, name=?2")
  void updateRole(int company_id, String name);
}
