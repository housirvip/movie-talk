package edu.uta.movietalk.mapper;

import edu.uta.movietalk.entity.UserFollow;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author hxy
 */
@Mapper
public interface UserFollowMapper {
    /**
     * delete by id
     *
     * @param id int
     * @return int
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * insert a new UserFollow
     *
     * @param record UserFollow
     * @return int
     */
    int insert(UserFollow record);

    /**
     * insert a new UserFollow, selective
     *
     * @param record UserFollow
     * @return int
     */
    int insertSelective(UserFollow record);

    /**
     * select by id
     *
     * @param id int
     * @return UserFollow
     */
    UserFollow selectByPrimaryKey(Integer id);

    /**
     * update by id, selective
     *
     * @param record UserFollow
     * @return int
     */
    int updateByPrimaryKeySelective(UserFollow record);

    /**
     * update by id
     *
     * @param record UserFollow
     * @return int
     */
    int updateByPrimaryKey(UserFollow record);
}