package cn.com.nanfeng.oracle_test.web.controller;

import cn.com.nanfeng.oracle_test.model.po.NfBook;
import cn.com.nanfeng.oracle_test.service.INfBookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-12-18 21:48
 */
@RestController
public class NfBookController {

    private static final Logger logger = LoggerFactory.getLogger(NfBookController.class);

    @Resource
    private INfBookService nfBookService;

    @GetMapping("/book/queryBookById/{id}")
    public NfBook queryBookById(@PathVariable("id")Long id){
        NfBook book = nfBookService.queryBookById(id);
        return book;
    }

    @PostMapping("book/addBook")
    public int addBook(NfBook book){
        try {
            TimeUnit.MILLISECONDS.sleep(10000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        int res = nfBookService.addBook(book);
        return res;
    }

}
