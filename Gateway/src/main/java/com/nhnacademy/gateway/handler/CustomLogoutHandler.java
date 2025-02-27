package com.nhnacademy.gateway.handler;

import com.nhnacademy.gateway.utils.loginUtil.SessionService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomLogoutHandler implements LogoutSuccessHandler {
    private final SessionService sessionService;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
        Cookie[] cookies = request.getCookies();
        if (cookies != null)
            for (Cookie c : cookies) {
                if (c.getName().equals("SESSION")) {
                    sessionService.removeUser(c.getValue());
                }
            }

        response.sendRedirect("/login");
    }

}
