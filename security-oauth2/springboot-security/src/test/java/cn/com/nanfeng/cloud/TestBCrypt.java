package cn.com.nanfeng.cloud;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2020-07-09 21:42
 */
@RunWith(SpringRunner.class)
@Slf4j
public class TestBCrypt {
    
    @Test
    public void testBCrypt(){
        String hashpw = BCrypt.hashpw("secret", BCrypt.gensalt());
        log.info("==============================================================hashpw=[{}]",hashpw);

    }
}
