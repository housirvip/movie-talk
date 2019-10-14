package edu.uta.movietalk.controller;

import com.github.pagehelper.Page;
import com.google.common.base.Preconditions;
import edu.uta.movietalk.base.BaseResponse;
import edu.uta.movietalk.base.PageResponse;
import edu.uta.movietalk.base.ResultResponse;
import edu.uta.movietalk.dto.PageDto;
import edu.uta.movietalk.dto.Register;
import edu.uta.movietalk.entity.Review;
import edu.uta.movietalk.entity.UserFollow;
import edu.uta.movietalk.service.ReviewService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static edu.uta.movietalk.constant.ErrorMessage.REVIEW_NOT_FOUND;
import static org.apache.logging.log4j.ThreadContext.isEmpty;

/**
 * @author hxy
 */
@RestController
@RequestMapping(value = "/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping(value = "/getByMid")
    public BaseResponse<Page> getReviewByMid(PageDto pageDto, Authentication auth) {

        Page<Review> reviewPage = reviewService.pageReviewBymid(pageDto);
        return new PageResponse<>(reviewPage, reviewPage.getTotal());
    }

    @GetMapping(value = "/getByUid")
    public BaseResponse<Page> getReviewByUid(PageDto pageDto, Authentication auth) {

        Page<Review> reviewPage = reviewService.pageReviewByuid(pageDto);
        return new PageResponse<>(reviewPage, reviewPage.getTotal());
    }

    @PostMapping(value = "")
    public BaseResponse<Integer> createReview(@RequestBody Review review, Authentication auth) {

        review.setUid((Integer) auth.getPrincipal());

        return new ResultResponse<>(reviewService.createReview(review));
    }

    @DeleteMapping(value = "")
    public BaseResponse<Integer> deleteReview(@RequestParam Integer id, Authentication auth) {

        PageDto pageDto = new PageDto();
        pageDto.getParamAsMap().put("id", id);
        pageDto.putUid((Integer) auth.getPrincipal());

        Page<Review> reviewPage=  reviewService.findReviewBySelective(pageDto);
        Preconditions.checkArgument(!reviewPage.isEmpty(),REVIEW_NOT_FOUND);

        return new ResultResponse<>(reviewService.deleteReview(id));
    }

    @PutMapping(value = "")
    public BaseResponse<Integer> updateReview(@RequestBody Review review, Authentication auth) {

        PageDto pageDto = new PageDto();
        pageDto.getParamAsMap().put("id", review.getId());
        pageDto.putUid((Integer) auth.getPrincipal());

        Page<Review> reviewPage=  reviewService.findReviewBySelective(pageDto);
        Preconditions.checkArgument(!reviewPage.isEmpty(),REVIEW_NOT_FOUND);

        return new ResultResponse<>(reviewService.updateReview(review));
    }
}
