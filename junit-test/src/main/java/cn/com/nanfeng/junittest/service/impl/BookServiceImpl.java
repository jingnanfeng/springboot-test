package cn.com.nanfeng.junittest.service.impl;

import cn.com.nanfeng.junittest.mapper.BookMapper;
import cn.com.nanfeng.junittest.model.po.Book;
import cn.com.nanfeng.junittest.service.IBookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-11-18 15:47
 */
@Service
public class BookServiceImpl implements IBookService {

    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    @Resource
    private BookMapper bookMapper;

    @Override
    public Book decrementBook(int bId) {
        try {
            //查询书籍
            Book book = bookMapper.selectByPrimaryKey(bId);
            int number = book.getBNumber();
            if (number <= 0){
                throw new RuntimeException("该书籍已经售完");
            }
            book.setBNumber(number-1);
            logger.info("还剩[{}]本书籍",number);
            int res = bookMapper.updateByPrimaryKeySelective(book);

            Book updateBook = bookMapper.selectByPrimaryKey(bId);

            return updateBook;
        }catch (Exception e){
            logger.error("该书籍已经售完");
            e.printStackTrace();
            throw e;
        }
    }
}
