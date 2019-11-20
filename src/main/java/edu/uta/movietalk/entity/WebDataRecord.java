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
public class WebDataRecord {

    private String date;

    private Integer visitor;

    private Integer liker;

    private Double ratio;
}
