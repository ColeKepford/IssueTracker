package com.bugtracker.alpha.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class LoggingController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void error(String msg, Throwable t) {
      logger.error(msg, t);
    }

    public void error(String msg) {
      logger.error(msg);
    }

    public void info(String msg){
      logger.info(msg);
    }
}
