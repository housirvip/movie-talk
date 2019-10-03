package edu.uta.movietalk;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @author kong_p
 */
@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
class MovieTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testNowPlaying() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/movie/now_playing"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void testDiscover1() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/movie/discover")
                .param("year", "2018")
                .param("with_genres", "War")
                .param("sort_by", "popularity")
                .param("page", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void testDiscover2() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/movie/discover")
                .param("year", "2023")
                .param("with_genres", "War")
                .param("sort_by", "popularity")
                .param("page", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void testMovieSearch() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/movie/search")
                .param("query", "star")
                .param("page", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void testMovieSearchError() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/movie/search")
                .param("query", "")
                .param("page", "1"))
                .andExpect(MockMvcResultMatchers.status().is(422))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void testMovieCredits1() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/movie/credits/420818")
                .param("movie_id", "420818"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void testMovieCredits2() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/movie/credits/420818"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void testMovieCredits3() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/movie/credits/4208181234")
                .param("movie_id", "4208181234"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void testMovieCredits4() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/movie/credits/522938")
                .param("movie_id", "522938"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }


    @Test
    void testMovieDetails1() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/movie/details/420818")
                .param("movie_id", "420818"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void testMovieDetails2() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/movie/details/420818"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void testMovieDetails3() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/movie/details/522938")
                .param("movie_id", "522938"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void testMovieDetails4() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/movie/details/4208171234")
                .param("movie_id", "4208171234"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void contextLoads() {
        log.debug("The test is running successful");
    }

}
