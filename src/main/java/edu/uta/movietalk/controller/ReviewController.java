package edu.uta.movietalk.controller;

import com.github.pagehelper.Page;
import com.google.common.base.Preconditions;
import edu.uta.movietalk.base.BaseResponse;
import edu.uta.movietalk.base.PageResponse;
import edu.uta.movietalk.base.ResultResponse;
import edu.uta.movietalk.dto.PageDto;
import edu.uta.movietalk.dto.Register;
import edu.uta.movietalk.entity.Review;
import edu.uta.movietalk.entity.ReviewReply;
import edu.uta.movietalk.entity.UserFollow;
import edu.uta.movietalk.service.ReviewService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static edu.uta.movietalk.constant.ErrorMessage.REVIEW_NOT_FOUND;
import static edu.uta.movietalk.constant.ErrorMessage.REVIEW_REPLY_NOT_FOUND;
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

    @GetMapping(value = "/getByFollowing")
    public BaseResponse<Page> getReviewByFollowing(PageDto pageDto, Authentication auth) {

        pageDto.putUid((Integer) auth.getPrincipal());

        Page<Review> reviewPage = reviewService.pageReviewByFollowing(pageDto);
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

    @GetMapping(value = "/reply/getByRid")
    public BaseResponse<Page> getReviewReplyByRid(PageDto pageDto, Authentication auth) {

        Page<ReviewReply> replyPage = reviewService.pageReviewReplyByrid(pageDto);
        return new PageResponse<>(replyPage, replyPage.getTotal());
    }

    @PostMapping(value = "/reply")
    public BaseResponse<Integer> createReviewReply(@RequestBody ReviewReply reply, Authentication auth) {

        reply.setUid((Integer) auth.getPrincipal());

        return new ResultResponse<>(reviewService.createReviewReply(reply));
    }

    @DeleteMapping(value = "/reply")
    public BaseResponse<Integer> deleteReviewReply(@RequestParam Integer id, Authentication auth) {

        PageDto pageDto = new PageDto();
        pageDto.getParamAsMap().put("id", id);
        pageDto.putUid((Integer) auth.getPrincipal());

        Page<ReviewReply> replyPage=  reviewService.findReviewReplyBySelective(pageDto);
        Preconditions.checkArgument(!replyPage.isEmpty(),REVIEW_REPLY_NOT_FOUND);

        return new ResultResponse<>(reviewService.deleteReviewReply(id));
    }

    @PutMapping(value = "/reply")
    public BaseResponse<Integer> updateReviewReply(@RequestBody ReviewReply reply, Authentication auth) {

        PageDto pageDto = new PageDto();
        pageDto.getParamAsMap().put("id", reply.getId());
        pageDto.putUid((Integer) auth.getPrincipal());

        Page<ReviewReply> reviewPage=  reviewService.findReviewReplyBySelective(pageDto);
        Preconditions.checkArgument(!reviewPage.isEmpty(),REVIEW_REPLY_NOT_FOUND);

        return new ResultResponse<>(reviewService.updateReviewReply(reply));
    }
}
