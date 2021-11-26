package com.bugtracker.alpha;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

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
   
    System.out.println("\n\n\n!!!STARTING DELETE!!!");
    Iterator<Issue> issues = iRepo.findAll().iterator();
    Issue newIssue;
    while(issues.hasNext()) {
      newIssue = issues.next();
      iRepo.unassignUserFromIssue(newIssue.getIssueId());
      iRepo.deleteIssue(newIssue.getIssueId());
    }
    uRepo.deleteAll();
    cRepo.deleteAll();
    rRepo.deleteAll();
    System.out.println("\n\n\n!!!ENDING DELETE!!!");

    Company company = new Company("bigCorp");
    cRepo.save(company);

    
    Role role = new Role("Developer");
    rRepo.save(role);
    
    
    User user1 = new User("cole.kepford@gmail.com", "password", "Cole", "Kepford", company, "119 Canata Close", "t2w1p8", "AB", "Canada", "587-215-9106", LocalDateTime.now(), role);
    User user2 = new User("greg.charles@email.com", "password", "Greg", "Charles", company, "123 Street Place", "t3y0e9", "AB", "Canada", "123-456-7890", LocalDateTime.now(), role);
    

    
    Issue issue = new Issue("UI won't load", "Page won't load", "Extreme", company, "UI", "Not Fixed", user1, LocalDateTime.now());
    Issue issue2 = new Issue("API Crashes when deleting Issue object", "When using the default repositorie's delete methods a PropertyValueException is thrown.", "Extreme", company, "API", "Not Fixed", user1, LocalDateTime.now());
    uRepo.save(user1);
    uRepo.save(user2);
    iRepo.save(issue);
    iRepo.save(issue2);
    iRepo.assignUserToIssue(issue.getIssueId(), user1.getUserId());
    iRepo.assignUserToIssue(issue2.getIssueId(), user1.getUserId());
    iRepo.assignUserToIssue(issue.getIssueId(), user2.getUserId());
    //iRepo.assignUserToIssue(issue.getIssue_id(), user1.getUserId());
    //iRepo.assignUserToIssue(issue.getIssue_id(), user2.getUserId());

    System.out.println("\n\n\n\nIT WORKED!!!!!!");
  }
}
