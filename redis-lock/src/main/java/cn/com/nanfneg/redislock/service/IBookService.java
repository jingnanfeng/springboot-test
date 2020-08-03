package cn.com.nanfneg.redislock.service;

import cn.com.nanfneg.redislock.model.po.Book;

/**
 * @author liutao
 * @title IBookService
 * @description
 * @date 2020-08-02 0:03
 */
public interface IBookService {

    /**
     * 通过书名查询图书信息
     * @param bookName
     * @return
     */
    Book getBookDetailByName(String bookName);

}
