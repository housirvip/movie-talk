package edu.uta.movietalk.client;

import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author housirvip
 */
@Configuration
public class ClientConfig {

    @Value("http://www.baidu.com")
    private String testUrl;

    @Bean
    TestClient testClient() {

        return Feign.builder()
                .client(new OkHttpClient())
//                .encoder(new JacksonEncoder())
//                .decoder(new JacksonDecoder())
                .logger(new Slf4jLogger(TestClient.class))
                .logLevel(Logger.Level.FULL)
                .target(TestClient.class, testUrl);
    }
}
