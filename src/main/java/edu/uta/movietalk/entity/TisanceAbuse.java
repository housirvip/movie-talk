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
public class TisanceAbuse {

    private int sentence_index;

    private int offset;

    private int length;

    private String type;

    private String severity;
}
