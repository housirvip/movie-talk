package edu.uta.movietalk;


import edu.uta.movietalk.dto.UserDto;
import edu.uta.movietalk.entity.User;
import edu.uta.movietalk.utils.JsonUtils;
import edu.uta.movietalk.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestExecutionListeners;
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
public class ReviewTests {

    @Autowired
    private MockMvc mockMvc;

    private static final String jwtToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1aWQiOjEsInJvbGUiOlsiUk9MRV9VU0VSIl0sImV4cCI6MTU5MTMwNjcxMn0.RaIS2ghaj9pE8VJphvD3CoN2vX7ywqFKfFO1Sg3buU0";

    @Test
    void testCreatReview() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.post("/review")
                .header("Authorization", "Bearer " + jwtToken))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void testFindReviewById() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/review/getById/5")
                .header("Authorization", "Bearer " + jwtToken))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void testFindReviewByMid() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/review/getByMid")
                .header("Authorization", "Bearer " + jwtToken)
                .param("pageNum","1")
                .param("pageSize","5")
                .param("param","{mid:578672}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
    @Test
    void testFindReviewByUid() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/review/getByUid")
                .header("Authorization", "Bearer " + jwtToken)
                .param("pageNum","1")
                .param("pageSize","5")
                .param("param","{\"uid\":1}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
    @Test
    void testFindReviewByFollowing() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/review/getByFollowing")
                .header("Authorization", "Bearer " + jwtToken)
                .param("pageNum","1")
                .param("pageSize","5")
                .param("param","{\"uid\":1}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void testCreatReply() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/review/reply")
                .header("Authorization", "Bearer " + jwtToken))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void testFindReplyByrid() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/review/reply/getByRid")
                .header("Authorization", "Bearer " + jwtToken)
                .param("pageNum","1")
                .param("pageSize","5")
                .param("param","{rid:2}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void testCreateReviewLike() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/review/like")
                .header("Authorization", "Bearer " + jwtToken))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
    @Test
    void testDeleteReviewLike() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/review/like")
                .header("Authorization", "Bearer " + jwtToken)
                .param("rid","4"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
    @Test
    void testUpdateReview() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.put("/review")
                .header("Authorization", "Bearer " + jwtToken))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
    @Test
    void testDeleteReview() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.delete("/review")
                .header("Authorization", "Bearer " + jwtToken)
                .param("id","22"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
    @Test
    void testUpdateReviewReply() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.put("/review")
                .header("Authorization", "Bearer " + jwtToken))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
    @Test
    void testCreateCollect() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/movie/collect")
                .header("Authorization", "Bearer " + jwtToken))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
    @Test
    void testDeleteCollect() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/movie/collect")
                .header("Authorization", "Bearer " + jwtToken)
                .param("mid","475557"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
    @Test
    void testUploadAvatar() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/user/avatar")
                .header("Authorization", "Bearer " + jwtToken))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

}
