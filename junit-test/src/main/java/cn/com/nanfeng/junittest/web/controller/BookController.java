package cn.com.nanfeng.junittest.web.controller;

import cn.com.nanfeng.junittest.model.po.Book;
import cn.com.nanfeng.junittest.service.IBookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/decrementBook/{bId}")
    public Book decrementBook(@PathVariable("bId") int bId){
        Book book = bookService.decrementBook(bId);
        return book;
    }

}
