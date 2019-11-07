package edu.uta.movietalk.controller;

import com.github.pagehelper.Page;
import com.google.common.base.Preconditions;
import edu.uta.movietalk.base.BaseResponse;
import edu.uta.movietalk.base.PageResponse;
import edu.uta.movietalk.base.ResultResponse;
import edu.uta.movietalk.dto.PageDto;
import edu.uta.movietalk.entity.Review;
import edu.uta.movietalk.entity.ReviewLike;
import edu.uta.movietalk.entity.ReviewReply;
import edu.uta.movietalk.nlp.IllegalInfoProcess;
import edu.uta.movietalk.service.ReviewService;
import edu.uta.movietalk.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import static edu.uta.movietalk.constant.ErrorMessage.*;

/**
 * @author hxy
 */
@Slf4j
@RestController
@RequestMapping(value = "/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    private final IllegalInfoProcess illegalInfoProcess;

    @GetMapping(value = "/getById/{reviewId}")
    public BaseResponse<Review> getReviewById(@PathVariable("reviewId") Integer reviewId, Authentication auth) {

        PageDto pageDto = new PageDto();
        pageDto.getParamAsMap().put("id", reviewId);
        pageDto.getParamAsMap().put("liker", auth == null ? null : (Integer) auth.getPrincipal());

        return new ResultResponse<>(reviewService.findReviewBySelective(pageDto).getResult().get(0));
    }

    @GetMapping(value = "/getByMid")
    public BaseResponse<Page> getReviewByMid(PageDto pageDto, Authentication auth) {

        pageDto.getParamAsMap().put("liker", auth == null ? null : (Integer) auth.getPrincipal());
        Page<Review> reviewPage = reviewService.pageReviewBymid(pageDto);
        return new PageResponse<>(reviewPage, reviewPage.getTotal());
    }

    @GetMapping(value = "/getByUid")
    public BaseResponse<Page> getReviewByUid(PageDto pageDto, Authentication auth) {

        pageDto.getParamAsMap().put("liker", auth == null ? null : (Integer) auth.getPrincipal());
        Page<Review> reviewPage = reviewService.pageReviewByuid(pageDto);
        return new PageResponse<>(reviewPage, reviewPage.getTotal());
    }

    @GetMapping(value = "/getByFollowing")
    public BaseResponse<Page> getReviewByFollowing(PageDto pageDto, Authentication auth) {

        if (auth == null) {
            return new PageResponse<>(null, 0);
        }

        pageDto.putUid((Integer) auth.getPrincipal());
        pageDto.getParamAsMap().put("liker", (Integer) auth.getPrincipal());

        Page<Review> reviewPage = reviewService.pageReviewByFollowing(pageDto);
        return new PageResponse<>(reviewPage, reviewPage.getTotal());
    }

    @PostMapping(value = "")
    public BaseResponse<Integer> createReview(@RequestBody Review review, Authentication auth) {

        Preconditions.checkArgument(!illegalInfoProcess.isDirty(review.getTitle() + "." + review.getContent()), DIRTY_WORD_EXIST);

        review.setUid((Integer) auth.getPrincipal());

        return new ResultResponse<>(reviewService.createReview(review));
    }

    @DeleteMapping(value = "")
    public BaseResponse<Integer> deleteReview(@RequestParam Integer id, Authentication auth) {

        PageDto pageDto = new PageDto();
        pageDto.getParamAsMap().put("id", id);
        pageDto.putUid((Integer) auth.getPrincipal());

        Page<Review> reviewPage = reviewService.findReviewBySelective(pageDto);
        Preconditions.checkArgument(!reviewPage.isEmpty(), REVIEW_NOT_FOUND);

        return new ResultResponse<>(reviewService.deleteReview(id));
    }

    @DeleteMapping(value = "deleteByAdmin")
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<Integer> deleteByAdmin(@RequestParam Integer id) {

        return new ResultResponse<>(reviewService.deleteReview(id));
    }

    @PutMapping(value = "")
    public BaseResponse<Integer> updateReview(@RequestBody Review review, Authentication auth) {

        PageDto pageDto = new PageDto();
        pageDto.getParamAsMap().put("id", review.getId());
        pageDto.putUid((Integer) auth.getPrincipal());

        Page<Review> reviewPage = reviewService.findReviewBySelective(pageDto);
        Preconditions.checkArgument(!reviewPage.isEmpty(), REVIEW_NOT_FOUND);

        return new ResultResponse<>(reviewService.updateReview(review));
    }

    @GetMapping(value = "/reply/getById/{replyId}")
    public BaseResponse<ReviewReply> getReviewReplyById(@PathVariable("replyId") Integer replyId, Authentication auth) {

        PageDto pageDto = new PageDto();
        pageDto.getParamAsMap().put("id", replyId);

        return new ResultResponse<>(reviewService.findReviewReplyBySelective(pageDto).getResult().get(0));
    }

    @GetMapping(value = "/reply/getByRid")
    public BaseResponse<Page> getReviewReplyByRid(PageDto pageDto, Authentication auth) {

        Page<ReviewReply> replyPage = reviewService.pageReviewReplyByrid(pageDto);
        return new PageResponse<>(replyPage, replyPage.getTotal());
    }

    @PostMapping(value = "/reply")
    public BaseResponse<Integer> createReviewReply(@RequestBody ReviewReply reply, Authentication auth) {

        Preconditions.checkArgument(!illegalInfoProcess.isDirty(reply.getContent()), DIRTY_WORD_EXIST);

        reply.setUid((Integer) auth.getPrincipal());

        return new ResultResponse<>(reviewService.createReviewReply(reply));
    }

    @DeleteMapping(value = "/reply")
    public BaseResponse<Integer> deleteReviewReply(@RequestParam Integer id, Authentication auth) {

        PageDto pageDto = new PageDto();
        pageDto.getParamAsMap().put("id", id);
        pageDto.putUid((Integer) auth.getPrincipal());

        Page<ReviewReply> replyPage = reviewService.findReviewReplyBySelective(pageDto);
        Preconditions.checkArgument(!replyPage.isEmpty(), REVIEW_REPLY_NOT_FOUND);

        return new ResultResponse<>(reviewService.deleteReviewReply(id));
    }

    @DeleteMapping(value = "/reply/deleteByAdmin")
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<Integer> deleteReplyByAdmin(@RequestParam Integer id) {

        return new ResultResponse<>(reviewService.deleteReviewReply(id));
    }

    @PutMapping(value = "/reply")
    public BaseResponse<Integer> updateReviewReply(@RequestBody ReviewReply reply, Authentication auth) {

        PageDto pageDto = new PageDto();
        pageDto.getParamAsMap().put("id", reply.getId());
        pageDto.putUid((Integer) auth.getPrincipal());

        Page<ReviewReply> reviewPage = reviewService.findReviewReplyBySelective(pageDto);
        Preconditions.checkArgument(!reviewPage.isEmpty(), REVIEW_REPLY_NOT_FOUND);

        return new ResultResponse<>(reviewService.updateReviewReply(reply));
    }

    @PostMapping(value = "/like")
    public BaseResponse<Integer> createReviewLike(@RequestBody ReviewLike like, Authentication auth) {

        PageDto pageDto = new PageDto();
        pageDto.getParamAsMap().put("rid", like.getRid());
        pageDto.putUid((Integer) auth.getPrincipal());

        Page<ReviewLike> likePage = reviewService.findLikeBySelective(pageDto);
        Preconditions.checkArgument(likePage.isEmpty(), REVIEW_LIKE_AGAIN);
        like.setUid((Integer) auth.getPrincipal());

        return new ResultResponse<>(reviewService.createReviewLike(like));
    }

    @DeleteMapping(value = "/like")
    public BaseResponse<Integer> deleteReviewLike(@RequestParam Integer rid, Authentication auth) {

        PageDto pageDto = new PageDto();
        pageDto.getParamAsMap().put("rid", rid);
        pageDto.putUid((Integer) auth.getPrincipal());

        Page<ReviewLike> likePage = reviewService.findLikeBySelective(pageDto);
        Preconditions.checkArgument(!likePage.isEmpty(), REVIEW_LIKE_NOT_FOUND);

        return new ResultResponse<>(reviewService.deleteReviewLike(likePage.getResult().get(0).getId()));
    }

    @GetMapping(value = "/hot")
    public BaseResponse<Page> getHotReview() {

        Page<Review> reviewPage = reviewService.pageHotReviews(new PageDto());

        return new PageResponse<>(reviewPage, reviewPage.getTotal());
    }

    @GetMapping(value = "/all")
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<Page> getAllReviews(PageDto pageDto) {

        Page<Review> reviewPage = reviewService.pageAllReviewsByLike(pageDto);

        return new PageResponse<>(reviewPage, reviewPage.getTotal());

    }

    @GetMapping(value = "/reply/all")
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<Page> getAllReply(PageDto pageDto) {

        Page<ReviewReply> replyPage = reviewService.pageAllRepliesByLike(pageDto);

        return new PageResponse<>(replyPage, replyPage.getTotal());

    }
}
