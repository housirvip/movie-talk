package edu.uta.movietalk.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author housirvip
 */
@Data
public class UserInfo implements Serializable {
    private Integer id;

    private Integer uid;

    private String sex;

    private String job;

    private String state;

    private Date birthday;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}