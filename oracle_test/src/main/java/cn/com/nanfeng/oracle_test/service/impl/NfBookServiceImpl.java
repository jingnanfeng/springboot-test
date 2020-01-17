package cn.com.nanfeng.oracle_test.service.impl;

import cn.com.nanfeng.oracle_test.mapper.NfBookMapper;
import cn.com.nanfeng.oracle_test.model.po.NfBook;
import cn.com.nanfeng.oracle_test.service.INfBookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-12-18 21:25
 */
@Service
public class NfBookServiceImpl implements INfBookService {

    private final Logger logger = LoggerFactory.getLogger(NfBookServiceImpl.class);

   // private static long id = 5;

    @Resource
    private NfBookMapper nfBookMapper;

    @Override
    public NfBook queryBookById(Long id) {
        try {
            NfBook book = nfBookMapper.selectByPrimaryKey(id);
            return book;

        }catch (RuntimeException e){
            e.printStackTrace();
            throw new RuntimeException("查询失败，查无数据");
        }
    }

    @Override
    //@Transactional(propagation = Propagation.REQUIRED,rollbackFor = RuntimeException.class)
    public int addBook(NfBook book) {
        try {
            //long ran2 = (long) (Math.random()*(1000)+1);
            long id  = 100;
            logger.info(">>>>>>>>>>>>>>>>>>>随机数:[{}]",id);
            int res = nfBookMapper.deleteByPrimaryKey(id);
            TimeUnit.MILLISECONDS.sleep(id);
            book.setId(id);
            book.setDelFlag("0");
            int i = nfBookMapper.insertSelective(book);
            logger.info("<<<<<<<<<<<<<<<<<<<id:[{}]",id);
            return i;
        }catch (RuntimeException e){
            e.printStackTrace();
            throw new RuntimeException("查询失败，查无数据");
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("线程错误");
        }
    }

}
