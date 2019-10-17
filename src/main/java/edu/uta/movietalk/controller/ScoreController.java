package edu.uta.movietalk.controller;

import com.github.pagehelper.Page;
import edu.uta.movietalk.base.BaseResponse;
import edu.uta.movietalk.base.PageResponse;
import edu.uta.movietalk.base.ResultResponse;
import edu.uta.movietalk.dto.PageDto;
import edu.uta.movietalk.entity.Review;
import edu.uta.movietalk.entity.Score;
import edu.uta.movietalk.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/**
 * @author hxy
 */
@RestController
@RequestMapping(value = "/score")
@RequiredArgsConstructor
public class ScoreController {

    private final ScoreService scoreService;

    @GetMapping(value = "")
    public BaseResponse<Page> getScore(PageDto pageDto, Authentication auth) {

        pageDto.putUid((Integer) auth.getPrincipal());

        Page<Score> scorePage = scoreService.pageScore(pageDto);
        return new PageResponse<>(scorePage, scorePage.getTotal());
    }

    @PostMapping(value = "")
    public BaseResponse<Integer> setScore(@RequestBody Score score, Authentication auth) {

        Integer result;

        score.setUid((Integer) auth.getPrincipal());

        PageDto pageDto = new PageDto();
        pageDto.putUid((Integer) auth.getPrincipal());
        pageDto.getParamAsMap().put("mid", score.getMid());

        Page<Score> scorePage = scoreService.pageScore(pageDto);
        if(scorePage.isEmpty()) {
            result = scoreService.insertScore(score);
        } else {
            score.setId(scorePage.get(0).getId());
            result = scoreService.updateScore(score);
        }

        return new ResultResponse<>(result);
    }
}
