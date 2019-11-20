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
public class ParallelDotAbuse {

    private Integer code;
    private String message;
    private Double abusive;
    private Double hate_speech;
    private Double neither;
}
