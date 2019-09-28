package edu.uta.movietalk.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author hxy
 */
@Data
public class UserFollow implements Serializable {
    private Integer id;

    private Integer from;

    private Integer to;

    private Date createTime;

    private static final long serialVersionUID = 1L;
}