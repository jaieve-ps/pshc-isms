package com.pshc.isms.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class User extends ProcedureOutMsg implements UserDetails, Serializable {
    Logger logger = LoggerFactory.getLogger("User");
    private static final String ROLE_PREFIX = "ROLE_";

    private boolean enable = true;

    private String userId;
    private String username;
    private String loginPw;
    private String userType;
    private String companyName;
    private String businessNo;
    private String partCode;
    private String emailAddress;
    private String workDescription;

    private String errorCode;

    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        logger.info("role : " + roles);
        List<GrantedAuthority> roleList = new ArrayList<GrantedAuthority>();
        roles.forEach(role -> roleList.add(new SimpleGrantedAuthority(ROLE_PREFIX + role.getRole())));
        return null;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLoginPw() {
        return loginPw;
    }

    @Override
    public String getPassword() {
        return loginPw;
    }

    public void setLoginPw(String loginPw) {
        this.loginPw = loginPw;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getBusinessNo() {
        return businessNo;
    }

    public void setBusinessNo(String businessNo) {
        this.businessNo = businessNo;
    }

    public String getPartCode() {
        return partCode;
    }

    public void setPartCode(String partCode) {
        this.partCode = partCode;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getWorkDescription() {
        return workDescription;
    }

    public void setWorkDescription(String workDescription) {
        this.workDescription = workDescription;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return enable;
    }
}
