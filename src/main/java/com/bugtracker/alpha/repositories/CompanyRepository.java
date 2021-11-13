package com.bugtracker.alpha.repositories;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.bugtracker.alpha.entities.Company;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CompanyRepository extends PagingAndSortingRepository<Company, Long> {
  
  @Query("Select c FROM Company c WHERE c.name LIKE ?1")
  Optional<List<Company>> findByCompanyName(String name);

  @Transactional
  @Modifying
  @Query("UPDATE Company SET company_name= ?1 WHERE company_id = ?2")
  void updateCompany(String name, long id);

  @Transactional
  @Modifying
  @Query("DELETE FROM Company WHERE company_id=?1")
  void deleteCompany(long id);
}
