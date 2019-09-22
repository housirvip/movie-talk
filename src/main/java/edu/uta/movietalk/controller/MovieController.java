package edu.uta.movietalk.controller;

import edu.uta.movietalk.base.BaseResponse;
import edu.uta.movietalk.base.ResultResponse;
import edu.uta.movietalk.client.TMDBClient;
import feign.Param;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

/**
 * @author hxy
 */

@RestController
@RequestMapping(value = "/movie")
@RequiredArgsConstructor
public class MovieController {

    private final TMDBClient tmdbClient;

    @Value("${api.tmdb.v3}")
    String api_v3;

    @Value("${api.tmdb.v4}")
    String api_v4;

    @GetMapping(value = "/now_playing")
    public BaseResponse<Object> nowPlay() {

        return new ResultResponse<>(tmdbClient.getNowPlaying(api_v3));
    }

    @GetMapping(value = "/details/{movie_id}")
    public BaseResponse<Object> nowPlay(@PathVariable("movie_id") String movie_id) {

        return new ResultResponse<>(tmdbClient.getDetails(movie_id, api_v3));
    }

    @GetMapping(value = "/credits/{movie_id}")
    public BaseResponse<Object> credits(@PathVariable("movie_id") String movie_id) {

        return new ResultResponse<>(tmdbClient.getCredits(movie_id, api_v3));
    }

}
