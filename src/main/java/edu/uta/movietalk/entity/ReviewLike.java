package edu.uta.movietalk.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author hxy
 */
@Data
public class ReviewLike implements Serializable {
    private Integer id;

    private Integer uid;

    private Integer rid;

    private static final long serialVersionUID = 1L;
}