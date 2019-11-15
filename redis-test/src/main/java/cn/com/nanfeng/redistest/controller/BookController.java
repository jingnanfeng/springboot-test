package cn.com.nanfeng.redistest.controller;

import cn.com.nanfeng.redistest.model.po.Book;
import cn.com.nanfeng.redistest.service.IBookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-11-07 22:21
 */
@RestController
public class BookController {

    @Resource
    private IBookService bookService;

    @PostMapping("/update")
    public Book update(Book book){
        Book bookVO = bookService.update(book);
        return bookVO;
    }

    @PostMapping("/delete")
    public String delete(Integer id){
        bookService.deleteBookById(id);
        return "删除成功";
    }

    @GetMapping("/getBookById")
    public Book getBookById(Integer id){
        Book book = bookService.queryBookById(id);
        return book;
    }

    @GetMapping("/getAllBook")
    public List<Book> getAllBook(){
        List<Book> bookList = bookService.getAllBook();
        return bookList;
    }
    @PostMapping("/addBook")
    public int addBook(Book book){
        int res = bookService.addBook(book);
        return res;
    }

}
