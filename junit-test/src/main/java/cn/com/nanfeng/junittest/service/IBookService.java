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
    Book decrementBook(int bId);

}
