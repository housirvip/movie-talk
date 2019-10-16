package edu.uta.movietalk.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author hxy
 */
@Getter
@Setter
@ToString
public class UserFollow implements Serializable {
    private Integer id;

    private Integer fromId;

    private Integer toId;

    private Date createTime;

    private static final long serialVersionUID = 1L;
}