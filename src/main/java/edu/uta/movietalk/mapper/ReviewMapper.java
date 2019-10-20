package edu.uta.movietalk.mapper;

import com.github.pagehelper.Page;
import edu.uta.movietalk.dto.PageDto;
import edu.uta.movietalk.entity.Review;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

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
     * select by review
     *
     * @param param PageDto
     * @return Review
     */
    Page<Review> selectByPrimaryKeySelective(Map<String, Object> param);

    /**
     * select by review
     *
     * @param param PageDto
     * @return Review
     */
    Page<Review> selectByFollowing(Map<String, Object> param);


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

    /**
     * count Received like by uid
     *
     * @param uid Integer
     * @return int
     */
    int countReceivedReviewLikeByUid(Integer uid);


    /**
     * get hotReviews
     *
     * @param
     * @return Review
     */
    Page<Review> selectHotReviews();
}