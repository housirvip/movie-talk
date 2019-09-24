package edu.uta.movietalk.service;

import com.github.pagehelper.Page;
import edu.uta.movietalk.dto.PageDto;
import edu.uta.movietalk.dto.UserDto;
import edu.uta.movietalk.entity.User;

import java.util.List;

/**
 * @author housirvip
 */
public interface UserService {
    /**
     * verify account and password, then return jwt token
     *
     * @param userDto UserDto
     * @return String
     */
    String login(UserDto userDto);

    /**
     * register a new account, then return jwt token
     *
     * @param userDto UserDto
     * @return String
     */
    String register(UserDto userDto);

    /**
     * manager can create a new user, then return user id
     *
     * @param userDto UserDto
     * @return Integer
     */
    Integer create(UserDto userDto);

    /**
     * update user table set a new password, then return user id
     *
     * @param userDto UserDto
     * @return String
     */
    Integer changePass(UserDto userDto);

    /**
     * refresh jwt token
     *
     * @param uid Integer
     * @return String
     */
    String refresh(Integer uid);

    /**
     * select a user where equal param uid
     *
     * @param uid Integer
     * @return User
     */
    User oneById(Integer uid);

    /**
     * select user list where equal param pageDto
     *
     * @param pageDto PageDto
     * @return Page
     */
    Page<User> pageByParam(PageDto pageDto);

    /**
     * update user where equal param user
     *
     * @param user User
     * @return Integer
     */
    Integer update(User user);
}
