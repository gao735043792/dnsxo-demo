package com.dnsxo.mapper.user;

import com.dnsxo.bean.user.UserBean;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    UserBean queryuserbyid(String id);
}
