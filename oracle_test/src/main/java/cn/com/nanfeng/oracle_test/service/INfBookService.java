package cn.com.nanfeng.oracle_test.service;

import cn.com.nanfeng.oracle_test.model.po.NfBook;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-12-18 21:25
 */
public interface INfBookService {

    NfBook queryBookById(Long id);

    int addBook(NfBook book);

}
