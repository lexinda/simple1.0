package com.lexindasoft.controllers;

import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.lexindasoft.model.Test;
import com.lexindasoft.service.TestService;

@Path("")
public class HomeController {
	private Logger logger = Logger.getLogger(HomeController.class);
	@Autowired
	TestService testService;
    @Get("")
    public String index() {
    	logger.info("123------------");
    	int t = testService.getTest();
        return "topiclist";
    }
}