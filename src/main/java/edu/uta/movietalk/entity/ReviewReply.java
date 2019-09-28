package edu.uta.movietalk.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author hxy
 */
@Data
public class ReviewReply implements Serializable {
    private Integer id;

    private Integer uid;

    private Integer rid;

    private String content;

    private Date createTime;

    private static final long serialVersionUID = 1L;
}