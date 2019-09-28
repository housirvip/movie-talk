package edu.uta.movietalk.mapper;

import edu.uta.movietalk.entity.Review;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author hxy
 */
@Mapper
public interface ReviewMapper {

    /**
     * delete by review id
     *
     * @param id int
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * insert a new Review
     *
     * @param record Review
     * @return int
     */
    int insert(Review record);

    /**
     * insert a new review, selective
     *
     * @param record Review
     * @return int
     */
    int insertSelective(Review record);

    /**
     * select by review id
     *
     * @param id int
     * @return Review
     */
    Review selectByPrimaryKey(Integer id);


    /**
     * update by a review id, selective
     *
     * @param record Review
     * @return int
     */
    int updateByPrimaryKeySelective(Review record);


    /**
     * update by a review id
     *
     * @param record Review
     * @return int
     */
    int updateByPrimaryKey(Review record);
}