package com.pshc.isms.service.security;

//import com.pshc.cloud.contract.model.HawkEyesUser;

import com.pshc.isms.model.User;
import com.pshc.isms.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserDetailServiceImp implements UserDetailsService {
	Logger logger = LoggerFactory.getLogger(UserDetailServiceImp.class);
	@Autowired
	private EmployeeService employeeService;

	@Override
	public UserDetails loadUserByUsername(String userLoginId) throws UsernameNotFoundException {
		logger.info("loadUserByUserName " + userLoginId);
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		User user = employeeService.getLoginInfo(userLoginId, request);
		if(user != null && user.isEnabled() ) {
			return user;
		}else {
			throw new UsernameNotFoundException("username not found");
		}
	}

	private User findUserByUsername(String username) {
		logger.info("find : " + username );
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		User employee = employeeService.getLoginInfo(username, request);
		logger.info("employee : " + employee );

		return employee;
	}

}
