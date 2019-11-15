package cn.com.nanfeng.redistest.service;

import cn.com.nanfeng.redistest.model.po.Book;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-11-07 21:44
 */
@CacheConfig(cacheNames = "book")
public interface IBookService {

    /**
     * 修改
     * @param book
     * @return
     */
    @CachePut(key = "#p0.bId")
    Book update(Book book);

    /**
     * 删除
     * @param id 通过主键
     */
    @CacheEvict(key = "#p0",allEntries = false)
    void deleteBookById(Integer id);

    /**
     * 查询详情
     * @param id 通过主键
     * @return
     */
    @Cacheable(key = "#p0")
    Book queryBookById(Integer id);

    /**
     * 查询所有的书籍
     * @return
     */
    List<Book> getAllBook();

    /**
     * 数据新增
     * @param book
     * @return
     */
    @Cacheable(key = "#p0.bId")
    int addBook(Book book);

}
