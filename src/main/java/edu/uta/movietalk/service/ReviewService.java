package edu.uta.movietalk.service;

import com.github.pagehelper.Page;
import edu.uta.movietalk.dto.PageDto;
import edu.uta.movietalk.entity.Review;

/**
 * @author hxy
 */
public interface ReviewService {


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
     * user can find movie reviews by uid
     *
     * @param pageDto pageDto
     * @return Review
     */
    Page<Review> findReviewBySelective(PageDto pageDto);
}
