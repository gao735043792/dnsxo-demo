package com.dnsxo.service.manage.user;

import com.dnsxo.bean.manage.user.UserBean;

public interface UserService {

    UserBean queryUserByPhone(String phone);
}
