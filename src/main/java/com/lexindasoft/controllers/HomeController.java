package com.lexindasoft.controllers;

import java.util.Date;

import javax.servlet.http.HttpSession;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;
import net.paoding.rose.web.var.Model;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.lexindasoft.service.TestService;
import com.lexindasoft.utils.ServletUtil;
import com.lexindasoft.utils.UserUtils;
import com.lexindasoft.model.Admin;

@Path("")
public class HomeController {
	private Logger logger = Logger.getLogger(HomeController.class);
	
	@Autowired
	Invocation inv;
	
	@Autowired
	TestService testService;
	
	@NotValidateCheck
    @Get("")
    public String home() {
		logger.info("home");
    	int userId = UserUtils.getUserId(inv);
    	if(userId<=0){
    		return "login";
    	}else{
    		return "r:/index";
    	}
    }
    
	@NotValidateCheck
	@Get("login")
	public String login(@Param("error") String error){
		inv.addModel("error", error);
		return "login";
	}
	
	@NotValidateCheck
	@Post("login")
	public String login(@Param("username") String username, @Param("password") String password){
		//Admin admin = adminService.getUser(username, password);
		Admin admin = null;
		if (admin == null){
			return "r:/login?error=true";
		} 
		
		if(admin.getStatus() == 0){
			return "r:/login?error=true";
		}
		if(admin.getRoleid() == 1){
			return "r:/login?error=true";
		}
		HttpSession userSession = inv.getRequest().getSession();
		userSession.setAttribute("userId", admin.getUserid());
		userSession.setAttribute("lastloginip", admin.getLastloginip());
		userSession.setAttribute("lastlogintime", admin.getLastlogintime());
		
		admin.setLastloginip(ServletUtil.getClientRealIp(inv.getRequest()));
		admin.setLastlogintime(new Date());
		//adminService.updateUser(admin);
		return "r:/index";
	}
	
    @Get("/index")
    public String index() {
    	logger.info("index");
    	//int t = testService.getTest();
        return "index";
    }
}