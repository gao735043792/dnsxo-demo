package com.dnsxo.bean.manage.user;

import com.dnsxo.bean.base.BaseBean;

/**
 * @author Mr.peak
 * @description 管理端用户实体
 * @date 2020/4/6 10:10
 */
public class UserBean extends BaseBean {

    //ID唯一标识，UUID无规律，前端查询时用防止爬虫
    private String id;
    //用户名称
    private String name;
    //手机号码
    private String phone;
    //密码密文
    private String pwd;
    //密码明文
    private String password;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public String getPwd() {
        return pwd;
    }

}
