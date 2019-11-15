package cn.com.nanfeng.junittest.user;

import cn.com.nanfeng.junittest.model.po.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import java.beans.Transient;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-11-15 15:17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Resource
    private ObjectMapper mapper;

    //private MockHttpSession session;

    @Before
    public void setUpMockMvc(){
        //初始化MockMvc对象
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        //session = new MockHttpSession();
    }

    @Test
    public void getUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/user/getUser/{id}","8")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1111))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void addUser() throws Exception{
        User user = new User();
        user.setId(1002);
        user.setName("小刚");

        String userJson = mapper.writeValueAsString(user);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/user/addUser")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(userJson.getBytes()))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print());
    }

}