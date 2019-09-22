package edu.uta.movietalk.client;

import feign.Param;
import feign.RequestLine;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @author hxy
 */
public interface TMDBClient {

    @RequestLine("GET /3/movie/now_playing?api_key={api_key}")
    public Object getNowPlaying(@Param("api_key") String api_key);

    @RequestLine("GET /3/movie/{movie_id}?api_key={api_key}")
    public Object getDetails(@Param("movie_id") String movie_id, @Param("api_key") String api_key);
}
