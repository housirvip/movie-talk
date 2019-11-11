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
public class AdminTests {

    @Autowired
    private MockMvc mockMvc;

    private static final String jwtToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1aWQiOjEsInJvbGUiOlsiUk9MRV9VU0VSIiwiUk9MRV9BRE1JTiJdLCJleHAiOjE1OTQ4ODMwOTJ9.SuTK-OYYOmNmnVnupdXTv5XYqy4Cs2Q856Bol7SuZWQ";

    @Test
    void testGetAllReview() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.get("/review/all")
                .header("Authorization", "Bearer " + jwtToken)
                .param("pageNum","1")
                .param("pageSize","5")
                .param("param","{\"username\":\"asd\",\"content\":\"good\",\"title\":\"good\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void testGetAllReview1() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.get("/review/all")
                .header("Authorization", "Bearer " + jwtToken+234)
                .param("pageNum","1")
                .param("pageSize","5")
                .param("param","{\"username\":\"asd\",\"content\":\"good\",\"title\":\"good\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void testDeleteReview() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.delete("/review")
                .header("Authorization", "Bearer " + jwtToken)
                .param("id","12"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void testgetAllReply() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.get("/review/reply/all")
                .header("Authorization", "Bearer " + jwtToken)
                .param("pageNum","1")
                .param("pageSize","5")
                .param("param","{\"username\":\"asd\",\"content\":\"good\",\"title\":\"good\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void testDeleteReplyByAdmin() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.delete("/review/reply/deleteByAdmin")
                .header("Authorization", "Bearer " + jwtToken)
                .param("id","1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }


    @Test
    void testGetAllByAdmain() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.get("/user/all")
                .header("Authorization", "Bearer " + jwtToken)
                .param("pageNum","1")
                .param("pageSize","5")
                .param("param","{\"username\":\"asd\",\"email\":\"\",\"phone\":\"good\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }


    @Test
    void testUpdateByAdmain() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.put("/user/update")
                .header("Authorization", "Bearer " + jwtToken))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void testgetReportBySelective() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.get("/report/all")
                .header("Authorization", "Bearer " + jwtToken)
                .param("pageNum","1")
                .param("pageSize","5")
                .param("param","{\"solve\":\"0\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void testUpdateReportByAdmain() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.put("/report")
                .header("Authorization", "Bearer " + jwtToken))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}
