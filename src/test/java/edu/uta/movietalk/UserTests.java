package edu.uta.movietalk;

import edu.uta.movietalk.controller.UserController;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
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

    @Test
    public void testDetail() throws Exception {

        // TODO login first and user access token to verify
        log.info("TO BE DO");

//        mockMvc.perform(MockMvcRequestBuilders.get("/user/detail"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn();
    }

    @Test
    public void contextLoads() {
        log.debug("The test is running successful");
    }

}
