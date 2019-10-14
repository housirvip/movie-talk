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
public class ReviewLike implements Serializable {
    private Integer id;

    private Integer uid;

    private Integer rid;

    private static final long serialVersionUID = 1L;
}