package edu.uta.movietalk.mapper;

import com.github.pagehelper.Page;
import edu.uta.movietalk.entity.ReviewReply;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * @author hxy
 */
@Mapper
public interface ReviewReplyMapper {
    /**
     * delete by a reply id
     *
     * @param id int
     * @return int
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * insert a new ReviewReply
     *
     * @param record ReviewReply
     * @return int
     */
    int insert(ReviewReply record);

    /**
     * insert a new ReviewReply, selective
     *
     * @param record ReviewReply
     * @return int
     */
    int insertSelective(ReviewReply record);

    /**
     * select by a reply id
     *
     * @param id int
     * @return ReviewReply
     */
    ReviewReply selectByPrimaryKey(Integer id);

    /**
     * select by review
     *
     * @param param PageDto
     * @return Review
     */
    Page<ReviewReply> selectByPrimaryKeySelective(Map<String, Object> param);

    /**
     * update by a reply id, selective
     *
     * @param record ReviewReply
     * @return ReviewReply
     */
    int updateByPrimaryKeySelective(ReviewReply record);

    /**
     * update by a reply id
     *
     * @param record ReviewReply
     * @return int
     */
    int updateByPrimaryKey(ReviewReply record);

    /**
     * get reply by like
     *
     * @param param
     * @return ReviewReply
     */
    Page<ReviewReply> selectByLike(Map<String, Object> param);

    /**
     * delete reply by selective
     *
     * @param reply
     * @return Integer
     */
    Integer deleteBySelective(ReviewReply reply);
}