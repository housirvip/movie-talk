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
public class DirtyWord implements Serializable {
    private Integer id;

    private String word;

    private String language;

    private static final long serialVersionUID = 1L;
}