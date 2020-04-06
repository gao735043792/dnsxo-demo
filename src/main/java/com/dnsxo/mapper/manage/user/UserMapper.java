package com.dnsxo.mapper.manage.user;

import com.dnsxo.bean.manage.user.UserBean;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    UserBean queryUserByPhone(String phone);
}
