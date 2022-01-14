package com.pshc.isms.mapper.user;

import com.pshc.isms.model.user.response.UserInfoResponseDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {
    List<UserInfoResponseDto> getUserList();// ex. 회원목록
    UserInfoResponseDto getUserInfo(String userLoginId);// ex. 개인 회원정보 가져오는 mapper method
}
