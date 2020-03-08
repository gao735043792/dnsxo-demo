package com.dnsxo.service.user.impl;

import com.dnsxo.bean.user.UserBean;
import com.dnsxo.mapper.user.UserMapper;
import com.dnsxo.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public UserBean queryuserbyid(String id){
        return userMapper.queryuserbyid(id);
    }
}
