package com.bugtracker.alpha;

import java.time.LocalDateTime;

import com.bugtracker.alpha.entities.Company;
import com.bugtracker.alpha.entities.Issue;
import com.bugtracker.alpha.entities.Role;
import com.bugtracker.alpha.entities.User;
import com.bugtracker.alpha.repositories.CompanyRepository;
import com.bugtracker.alpha.repositories.IssueRepository;
import com.bugtracker.alpha.repositories.RoleRepository;
import com.bugtracker.alpha.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatebaseLoader implements CommandLineRunner {
  @Autowired
  private CompanyRepository cRepo;
  @Autowired
  private IssueRepository iRepo;
  @Autowired
  private RoleRepository rRepo;
  @Autowired
  private UserRepository uRepo;
  
  @Override
  public void run(String... args) throws Exception {
    iRepo.deleteAll();
    uRepo.deleteAll();
    cRepo.deleteAll();
    rRepo.deleteAll();
    Company company = new Company("bigCorp");
    cRepo.save(company);

    
    Role role = new Role("Developer");
    rRepo.save(role);
    
    
    User user1 = new User("cole.kepford@gmail.com", "password", "Cole", "Kepford", company, "119 Canata Close", "t2w1p8", "AB", "Canada", "587-215-9106", "2021-11-04 10:05:25", role);
    //uRepo.save(user1);
    User user2 = new User("greg.charles@email.com", "password", "Greg", "Charles", company, "123 Street Place", "t3y0e9", "AB", "Canada", "123-456-7890", "2021-11-04 10:05:25", role);
    //uRepo.save(user2);
    

    
    Issue issue = new Issue("UI won't load", "Page won't load", "Extreme", company, "UI", "Not Fixed", user1, LocalDateTime.now());
    issue.assignUser(user1);
    issue.assignUser(user2);
    iRepo.save(issue);
    //iRepo.assignUserToIssue(issue.getIssue_id(), user1.getUserId());
    //iRepo.assignUserToIssue(issue.getIssue_id(), user2.getUserId());

    System.out.println("\n\n\n\nUser1 Issues:" + user1.getIssues());
    System.out.println("\nUser2 Issues:" + user2.getIssues());
    

    System.out.println("\n\n\n\nIT WORKED!!!!!!");
  }
}
