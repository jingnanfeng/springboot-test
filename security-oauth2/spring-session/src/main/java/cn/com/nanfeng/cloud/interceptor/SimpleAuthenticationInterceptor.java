package cn.com.nanfeng.cloud.interceptor;

import cn.com.nanfeng.cloud.model.UserDto;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2020-07-08 19:29
 */
@Component
public class SimpleAuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //校验用户请求的url是否在用户的权限范围内
        Object object = request.getSession().getAttribute(UserDto.SESSION_USER_KEY);
        if (object == null){
            //没有认证，提示登录
            writeContent(response,"请登录");
        }
        UserDto userDto = (UserDto)object;
        //请求的url
        String requestURI = request.getRequestURI();
        if (userDto.getAuthorities().contains("p1") && requestURI.contains("/user/getUserDetail")){
            return true;
        }
        if (userDto.getAuthorities().contains("p2") && requestURI.contains("/user/getUser")){
            return true;
        }
        writeContent(response,"没有权限，拒接访问");
        return false;
    }

    /**
     * 响应信息给客户端
     * @param response
     * @param msg
     * @throws IOException
     */
    private void writeContent(HttpServletResponse response,String msg) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.print(msg);
        writer.close();
        response.resetBuffer();
    }
}
