package edu.uta.movietalk.dto;


import edu.uta.movietalk.constant.UserGroup;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author housirvip
 */
@Getter
@Setter
@ToString
public class UserDto implements Serializable {

    @NotBlank(groups = {Login.class})
    private String account;

    @NotBlank(groups = {Register.class})
    private String phone;

    @NotBlank(groups = {Register.class})
    private String username;

    @NotBlank(groups = {Login.class, Register.class, ChangePass.class})
    private String password;

    @NotBlank(groups = {ChangePass.class})
    private String newPass;

    @NotBlank(groups = {Register.class})
    private String email;

    private Boolean remember;

    private String code;

    private String captcha;

    private Integer id;

    private Date createTime;

    private Date updateTime;

    private List<String> role;

    private Boolean enable;

    private Integer level;

    private Integer coin;

    private UserGroup group;

    private static final long serialVersionUID = 1L;
}
