package edu.uta.movietalk.base;

import lombok.*;

/**
 * @author housirvip
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
public class BaseResponse<T> {

    private Integer code;

    private String message;

    private Long total;

    private T result;
}
