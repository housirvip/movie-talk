package edu.uta.movietalk.entity;

import edu.uta.movietalk.dto.UserDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author hxy
 */
@Getter
@Setter
@ToString
public class UserRecord extends UserDto {

    private Integer reviewTotal;

    private Integer likeTotal;

    private Integer replyTotal;

    private Integer followTotal;

}
