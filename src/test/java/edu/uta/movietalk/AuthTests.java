package edu.uta.movietalk;

import edu.uta.movietalk.dto.UserDto;
import edu.uta.movietalk.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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
class AuthTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testLogin1() throws Exception {

        UserDto userDto = new UserDto();
        userDto.setAccount("asdasd");
        userDto.setPassword("asdasd");

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtils.convertToString(userDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void testLogin2() throws Exception {

        UserDto userDto = new UserDto();
        userDto.setAccount("kong_p");
        userDto.setPassword("123");

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtils.convertToString(userDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void testLogin3() throws Exception {

        UserDto userDto = new UserDto();
        userDto.setAccount("hxy112292");
        userDto.setPassword("123456");

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtils.convertToString(userDto)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    void testLogin4() throws Exception {

        UserDto userDto = new UserDto();
        userDto.setAccount("hxy112292");
        userDto.setPassword("123");

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtils.convertToString(userDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }


    @Test
    void contextLoads() {
        log.debug("The test is running successful");
    }

}
