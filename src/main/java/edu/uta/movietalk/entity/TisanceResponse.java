package edu.uta.movietalk.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author hxy
 */
@Getter
@Setter
@ToString
public class TisanceResponse {

    private String text;

    private List<TisanceAbuse> abuse;
}
