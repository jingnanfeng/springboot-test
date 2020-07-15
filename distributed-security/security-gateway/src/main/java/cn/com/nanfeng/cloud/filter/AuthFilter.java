package cn.com.nanfeng.cloud.filter;

import cn.com.nanfeng.cloud.common.EncryptUtil;
import com.alibaba.fastjson.JSON;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liutao
 * @Title
 * @Description
 * @date 2020-07-15 11:23
 */
public class AuthFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {

        RequestContext ctx = RequestContext.getCurrentContext();
        //从上下文上面获取信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof OAuth2Authentication)){
            return null;
        }
        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) authentication;
        Authentication userAuthentication = oAuth2Authentication.getUserAuthentication();
        //取出用户身份
        String principal = userAuthentication.getName();
        //取出用户的权限
        List<String> authorizations = new ArrayList<>();
        userAuthentication.getAuthorities().stream().forEach(c
                -> authorizations.add(((GrantedAuthority) c).getAuthority()));
        //其他信息
        OAuth2Request oAuth2Request = oAuth2Authentication.getOAuth2Request();
        Map<String, String> requestParameters = oAuth2Request.getRequestParameters();
        //把身份信息和权限信息放到json中，加入http的header中
        Map<String,Object> jsonToken = new HashMap<>(requestParameters);
        if (userAuthentication != null){
            jsonToken.put("principal",principal);
            jsonToken.put("authorizations",authorizations);
        }
        //转发给微服务
        ctx.addZuulRequestHeader("json-token", EncryptUtil.encodeUTF8StringBase64(JSON.toJSONString(jsonToken)));
        return null;
    }
}
