package cn.com.nanfeng.boot.web.controller;

import cn.com.nanfeng.commit.exception.BusinessException;
import cn.com.nanfeng.commit.exception.ErrorCodeEnum;
import cn.com.nanfeng.commit.response.WrapMapper;
import cn.com.nanfeng.commit.response.Wrapper;
import cn.com.nanfeng.boot.model.vo.BookVO;
import cn.com.nanfeng.boot.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2020-05-10 12:11
 */
//@RestController
//@RequestMapping("/book")
public class BookController {

    //@Resource
    private BookService bookService;

    @GetMapping("/qureyBookDetail")
    public Wrapper<BookVO> qureyBookDetail(@RequestParam("bookId")Integer bookId){
        Wrapper<BookVO> wrapper = null;
        BookVO bookVO = bookService.queryBookDetail(bookId);
        if (bookVO != null){
            wrapper = WrapMapper.wrap(200,"查询成功",bookVO);
        }else {
            throw new BusinessException(ErrorCodeEnum.ERROR50001.getCode(),ErrorCodeEnum.ERROR50001.getMessage());
        }
        return wrapper;
    }
}
