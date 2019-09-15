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
     * 验证账户和密码，成功返回 jwt
     *
     * @param userDto UserDto
     * @return String
     */
    String login(UserDto userDto);

    /**
     * 注册账户，成功返回 jwt
     *
     * @param userDto UserDto
     * @return String
     */
    String register(UserDto userDto);

    /**
     * 管理员创建账户，返回主键 id
     *
     * @param userDto UserDto
     * @return Integer
     */
    Integer create(UserDto userDto);

    /**
     * 更新认证，刷新 jwt
     *
     * @param uid Integer
     * @return String
     */
    String refresh(Integer uid);

    /**
     * 根据 uid 获取 User 不包含 UserInfo
     *
     * @param uid Integer
     * @return User
     */
    User oneById(Integer uid);

    /**
     * 根据 uid 获取 User 包含 UserInfo, Wallet
     *
     * @param uid Integer
     * @return User
     */
    User detail(Integer uid);

    /**
     * 根据参数查询，支持分页
     *
     * @param pageDto PageDto
     * @return Page
     */
    Page<User> pageByParam(PageDto pageDto);

    /**
     * 更新 User，返回 id
     *
     * @param user User
     * @return Integer
     */
    Integer update(User user);
}
