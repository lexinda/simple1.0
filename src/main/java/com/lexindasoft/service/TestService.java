package com.lexindasoft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lexindasoft.dao.TestDAO;
import com.lexindasoft.model.Test;

@Service
public class TestService {

    @Autowired
    private TestDAO testDAO;

    public int getTest() {
        return testDAO.getTest();
    }
}
