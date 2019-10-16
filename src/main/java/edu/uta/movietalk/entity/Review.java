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
public class Review implements Serializable {
    private Integer id;

    private Integer uid;

    private Integer mid;

    private String title;

    private Integer vote;

    private Date createTime;

    private Date updateTime;

    private String content;

    private static final long serialVersionUID = 1L;
}