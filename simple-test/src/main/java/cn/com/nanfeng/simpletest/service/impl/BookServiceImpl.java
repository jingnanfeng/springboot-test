package cn.com.nanfeng.simpletest.service.impl;

import cn.com.nanfeng.commit.exception.BusinessException;
import cn.com.nanfeng.commit.exception.ErrorCodeEnum;
import cn.com.nanfeng.simpletest.mapper.BookMapper;
import cn.com.nanfeng.simpletest.model.po.Book;
import cn.com.nanfeng.simpletest.model.vo.BookVO;
import cn.com.nanfeng.simpletest.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2020-05-10 10:28
 */
@Service
public class BookServiceImpl implements BookService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookServiceImpl.class);

    @Resource
    private BookMapper bookMapper;

    @Override
    public BookVO queryBookDetail(Integer bookId) {
        Book book = bookMapper.selectByPrimaryKey(bookId);
        if (book == null){
            throw new BusinessException(ErrorCodeEnum.ERROR10001.getCode(),"无法查询到相应书籍,请刷新后重试");
        }
        BookVO bookVO = BookVO.builder()
                .bId(bookId)
                .bName(book.getBName())
                .bContent(book.getBContent())
                .bImage(book.getBImage())
                .bDate(book.getBDate())
                .bNumber(book.getBNumber())
                .bPrice(book.getBPrice()).build();

        return bookVO;
    }
}
