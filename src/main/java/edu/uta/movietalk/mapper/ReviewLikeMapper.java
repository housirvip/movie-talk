package edu.uta.movietalk.mapper;

import com.github.pagehelper.Page;
import edu.uta.movietalk.entity.ReviewLike;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

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
     * select by selective
     *
     * @param param Map
     * @return ReviewLike
     */
    Page<ReviewLike> selectByPrimaryKeySelective(Map<String, Object> param);


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