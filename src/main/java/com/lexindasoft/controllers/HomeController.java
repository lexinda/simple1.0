package com.lexindasoft.controllers;

import javax.servlet.http.HttpSession;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.var.Model;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.lexindasoft.service.TestService;

@Path("")
public class HomeController {
	private Logger logger = Logger.getLogger(HomeController.class);
	@Autowired
	TestService testService;
	
	@PriCheckRequired
    @Get("")
    public String login(Invocation inv,Model model) {
    	logger.info("123------------");
    	HttpSession session = inv.getRequest().getSession();
    	session.setAttribute("user_name", "admin");
    	//int t = testService.getTest();
        return "@this is a access track interceptor";
    }
    
    @Get("/index")
    public String index() {
    	logger.info("123------------");
    	int t = testService.getTest();
        return "index";
    }
}