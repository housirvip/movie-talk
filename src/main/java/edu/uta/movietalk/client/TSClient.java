package edu.uta.movietalk.client;

import edu.uta.movietalk.entity.TisanceParse;
import edu.uta.movietalk.entity.TisanceResponse;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * @author hxy
 */
public interface TSClient {

    @Headers("Ocp-Apim-Subscription-Key: {tsKey}")
    @RequestLine("POST /parse")
    TisanceResponse parse(@Param("tsKey") String tsKey, TisanceParse tisanceParse);
}
