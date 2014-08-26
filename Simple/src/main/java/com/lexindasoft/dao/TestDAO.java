package com.lexindasoft.dao;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.ReturnGeneratedKeys;
import net.paoding.rose.jade.annotation.SQL;

import com.lexindasoft.model.Test;

@DAO
public interface TestDAO {
	@ReturnGeneratedKeys
    @SQL("insert into test(user_name,user_password)values('zhangsan','123456')")
    public int getTest();
}