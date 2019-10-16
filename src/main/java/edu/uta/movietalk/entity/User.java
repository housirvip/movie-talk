package edu.uta.movietalk.entity;

import edu.uta.movietalk.constant.UserGroup;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author housirvip
 */
@Getter
@Setter
@ToString
public class User implements Serializable {
    private Integer id;

    private String username;

    private String email;

    private String phone;

    private String password;

    private Date createTime;

    private Date updateTime;

    private List<String> role;

    private Boolean enable;

    private Integer level;

    private UserGroup group;

    private UserInfo userInfo;

    private static final long serialVersionUID = 1L;
}