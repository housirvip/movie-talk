package edu.uta.movietalk.controller;

import com.github.pagehelper.Page;
import edu.uta.movietalk.base.BaseResponse;
import edu.uta.movietalk.base.PageResponse;
import edu.uta.movietalk.base.ResultResponse;
import edu.uta.movietalk.client.TMDBClient;
import edu.uta.movietalk.dto.PageDto;
import edu.uta.movietalk.entity.Collect;
import edu.uta.movietalk.entity.Score;
import edu.uta.movietalk.service.CollectService;
import edu.uta.movietalk.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/**
 * @author hxy
 */
@RestController
@RequestMapping(value = "/movie")
@RequiredArgsConstructor
public class MovieController {

    private final TMDBClient tmdbClient;

    private final ScoreService scoreService;

    private final CollectService collectService;

    @Value("${api.tmdb.v3}")
    String apiV3;

    @Value("${api.tmdb.v4}")
    String apiV4;

    @GetMapping(value = "/isCollect")
    public BaseResponse<Object> isCollect(@RequestParam("mid") Integer mid, Authentication auth) {

        if(auth != null && auth.getPrincipal() != null) {
            Collect collect = new Collect();
            collect.setMid(mid);
            collect.setUid((Integer)auth.getPrincipal());
            return new ResultResponse<>(collectService.isCollect(collect));
        }

        return new ResultResponse<>(0);
    }

    @PostMapping(value = "/collect")
    public BaseResponse<Object> createCollect(@RequestBody Collect collect, Authentication auth) {

        collect.setUid((Integer)auth.getPrincipal());
        return new ResultResponse<>(collectService.insertCollect(collect));
    }

    @DeleteMapping(value = "/collect")
    public BaseResponse<Object> deleteCorrect(@RequestParam("mid") Integer mid, Authentication auth) {

        PageDto pageDto = new PageDto();
        pageDto.putUid((Integer) auth.getPrincipal());
        pageDto.getParamAsMap().put("mid", mid);
        Page<Collect> collectPage = collectService.pageCollectBySelective(pageDto);
        return new ResultResponse<>(collectService.deleteCollect(collectPage.get(0).getId()));
    }

    @GetMapping(value = "/collect/getByUid")
    public BaseResponse<Page> findCorrectByUid(PageDto pageDto, Authentication auth) {
        pageDto.putUid((Integer) auth.getPrincipal());
        Page<Collect> collectPage = collectService.pageCollectBySelective(pageDto);
        return new PageResponse<>(collectPage, collectPage.getTotal());
    }

    @GetMapping(value = "/now_playing")
    public BaseResponse<Object> nowPlay() {

        return new ResultResponse<>(tmdbClient.getNowPlaying(apiV3));
    }

    @GetMapping(value = "/details/{movie_id}")
    public BaseResponse<Object> nowPlay(@PathVariable("movie_id") String movie_id) {

        return new ResultResponse<>(tmdbClient.getDetails(movie_id, apiV3));
    }

    @GetMapping(value = "/credits/{movie_id}")
    public BaseResponse<Object> credits(@PathVariable("movie_id") String movie_id) {

        return new ResultResponse<>(tmdbClient.getCredits(movie_id, apiV3));
    }

    @GetMapping(value = "/discover")
    public BaseResponse<Object> discoverMovie(@RequestParam("year") String year, @RequestParam("with_genres") String with_genres, @RequestParam("sort_by") String sort_by, @RequestParam("page") String page) {

        return new ResultResponse<>(tmdbClient.discoverMovie(apiV3, year, with_genres, sort_by, page));
    }

    @GetMapping(value = "/search")
    public BaseResponse<Object> searchMovie(@RequestParam("query") String query, @RequestParam("page") String page) {

        return new ResultResponse<>(tmdbClient.searchMovie(apiV3, query, page));
    }

    @GetMapping(value = "/recommend")
    public BaseResponse<Object> getRecommend(Authentication auth) {

        if (auth != null && auth.getPrincipal() != null) {
            Score score = scoreService.selectMaxScoreByUid((Integer) auth.getPrincipal());
            if(score != null) {
                return new ResultResponse<>(tmdbClient.getRecommend(score.getMid().toString(), apiV3, 1));
            }
        }

        return new ResultResponse<>(tmdbClient.getNowPlaying(apiV3));
    }

}
