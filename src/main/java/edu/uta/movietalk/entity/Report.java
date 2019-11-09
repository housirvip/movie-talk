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
public class Report implements Serializable {
    private Integer id;

    private Integer replyId;

    private Integer reviewId;

    private Integer uid;

    private String type;

    private String content;

    private Integer solve;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}