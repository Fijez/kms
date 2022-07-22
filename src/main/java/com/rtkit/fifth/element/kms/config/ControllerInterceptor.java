package com.rtkit.fifth.element.kms.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Scope(value = "prototype")
@AllArgsConstructor(onConstructor_={@Autowired})
public class ControllerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String method = request.getMethod();
        String remoteUser = request.getRemoteUser();
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
