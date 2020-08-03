package cn.com.nanfneg.redislock.mapper;

import cn.com.nanfneg.redislock.model.po.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface BookMapper {
    int deleteByPrimaryKey(Integer bId);

    int insert(Book record);

    int insertSelective(Book record);

    Book selectByPrimaryKey(Integer bId);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);

    /**
     * 查询所有的书名
     * @return
     */
    List<String> selectBookName();

    /**
     * 查询书籍信息
     * @param bookName
     * @return
     */
    Book selectBookByName(@Param("bookName")String bookName);

}