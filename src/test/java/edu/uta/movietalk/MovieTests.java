package edu.uta.movietalk;

import edu.uta.movietalk.controller.AuthController;
import edu.uta.movietalk.controller.MovieController;
import edu.uta.movietalk.controller.UserController;
import edu.uta.movietalk.dto.UserDto;
import edu.uta.movietalk.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * @author kong_p
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieTests {

    @Autowired
    public MovieController movieController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
    }

    @Test
    public void testNowPlaying() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/movie/now_playing"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testDiscover() throws Exception {

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
    public void testMovieSearch() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/movie/search")
                .param("query", "star")
                .param("page", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testMovieDetails() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/movie/details/420818")
                .param("movie_id", "420818"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void testMovieCredits() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/movie/credits/420818")
                .param("movie_id", "420818"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void contextLoads() {
    }

}
