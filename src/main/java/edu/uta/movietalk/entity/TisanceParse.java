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
public class TisanceParse {

    private String language;

    private String content;

    private TisanceParseSetting settings;
}
