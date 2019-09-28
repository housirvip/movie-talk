package edu.uta.movietalk.mapper;

import edu.uta.movietalk.entity.ReviewLike;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author hxy
 */
@Mapper
public interface ReviewLikeMapper {

    /**
     * delete by like id
     *
     * @param id Integer
     * @return int
     */
    int deleteByPrimaryKey(Integer id);


    /**
     * insert a new ReviewLike
     *
     * @param record ReviewLike
     * @return int
     */
    int insert(ReviewLike record);

    /**
     * insert a new ReviewLike, selective
     *
     * @param record ReviewLike
     * @return int
     */
    int insertSelective(ReviewLike record);

    /**
     * select by like id
     *
     * @param id Integer
     * @return ReviewLike
     */
    ReviewLike selectByPrimaryKey(Integer id);


    /**
     * update by like id, selective
     *
     * @param record ReviewLike
     * @return int
     */
    int updateByPrimaryKeySelective(ReviewLike record);

    /**
     * update by like id
     *
     * @param record ReviewLike
     * @return int
     */
    int updateByPrimaryKey(ReviewLike record);
}