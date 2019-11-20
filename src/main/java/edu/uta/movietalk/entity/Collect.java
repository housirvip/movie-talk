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
public class Collect implements Serializable {
    private Integer id;

    private Integer mid;

    private String title;

    private Integer uid;

    private static final long serialVersionUID = 1L;
}