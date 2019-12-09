package cn.com.nanfeng.test;

import cn.com.nanfeng.model.Country;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-12-09 14:59
 */
public class CountryMapperTest {

    private static SqlSessionFactory sqlSessionFactory;

    @BeforeClass
    public static void init(){
        try {
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            reader.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void testSelectAll(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            List<Country> countryList = sqlSession.selectList("selectAll");
            printCountryList(countryList);
        }finally {
            sqlSession.close();
        }
    }

    private void printCountryList(List<Country> countrieList){
        for (Country country : countrieList) {
            System.out.println(country);
        }
    }

}
