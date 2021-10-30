package com.bugtracker.alpha.logging;

import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class LoggingController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void userDoesntExist(String s) {
        this.logger.error(s);
    }

    public void userDoesExist(String s) {
        this.logger.error(s);
    }

    public void unableToUpdateUser(String s) {
        this.logger.error(s);
    }

    public void updateSuccessful(String s) {
        this.logger.info(s);
    }

    public void userAddedSuccessfully(String s) {
        this.logger.info(s);
    }

    public void userRetrievedSuccessfully(String s) {
        this.logger.info(s);
    }

    public void userDeletedSuccessfully(String s) {
        this.logger.info(s);
    }

    public void unableToDeleteUser(String s) {
        this.logger.error(s);
    }

    public void adminDoesntExist(String s) {
        this.logger.error(s);
    }

    public void adminDoesExist(String s) {
        this.logger.error(s);
    }

    public void adminRetrievedSuccessfully(String s) {
        this.logger.info(s);
    }

    public void adminAddedSuccessfully(String s) {
        this.logger.info(s);
    }

    public void companyRetrievedSuccesfully(String s) {
      this.logger.info(s);
    }

    public void companyDoesExist(String s) {
      this.logger.info(s);
    }

    public void companyDoesntExist(String s) {
      this.logger.error(s);
    }

    public void companyUpdatedSuccessfully(String s) {
      this.logger.info(s);
    }

    public void companyUpdatedUnsuccessfully(String s) {
      this.logger.error(s);
    }

    public void companyDeletedSuccessfully(String s) {
      this.logger.info(s);
    }

    public void companyDidntDelete(String s) {
      this.logger.error(s);
    }

    public void companyAdded(String s) {
      this.logger.info(s);
    }

    public void companyNotAdded(String s) {
      this.logger.error(s);
    }
 
    public void sendingEmail(String s) {
        this.logger.info(s);
    }

    public void emailSent(String s) {
        this.logger.info(s);
    }

    public void unableToSendEmail(String s) {
        this.logger.error(s);
    }
}
