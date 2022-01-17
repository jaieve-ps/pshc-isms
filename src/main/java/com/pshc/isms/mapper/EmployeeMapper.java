package com.pshc.isms.mapper;

import com.pshc.isms.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface EmployeeMapper {
    User getLoginInfo(@Param("userLoginId") String username);
    User getUser(String code);
    void updateUser(User employee);
    void saveUser(User employee);

}
