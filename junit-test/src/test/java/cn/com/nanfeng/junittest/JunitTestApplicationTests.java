package cn.com.nanfeng.junittest;

import org.junit.*;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JunitTestApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(JunitTestApplicationTests.class);


    @BeforeClass
    public static void beforeClassTest(){
        logger.info("before class test");
    }

    @Before
    public void beforeTest(){
        logger.info("before test");
    }

    @Test
    public void Test1(){
        logger.info("test 1+1=2");
        Assert.assertEquals(2,1 + 1);
    }

    @Test
    public void Test2(){
        logger.info("test 2+2=5");
        Assert.assertEquals(4,2+2);
    }

   @After
    public void afterTest(){
        logger.info("after test");
   }

   @AfterClass
    public static void afterClassTest(){
        logger.info("after class test");
   }

}
