package edu.uta.movietalk.mapper;

import edu.uta.movietalk.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author housirvip
 */
@Mapper
public interface UserInfoMapper {
    /**
     * delete by UserInfo id
     *
     * @param id Integer
     * @return int
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * insert a new UserInfo
     * return UserInfo id
     *
     * @param record UserInfo
     * @return int
     */
    int insert(UserInfo record);

    /**
     * insert a new UserInfo, selective
     * return UserInfo id
     *
     * @param record UserInfo
     * @return int
     */
    int insertSelective(UserInfo record);

    /**
     * select by UserInfo id
     *
     * @param id Integer
     * @return UserInfo
     */
    UserInfo selectByPrimaryKey(Integer id);

    /**
     * select by User id
     *
     * @param uid Integer
     * @return UserInfo
     */
    UserInfo selectByUid(Integer uid);

    /**
     * update by UserInfo id, selective
     *
     * @param record UserInfo
     * @return int
     */
    int updateByPrimaryKeySelective(UserInfo record);

    /**
     * update by UserInfo id
     *
     * @param record UserInfo
     * @return int
     */
    int updateByPrimaryKey(UserInfo record);
}