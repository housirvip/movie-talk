package edu.uta.movietalk.client;

import edu.uta.movietalk.entity.Abuse;
import feign.Body;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * @author hxy
 */
public interface PDClient {


    @RequestLine("POST /v4/abuse")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @Body("api_key={api_key}&text={text}")
    Abuse postEmotion(@Param("api_key") String apiKey, @Param("text") String text);
}
