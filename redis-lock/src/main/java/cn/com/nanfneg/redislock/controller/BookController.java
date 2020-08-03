package cn.com.nanfneg.redislock.controller;

import cn.com.nanfeng.commit.response.WrapMapper;
import cn.com.nanfeng.commit.response.Wrapper;
import cn.com.nanfneg.redislock.model.po.Book;
import cn.com.nanfneg.redislock.service.IBookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author liutao
 * @title BookController
 * @description
 * @date 2020-08-02 12:56
 */
@RestController
public class BookController {

    @Resource
    private IBookService bookService;

    @GetMapping("/book/getBookDetail")
    public Wrapper<Book> getBookDetail(@RequestParam("bookName") String bookName){
        //查询书籍信息
        Book book = bookService.getBookDetailByName(bookName);
        return WrapMapper.ok(book);
    }

}
