package edu.uta.movietalk.client;

import feign.RequestLine;

/**
 * @author housirvip
 */
public interface TestClient {

    @RequestLine("GET /")
    String test();
}