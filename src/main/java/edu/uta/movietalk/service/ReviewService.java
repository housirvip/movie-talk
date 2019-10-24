package edu.uta.movietalk.service;

import com.github.pagehelper.Page;
import edu.uta.movietalk.dto.PageDto;
import edu.uta.movietalk.entity.Review;
import edu.uta.movietalk.entity.ReviewLike;
import edu.uta.movietalk.entity.ReviewReply;

import java.util.List;

/**
 * @author hxy
 */
public interface ReviewService {

    /**
     * user can get a movie review
     *
     * @param id Integer
     * @return Integer
     */
    Review getById(Integer id);

    /**
     * user can create a movie review
     *
     * @param review Review
     * @return Integer
     */
    Integer createReview(Review review);

    /**
     * user can update a movie review
     *
     * @param review Review
     * @return Integer
     */
    Integer updateReview(Review review);

    /**
     * user can delete a movie review
     *
     * @param id Integer
     * @return Integer
     */
    Integer deleteReview(Integer id);

    /**
     * user can find movie reviews by mid
     *
     * @param pageDto PageDto
     * @return Review
     */
    Page<Review> pageReviewBymid(PageDto pageDto);

    /**
     * user can find movie reviews by uid
     *
     * @param pageDto PageDto
     * @return Review
     */
    Page<Review> pageReviewByuid(PageDto pageDto);

    /**
     * user can find movie reviews by following
     *
     * @param pageDto PageDto
     * @return Review
     */
    Page<Review> pageReviewByFollowing(PageDto pageDto);

    /**
     * user can find movie reviews by uid
     *
     * @param pageDto pageDto
     * @return Review
     */
    Page<Review> findReviewBySelective(PageDto pageDto);

    /**
     * user can create a movie reply
     *
     * @param reply ReviewReply
     * @return Integer
     */
    Integer createReviewReply(ReviewReply reply);

    /**
     * user can update a movie reply
     *
     * @param reply ReviewReply
     * @return Integer
     */
    Integer updateReviewReply(ReviewReply reply);

    /**
     * user can delete a movie reply
     *
     * @param id Integer
     * @return Integer
     */
    Integer deleteReviewReply(Integer id);

    /**
     * user can find movie replys by rid
     *
     * @param pageDto PageDto
     * @return ReviewReply
     */
    Page<ReviewReply> pageReviewReplyByrid(PageDto pageDto);


    /**
     * user can find movie replys by uid
     *
     * @param pageDto pageDto
     * @return ReviewReply
     */
    Page<ReviewReply> findReviewReplyBySelective(PageDto pageDto);

    /**
     * user can create a review like
     *
     * @param like ReviewLike
     * @return Integer
     */
    Integer createReviewLike(ReviewLike like);

    /**
     * user can delete a review like
     *
     * @param id Integer
     * @return Integer
     */
    Integer deleteReviewLike(Integer id);


    /**
     * find like by id
     *
     * @param pageDto PageDto
     * @return ReviewLike
     */
    Page<ReviewLike> findLikeBySelective(PageDto pageDto);


    /**
     * get hotReviews
     *
     * @param
     * @return Review
     */
    Page<Review> pageHotReviews(PageDto pageDto);

    /**
     * get AllReviews
     *
     * @param
     * @return Review
     */
    Page<Review> pageAllReviewsByLike(PageDto pageDto);

    /**
     * get AllReplies
     *
     * @param
     * @return Review
     */
    Page<ReviewReply> pageAllRepliesByLike(PageDto pageDto);
}
