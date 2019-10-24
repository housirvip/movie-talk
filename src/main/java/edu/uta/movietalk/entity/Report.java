package edu.uta.movietalk.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

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

    private static final long serialVersionUID = 1L;
}