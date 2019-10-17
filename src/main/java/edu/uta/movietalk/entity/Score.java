package edu.uta.movietalk.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author hxy
 */
@Getter
@Setter
@ToString
public class Score {

    private Integer id;

    private Integer mid;

    private Integer uid;

    private Double score;
}
