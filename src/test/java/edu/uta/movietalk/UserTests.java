package edu.uta.movietalk;

import edu.uta.movietalk.entity.User;
import edu.uta.movietalk.mapper.UserMapper;
import edu.uta.movietalk.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
class UserTests {

    // a warning for mapper not found, but still work, ignore it
    @SuppressWarnings(value = "all")
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MockMvc mockMvc;

    private String jwtToken;

    @Value("${jwt.secret}")
    private String secret;

    @BeforeEach
    void testJwt() {
        JwtUtils jwtUtils = new JwtUtils(secret, 3600L, 0L);
        User user = userMapper.selectByPrimaryKey(1);
        jwtToken = jwtUtils.encode(user.getId(), user.getRole());

        log.info(user.toString());
    }

    @Test
    void testDetail() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/user/detail")
                .header("Authorization", "Bearer " + jwtToken))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void testDetailNoAuth() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/user/detail"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void contextLoads() {
        log.debug("The test is running successful");
    }

}
