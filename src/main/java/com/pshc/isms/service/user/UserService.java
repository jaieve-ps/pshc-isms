package com.pshc.isms.service.user;

import com.pshc.isms.mapper.user.UserMapper;
import com.pshc.isms.model.User;
import com.pshc.isms.model.user.response.UserInfoResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private static final Marker MESSAGE_MARKER = MarkerFactory.getMarker("USER");
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserMapper userMapper;
    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public List<UserInfoResponseDto> getUserList() {
        return userMapper.getUserList();
    }

    public UserInfoResponseDto getUserInfo(String userLoginId) {
        logger.info(MESSAGE_MARKER, "List Param : {}", userLoginId);
        UserInfoResponseDto userInfo = userMapper.getUserInfo(userLoginId);
        return userInfo;
    }
}
