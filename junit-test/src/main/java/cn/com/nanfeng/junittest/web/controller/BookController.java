package cn.com.nanfeng.junittest.web.controller;

import cn.com.nanfeng.junittest.model.po.Book;
import cn.com.nanfeng.junittest.service.IBookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-11-18 16:02
 */
@RestController
public class BookController {

    private final static Logger logger = LoggerFactory.getLogger(BookController.class);

    @Resource
    private IBookService bookService;

    @GetMapping("/book/decrementBook/{bId}")
    public Book decrementBook(@PathVariable("bId") Integer bId){
        Book book = bookService.decrementBook(bId);
        return book;
    }

    @GetMapping("/book/queryBookById/{bId}")
    public Book queryBookById(@PathVariable("bId")Integer bId){
        Book book = bookService.queryBookById(bId);
        return book;
    }

    @PostMapping("/book/addBook")
    public String addBook(@RequestBody Book book){
        int res = bookService.addBook(book);
        if (res == 0){
            throw new RuntimeException("添加失败");
        }
        return "添加成功";
    }

}
