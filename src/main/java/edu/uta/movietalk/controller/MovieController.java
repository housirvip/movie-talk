package edu.uta.movietalk.controller;

import edu.uta.movietalk.base.BaseResponse;
import edu.uta.movietalk.base.ResultResponse;
import edu.uta.movietalk.client.TMDBClient;
import edu.uta.movietalk.entity.Score;
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

    @Value("${api.tmdb.v3}")
    String apiV3;

    @Value("${api.tmdb.v4}")
    String apiV4;

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
            return new ResultResponse<>(tmdbClient.getRecommend(score.getMid().toString(), apiV3, 1));
        }

        return new ResultResponse<>(tmdbClient.getNowPlaying(apiV3));
    }

}
