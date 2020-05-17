package cn.com.nanfeng.junittest.book;

import cn.com.nanfeng.junittest.model.po.Book;
import cn.com.nanfeng.junittest.model.vo.BookVO;
import cn.com.nanfeng.junittest.service.IBookService;
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
import cn.com.nanfeng.junittest.model.vo.BookVO.BookVOBuilder;
import java.util.Date;
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

    @Resource
    private IBookService bookService;

    @Resource
    private ObjectMapper mapper;

    @Before
    public void setupMockMvc(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testQueryBookById() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.get("/book/queryBookById/{bId}",3)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.bid").value(3))
            .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void addBook() throws Exception{
        Book book = new Book();
        book.setBName("西游记");
        book.setBNumber(30);
        book.setBContent("师徒四人的取经之旅");
        book.setBDate(new Date());
        book.setBImage("1.jpg");
        book.setBPrice(66);
        String bookJson = mapper.writeValueAsString(book);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/book/addBook")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(bookJson))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print());
    }
}
