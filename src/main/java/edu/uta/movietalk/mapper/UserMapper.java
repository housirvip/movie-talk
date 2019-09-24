package edu.uta.movietalk.mapper;

import com.github.pagehelper.Page;
import edu.uta.movietalk.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * @author housirvip
 */
@Mapper
public interface UserMapper {
    /**
     * delete by user id
     *
     * @param id Integer
     * @return int
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * insert a new user
     * return user id
     *
     * @param record User
     * @return int
     */
    int insert(User record);

    /**
     * insert a new user, selective
     * return user id
     *
     * @param record User
     * @return int
     */
    int insertSelective(User record);

    /**
     * select by user id
     *
     * @param id Integer
     * @return User
     */
    User selectByPrimaryKey(Integer id);

    /**
     * update by user id, selective
     *
     * @param record User
     * @return int
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * update by user id
     *
     * @param record User
     * @return int
     */
    int updateByPrimaryKey(User record);

    /**
     * update by user id
     * with BLOBS(json type)
     *
     * @param record User
     * @return int
     */
    int updateByPrimaryKeyWithBLOBs(User record);

    /**
     * select by account
     *
     * @param account String
     * @return User
     */
    User selectByAccount(String account);

    /**
     * select by phone
     *
     * @param phone String
     * @return User
     */
    User selectByPhone(String phone);

    /**
     * check if username exist
     *
     * @param username String
     * @return Boolean
     */
    Boolean existUsername(String username);

    /**
     * check if username email
     *
     * @param email String
     * @return Boolean
     */
    Boolean existEmail(String email);

    /**
     * check if phone exist
     *
     * @param phone String
     * @return Boolean
     */
    Boolean existPhone(String phone);

    /**
     * select user list by param map
     * support pagehelper
     *
     * @param param Map<String,Object>
     * @return Page
     */
    Page<User> listByParam(Map<String, Object> param);
}