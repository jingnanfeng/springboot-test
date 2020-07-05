package cn.com.nanfeng.boot.service.impl;

import cn.com.nanfeng.boot.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2020-07-05 22:31
 */
@Service
@Slf4j
public class TestServiceImpl implements TestService {

    @Override
    public void testLog() {
        log.info("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
    }
}
