package cn.com.nanfeng.cloud.interceptor;

import cn.com.nanfeng.cloud.common.WrapMapper;
import cn.com.nanfeng.cloud.common.Wrapper;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2020-07-18 12:11
 */
@Slf4j
@Component
public class GlobalInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("json-token");
        //请求头中是否有自定义的请求头
        if (StringUtils.isEmpty(token)){
            response.setStatus(HttpStatus.OK.value());
            response.setHeader("Content-Type","application/json;charset=UTF-8");
            Wrapper<Object> wrapper = WrapMapper.wrap(401, "坏的凭证");
            String result = JSON.toJSONString(wrapper);
            response.getWriter().write(result);
            return false;
        }
        return true;
    }
}
