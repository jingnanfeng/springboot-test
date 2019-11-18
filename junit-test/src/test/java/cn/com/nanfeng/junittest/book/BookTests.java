package cn.com.nanfeng.junittest.book;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-11-18 16:11
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BookTests {

    @Resource
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setupMockMvc(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void test() throws Exception{

        for (int i = 0; i < 10; i++) {
            new Thread(() ->{

                for (int j = 0; j < 1000; j++) {
                    try {
                        decrement();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            },"线程"+i).start();
        }
    }

    @Test
    public void decrement() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.get("/decrementBook/{bId}","3")
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


}
