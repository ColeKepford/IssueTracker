package com.bugtracker.alpha.companies;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
  
  @Query("Select c FROM Company c WHERE c.name LIKE ?1")
  Optional<List<Company>> findByCompanyName(String name);

  @Transactional
  @Modifying
  @Query("UPDATE Company SET company_name= ?1")
  void updateCompany(String name);

  @Transactional
  @Modifying
  @Query("DELETE FROM Company WHERE company_id=?1")
  void deleteCompany(int id);
}
