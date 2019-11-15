package cn.com.nanfeng.securitytest.smscode;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2019-10-21 20:37
 */
@RestController
public class VailDateController {

    private static final Logger logger = LoggerFactory.getLogger(VailDateController.class);

    public final static String SESSION_KEY_MSM_CODE = "SESSION_KEY_SMS_CODE";

    public SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @GetMapping("/code/sms")
    public void createSmsCode(HttpServletRequest request, HttpServletResponse response,
                              String mobile) throws IOException{
        SmsCode smsCode = createSMSCode();
        sessionStrategy.setAttribute(new ServletWebRequest(request),SESSION_KEY_MSM_CODE+mobile,smsCode);
        logger.info("您登录的验证码为：{},有效时间为60",smsCode.getCode());

    }

    private SmsCode createSMSCode(){
        String code = RandomStringUtils.randomNumeric(6);
        return new SmsCode(code,60);
    }

}
