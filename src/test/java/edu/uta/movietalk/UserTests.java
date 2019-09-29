package edu.uta.movietalk;

import edu.uta.movietalk.controller.AuthController;
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
public class UserTests {

    @Autowired
    public UserController userController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

//    @Test
//    public void testLogin() throws Exception {
//
//        UserDto userDto = new UserDto();
//        userDto.setAccount("asdasd");
//        userDto.setPassword("asdasd");
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(JsonUtils.convertToString(userDto)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn();
//    }


    @Test
    public void contextLoads() {
        log.debug("The test is running successful");
    }

}
