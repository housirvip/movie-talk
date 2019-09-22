package edu.uta.movietalk.controller;

import edu.uta.movietalk.base.BaseResponse;
import edu.uta.movietalk.base.ResultResponse;
import edu.uta.movietalk.client.TMDBClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
