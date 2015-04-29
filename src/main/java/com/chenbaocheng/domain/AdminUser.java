package com.chenbaocheng.domain;

import com.chenbaocheng.enums.StatusEnum;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by CBC on 2015-04-11 15:49.
 */
@Data
@Entity
@Table(name = "tb_admin", indexes = {@Index(name = "idx_user_name", columnList = "userName")})
public class AdminUser implements Serializable {

    @Id
    private int id;

    @Column(nullable = false, length = 100)
    private String userName = "";

    @Column(nullable = false, length = 100)
    private String password = "";

    @Column(nullable = false)
    private int status = StatusEnum.DEFAULT.value();

    @Column(nullable = false)
    private long lastLoginIp = 0L;

    @Column(updatable = false)
    private Date createDate = new Date(); //创建时间

    @Column(updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updateDate = new Date(); //最后更新时间
}
