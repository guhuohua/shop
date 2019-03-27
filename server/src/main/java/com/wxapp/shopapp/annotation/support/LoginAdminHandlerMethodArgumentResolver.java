package com.wxapp.shopapp.annotation.support;

import com.wxapp.shopapp.annotation.LoginAdmin;
import com.wxapp.shopapp.server.TokenManage;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class LoginAdminHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {


    public static final String LOGIN_TOKEN_KEY = "Admin-Token";

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(Integer.class)&&parameter.hasParameterAnnotation(LoginAdmin.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer container,
                                  NativeWebRequest request, WebDataBinderFactory factory) throws Exception {

//        return new Integer(1);
        String token = request.getHeader(LOGIN_TOKEN_KEY);
        System.out.println("token[" + token + "]");
        if(token == null || token.isEmpty()){
            return null;
        }

        return TokenManage.getUserId(token);
    }

}
