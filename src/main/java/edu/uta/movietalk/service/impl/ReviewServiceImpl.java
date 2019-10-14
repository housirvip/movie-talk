package edu.uta.movietalk.service.impl;

import com.github.pagehelper.Page;
import edu.uta.movietalk.dto.PageDto;
import edu.uta.movietalk.entity.Review;
import edu.uta.movietalk.mapper.ReviewMapper;
import edu.uta.movietalk.service.ReviewService;
import edu.uta.movietalk.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author hxy
 */
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewMapper reviewMapper;

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
    public Page<Review> findReviewBySelective(PageDto pageDto) {

        return reviewMapper.selectByPrimaryKeySelective(pageDto.putParam().getParamAsMap());
    }
}
