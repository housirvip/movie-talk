package edu.uta.movietalk.service.impl;

import com.github.pagehelper.Page;
import edu.uta.movietalk.dto.PageDto;
import edu.uta.movietalk.entity.Review;
import edu.uta.movietalk.entity.ReviewLike;
import edu.uta.movietalk.entity.ReviewReply;
import edu.uta.movietalk.mapper.ReviewLikeMapper;
import edu.uta.movietalk.mapper.ReviewMapper;
import edu.uta.movietalk.mapper.ReviewReplyMapper;
import edu.uta.movietalk.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author hxy
 */
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewMapper reviewMapper;

    private final ReviewReplyMapper replyMapper;

    private final ReviewLikeMapper likeMapper;

    @Override
    public Review getById(Integer id) {

        return reviewMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer createReview(Review review) {

        review.setCreateTime(new Date());

        return reviewMapper.insertSelective(review);
    }

    @Override
    public Integer updateReview(Review review) {

        return reviewMapper.updateByPrimaryKeySelective(review);
    }

    @Override
    public Integer deleteReview(Integer id) {

        return reviewMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Page<Review> pageReviewBymid(PageDto pageDto) {

        return reviewMapper.selectByPrimaryKeySelective(pageDto.putParam().getParamAsMap());
    }

    @Override
    public Page<Review> pageReviewByuid(PageDto pageDto) {

        return reviewMapper.selectByPrimaryKeySelective(pageDto.putParam().getParamAsMap());
    }

    @Override
    public Page<Review> pageReviewByFollowing(PageDto pageDto) {

        return reviewMapper.selectByFollowing(pageDto.putParam().getParamAsMap());
    }

    @Override
    public Page<Review> findReviewBySelective(PageDto pageDto) {

        return reviewMapper.selectByPrimaryKeySelective(pageDto.putParam().getParamAsMap());
    }

    @Override
    public Integer createReviewReply(ReviewReply reply) {

        reply.setCreateTime(new Date());

//        Integer replyTotal = reviewMapper.selectByPrimaryKey(reply.getRid()).getReplyTotal() + 1;
//        Review review = new Review();
//        review.setId(reply.getRid());
//        review.setReplyTotal(replyTotal);
//        review.setUpdateTime(new Date());

        Integer result = replyMapper.insertSelective(reply);

//        reviewMapper.updateByPrimaryKeySelective(review);
        return result;
    }

    @Override
    public Integer updateReviewReply(ReviewReply reply) {

        return replyMapper.updateByPrimaryKeySelective(reply);
    }

    @Override
    public Integer deleteReviewReply(Integer id) {

//        Integer rid = replyMapper.selectByPrimaryKey(id).getRid();
//        Integer replyTotal = reviewMapper.selectByPrimaryKey(rid).getReplyTotal() - 1;
//        Review review = new Review();
//        review.setId(rid);
//        review.setReplyTotal(replyTotal);
//        review.setUpdateTime(new Date());

        Integer result = replyMapper.deleteByPrimaryKey(id);
//        reviewMapper.updateByPrimaryKeySelective(review);

        return result;
    }

    @Override
    public Page<ReviewReply> pageReviewReplyByrid(PageDto pageDto) {

        return replyMapper.selectByPrimaryKeySelective(pageDto.putParam().getParamAsMap());
    }


    @Override
    public Page<ReviewReply> findReviewReplyBySelective(PageDto pageDto) {

        return replyMapper.selectByPrimaryKeySelective(pageDto.putParam().getParamAsMap());
    }

    @Override
    public Integer createReviewLike(ReviewLike like) {

//        Integer likeTotal = reviewMapper.selectByPrimaryKey(like.getRid()).getLikeTotal() + 1;
//        Review review = new Review();
//        review.setId(like.getRid());
//        review.setLikeTotal(likeTotal);
//        review.setUpdateTime(new Date());

        Integer result = likeMapper.insert(like);

//        reviewMapper.updateByPrimaryKeySelective(review);

        return result;
    }

    @Override
    public Integer deleteReviewLike(Integer id) {

//        Integer rid = likeMapper.selectByPrimaryKey(id).getRid();
//        Integer likeTotal = reviewMapper.selectByPrimaryKey(rid).getLikeTotal() - 1;
//        Review review = new Review();
//        review.setId(rid);
//        review.setLikeTotal(likeTotal);
//        review.setUpdateTime(new Date());

        Integer result = likeMapper.deleteByPrimaryKey(id);

//        reviewMapper.updateByPrimaryKeySelective(review);

        return result;
    }

    @Override
    public Page<ReviewLike> findLikeBySelective(PageDto pageDto) {

        return likeMapper.selectByPrimaryKeySelective(pageDto.putParam().getParamAsMap());
    }

    @Override
    public Page<Review> pageHotReviews(PageDto pageDto) {
        return reviewMapper.selectHotReviews();
    }
}
