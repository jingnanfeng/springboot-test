package cn.com.nanfeng.junittest.service;

import cn.com.nanfeng.junittest.model.po.Book;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-11-18 15:46
 */
public interface IBookService {

    /**
     * 购买书籍
     * @param bId
     * @return
     */
    Book decrementBook(Integer bId);

    /**
     * 查询某一本书籍
     * @param bId
     * @return
     */
    Book queryBookById(Integer bId);

    /**
     * 添加一本书籍
     * @param book
     * @return
     */
    int addBook(Book book);


}
