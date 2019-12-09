package cn.com.nanfeng.junittest.web.controller;

import cn.com.nanfeng.junittest.exception.BusinessException;
import cn.com.nanfeng.junittest.exception.ErrorCodeEnum;
import cn.com.nanfeng.junittest.model.po.Book;
import cn.com.nanfeng.junittest.service.IBookService;
import cn.com.nanfeng.junittest.util.WrapMapper;
import cn.com.nanfeng.junittest.util.Wrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

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
    public Wrapper<String> addBook(@RequestBody Book book){

        Wrapper<String> wrapper = null;

        int res = bookService.addBook(book);
        if (res != 0){
            wrapper = WrapMapper.wrap(200,"添加成功");
        }else {
            throw new BusinessException(ErrorCodeEnum.B10500.getCode(),ErrorCodeEnum.B10500.getMessage());
        }
        return wrapper;
    }

}
