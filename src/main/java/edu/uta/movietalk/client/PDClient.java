package edu.uta.movietalk.client;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

import java.util.Map;

/**
 * @author hxy
 */
public interface PDClient {


    @RequestLine("POST /v4/emotion")
    @Headers("Content-Type: multipart/form-data")
    Object postEmotion(Map<String, String> map);
}
