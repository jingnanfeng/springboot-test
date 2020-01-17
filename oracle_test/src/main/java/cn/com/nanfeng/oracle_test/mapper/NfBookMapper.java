package cn.com.nanfeng.oracle_test.mapper;


import cn.com.nanfeng.oracle_test.model.po.NfBook;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface NfBookMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NfBook record);

    int insertSelective(NfBook record);

    NfBook selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NfBook record);

    int updateByPrimaryKey(NfBook record);
}