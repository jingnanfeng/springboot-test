package cn.com.nanfeng.filterandint.user;

import cn.com.nanfeng.filterandint.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-12-09 16:41
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTests {

    private MockMvc mockMvc;

    @Resource
    private ObjectMapper mapper;

    @Resource
    private WebApplicationContext context;

    @Before
    public void setupMockMvc() throws Exception{
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

        User user = new User((long)1,"小王");
    }

    @Test
    public void testGetUserById() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/user/getUserById/{id}",1)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(MockMvcResultMatchers.status().isOk())
            //.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
            .andDo(MockMvcResultHandlers.print());

    }

}
