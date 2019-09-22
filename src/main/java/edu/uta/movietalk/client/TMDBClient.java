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

    @RequestLine("GET /3/movie/{movie_id}/credits?api_key={api_key}")
    public Object getCredits(@Param("movie_id") String movie_id, @Param("api_key") String api_key);

    @RequestLine("GET /3/discover/movie?api_key={api_key}&year={year}&with_genres={with_genres}&sort_by={sort_by}&page={page}")
    public Object discoverMovie(@Param("api_key") String api_key, @Param("year") String year, @Param("with_genres") String with_genres, @Param("sort_by") String sort_by, @Param(value = "page") String page);

    @RequestLine("GET /3/search/movie?api_key={api_key}&query={query}&page={page}")
    public Object searchMovie(@Param("api_key") String api_key, @Param("query") String query, @Param("page") String page);
}
