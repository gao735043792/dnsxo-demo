package com.dnsxo.service.manage.user.impl;

import com.dnsxo.bean.manage.user.UserBean;
import com.dnsxo.mapper.manage.user.UserMapper;
import com.dnsxo.service.manage.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public UserBean queryUserByPhone(String phone){
        return userMapper.queryUserByPhone(phone);
    }
}
