package com.pshc.isms.service.security;

import com.pshc.isms.mapper.EmployeeMapper;
import com.pshc.isms.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashSet;

@Service
public class EmployeeService {
    Logger logger = LoggerFactory.getLogger("EmployeeService");

    @Autowired
    private EmployeeMapper employeeMapper;

    public User getLoginInfo(String userLoginId, HttpServletRequest request) {
        User user = employeeMapper.getLoginInfo(userLoginId);
        if ( user != null) {
            user.setUsername(userLoginId);
            logger.info("login user : ", user);
            if (user.getErrorCode() == null) {
                user.setErrorCode("");
            }
            if(!"".equals(user.getErrorCode())) {
                logger.info("log error : " + user.getErrorCode());
            }
        }
        return user;
    }

    public User getUser(String code) {
        return employeeMapper.getUser(code);
    }

    public User updateUser(User employee) {
        employeeMapper.updateUser(employee);

        logger.info(employee.getSuccessYN());
        logger.info(employee.getErrorMessage());
        return employee;
    }

    public User saveUser(User employee) {
        employeeMapper.saveUser(employee);
        logger.info(employee.getSuccessYN());
        logger.info(employee.getErrorMessage());
        return employee;
    }

}
