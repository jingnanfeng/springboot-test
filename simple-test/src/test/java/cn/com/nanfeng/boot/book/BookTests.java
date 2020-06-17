package cn.com.nanfeng.boot.book;

import cn.com.nanfeng.boot.service.BookService;
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
 * @date 2020-05-17 11:54
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BookTests {

    @Resource
    private WebApplicationContext wac;
    private MockMvc mockMvc;
    @Resource
    private BookService bookService;
    @Resource
    private ObjectMapper mapper;

    @Before
    public void setupMockMvc(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testQueryBookById()throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.get("/book/qureyBookDetail?bookId=1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(MockMvcResultMatchers.status().isOk())
            //.andExpect(MockMvcResultMatchers.jsonPath("$.bid").value(1))
            .andDo(MockMvcResultHandlers.print());
    }

}
