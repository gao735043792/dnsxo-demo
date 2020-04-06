package com.dnsxo.bean.base;

/**
 * @author Mr.peak
 * @description 管理端用户实体
 * @date 2020/4/6 10:10
 */
public class BaseBean {

    //主键自动增长，数据库自动增长
    private Long pkid;

    public Long getPkid() {
        return pkid;
    }

    public void setPkid(Long pkid) {
        this.pkid = pkid;
    }
}
