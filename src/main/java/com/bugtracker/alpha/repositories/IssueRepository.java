package com.bugtracker.alpha.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.bugtracker.alpha.entities.Company;
import com.bugtracker.alpha.entities.Issue;
import com.bugtracker.alpha.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Integer> {

  @Query("SELECT c FROM Issue c WHERE c.company=?1")
  Optional<List<Issue>> findByCompany(int id);

  @Query("Select c FROM Issue c WHERE c.name LIKE ?1 AND c.user=?2")
  Optional<List<Issue>> findByTitle(String title, int id);

  @Query("SELECT c FROM Issue c WHERE c.severity LIKE ?1 AND c.user=?2")
  Optional<List<Issue>> findBySeverity(String severity, int id);

  @Query(value="SELECT User FROM issue_assigned_users iu WHERE iu.issue=?1", nativeQuery = true)
  Optional<List<User>> findAllAssignedUsers(int id);

  @Transactional
  @Modifying
  @Query("DELETE FROM Issue WHERE issue_id=?1")
  void deleteIssue(int id);

  @Transactional
  @Modifying
  @Query("UPDATE Issue SET title=?1, description=?2, severity=?3, company=?4, type=?5, state=?6, date_created=?7, date_resolved=?8")
  void updateIssue(String title, String description, String severity, Company company, String type, String state, LocalDateTime date_created, LocalDateTime date_resolved);
}
