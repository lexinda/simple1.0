package com.lexindasoft.controllers;

import java.lang.annotation.Annotation;

import javax.servlet.http.HttpSession;

import com.lexindasoft.utils.UserUtils;

import net.paoding.rose.web.ControllerInterceptorAdapter;
import net.paoding.rose.web.Invocation;

public class ValidateInterceptor extends ControllerInterceptorAdapter {

	public ValidateInterceptor() {
        setPriority(29600);
    }

    @Override
    public Class<? extends Annotation> getRequiredAnnotationClass() {
    	return null;
    }
    
    @Override
    public Object before(Invocation inv) throws Exception {
        // TODO ....
    	Class<? extends Object> controller = inv.getController().getClass();
		boolean isPresent = controller.isAnnotationPresent(NotValidateCheck.class) || inv.getMethod().isAnnotationPresent(NotValidateCheck.class);
		if(isPresent){
			return true;
		}
		int userId = UserUtils.getUserId(inv);
		inv.addModel("userId", userId);
        return super.before(inv);
    }

    @Override
    public void afterCompletion(final Invocation inv, Throwable ex) throws Exception {
        // TODO ....
    }
	
}
