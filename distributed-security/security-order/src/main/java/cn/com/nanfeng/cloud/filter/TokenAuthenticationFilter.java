package cn.com.nanfeng.cloud.filter;

import cn.com.nanfeng.cloud.common.EncryptUtil;
import cn.com.nanfeng.cloud.model.UserDto;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2020-07-15 12:20
 */
@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //解析请求头中的token
        String token = request.getHeader("json-token");
        if (token != null){
            String json = EncryptUtil.decodeUTF8StringBase64(token);
            JSONObject jsonObject = JSON.parseObject(json);
            //用户信息
            UserDto user = new UserDto();
            user.setUsername(jsonObject.getString("principal"));
            //用户权限
            JSONArray authorizationArray = jsonObject.getJSONArray("authorizations");
            String[] authorities = authorizationArray.toArray(new String[authorizationArray.size()]);
            //将身份信息和权限填充到用户身份token对象中
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(user,null, AuthorityUtils.createAuthorityList(authorities));
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            //将authenticationToken对象填充到安全上下文中
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request,response);
    }
}
