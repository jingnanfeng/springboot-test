package cn.com.nanfneg.redislock.service.impl;

import cn.com.nanfeng.commit.common.CommonStatic;
import cn.com.nanfeng.commit.exception.BusinessException;
import cn.com.nanfeng.commit.exception.ErrorCodeEnum;
import cn.com.nanfneg.redislock.mapper.BookMapper;
import cn.com.nanfneg.redislock.runner.BloomFilterHelper;
import cn.com.nanfneg.redislock.runner.RedisBloomService;
import cn.com.nanfneg.redislock.service.IBookService;
import lombok.extern.slf4j.Slf4j;
import cn.com.nanfneg.redislock.model.po.Book;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author liutao
 * @title BookServiceImpl
 * @description
 * @date 2020-08-02 0:03
 */
@Slf4j
@Service
public class BookServiceImpl implements IBookService {

    @Resource
    private RedisBloomService redisBloomService;
    @Resource
    private BloomFilterHelper<String> bloomFilterHelper;
    @Resource
    private BookMapper bookMapper;


    @Override
    public Book getBookDetailByName(String bookName) {
        try {
            //查看bookName是否存在布隆过滤器
            boolean b = redisBloomService.includeByBloomFilter(bloomFilterHelper, CommonStatic.BOOK_NAME, bookName);
            //如果不存在
            if (!b){
                throw new BusinessException(ErrorCodeEnum.COMMON1110.getCode(),"bloom中无法查询到该书籍");
            }
            //通过书名查询图书信息
            Book book = bookMapper.selectBookByName(bookName);
            if (book == null){
                throw new BusinessException(ErrorCodeEnum.COMMON1110.getCode(),"数据库中无法查询到该书籍");
            }
            return book;
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }
}
