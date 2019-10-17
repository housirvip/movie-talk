package edu.uta.movietalk.service;

import com.github.pagehelper.Page;
import edu.uta.movietalk.dto.PageDto;
import edu.uta.movietalk.dto.UserDto;
import edu.uta.movietalk.entity.User;
import edu.uta.movietalk.entity.UserFollow;
import edu.uta.movietalk.entity.UserInfo;
import edu.uta.movietalk.entity.UserRecord;

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
     * select a user where equal param uid
     *
     * @param uid Integer
     * @return User
     */
    User oneByIdWithInfo(Integer uid);

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

    /**
     * update userInfo where equal param userInfo
     *
     * @param userInfo UserInfo
     * @return Integer
     */
    Integer updateInfo(UserInfo userInfo);


    /**
     * follow a user
     *
     * @param fromId int
     * @param toId int
     * @return int
     */
    Integer followUser(int fromId, int toId);


    /**
     * cancel follow a user
     *
     * @param fromId int
     * @param toId int
     * @return int
     */
    Boolean unfollowUser(int fromId, int toId);

    /**
     * select user following list
     *
     * @param pageDto PageDto
     * @return Page<UserFollow>
     */
    Page<UserFollow> pageUserFollow(PageDto pageDto);

    /**
     * select user Record
     *
     * @param uid Integer
     * @return UserRecord
     */
    UserRecord selectUserRecord(Integer uid);
}
