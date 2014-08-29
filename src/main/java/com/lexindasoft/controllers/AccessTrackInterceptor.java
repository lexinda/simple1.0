package com.lexindasoft.controllers;

import net.paoding.rose.web.ControllerInterceptorAdapter;
import net.paoding.rose.web.Invocation;

import java.lang.annotation.Annotation;

import javax.servlet.http.HttpSession;

public class AccessTrackInterceptor extends ControllerInterceptorAdapter {
    public AccessTrackInterceptor() {
        setPriority(29600);
    }

    @Override
    public Class<? extends Annotation> getRequiredAnnotationClass() {
    	return CheckRequired.class;
    }
    
    @Override
    public Object before(Invocation inv) throws Exception {
        // TODO ....
    	
    	HttpSession session = inv.getRequest().getSession();
    	
    	System.out.println("==================before=============="+session.getAttribute("user_name"));
        return super.before(inv);
    }

    @Override
    public void afterCompletion(final Invocation inv, Throwable ex) throws Exception {
        // TODO ....
    }
}