package cn.com.nanfeng.simpletest.service;

import cn.com.nanfeng.simpletest.model.vo.BookVO;

/**
 * @author liutao
 * @Title BookController
 * @Description service
 * @date 2020-05-10 10:26
 */
public interface BookService {

    /**
     * 查询书籍的详情
     * @param bookId
     * @return
     */
    BookVO queryBookDetail(Integer bookId);

}
