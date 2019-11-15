package cn.com.nanfeng.redistest.service.impl;

import cn.com.nanfeng.redistest.mapper.BookMapper;
import cn.com.nanfeng.redistest.model.po.Book;
import cn.com.nanfeng.redistest.service.IBookService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-11-07 21:44
 */
@Service
public class BookServiceImpl implements IBookService {

    @Resource
    private BookMapper bookMapper;

    @Override
    public Book update(Book book) {
        int res = bookMapper.updateByPrimaryKeySelective(book);
        Book bookVO =  bookMapper.selectByPrimaryKey(book.getBId());
        return bookVO;
    }

    @Override
    public void deleteBookById(Integer id) {
        int res = bookMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Book queryBookById(Integer id) {
        Book book = bookMapper.selectByPrimaryKey(id);
        return book;
    }

    @Override
    public List<Book> getAllBook() {
        List<Book> bookList = bookMapper.selectAllBook();
        return bookList;
    }

    @Override
    public int addBook(Book book) {
        Book bookDto = new Book();
        bookDto.setBName(book.getBName());
        bookDto.setBContent(book.getBContent());
        bookDto.setBImage(book.getBImage());
        bookDto.setBNumber(book.getBNumber());
        bookDto.setBPrice(book.getBPrice());
        bookDto.setBDate(new Date());
        int res = bookMapper.insertSelective(book);
        return res;
    }
}
