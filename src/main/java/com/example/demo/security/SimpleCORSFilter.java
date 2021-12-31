package com.example.demo.security;

import com.example.demo.config.AppConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@RequiredArgsConstructor
public class SimpleCORSFilter implements Filter {

    private final AppConfig appConfig;

    List<String> allowedOrigins = new ArrayList<>();
    List<String> allowedMethods = new ArrayList<>();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {

        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;

        allowedOrigins.add(appConfig.getFrontendUrl());
        allowedOrigins.add(appConfig.getFrontendHttpUrl());
        allowedMethods.addAll(Arrays.asList("POST", "PUT", "GET", "DELETE", "OPTIONS"));

        String reqOrigin;
        reqOrigin = req.getHeader("Origin");

        for (String origin : allowedOrigins) {
            if (origin.equals(reqOrigin)) {
                res.setHeader("Access-Control-Allow-Origin", reqOrigin);
                break;
            }
        }

        if (req.getMethod().equals("OPTIONS")) {


            String reqMethod;
            reqMethod = req.getHeader("Access-Control-Request-Method");

            for (String method : allowedMethods) {
                if (method.equals(reqMethod)) {
                    res.setHeader("Access-Control-Allow-Methods", reqMethod);
                }
            }

            res.setHeader("Access-Control-Max-Age", "3600");
            res.setHeader("Access-Control-Allow-Credentials", "true");
            res.setHeader("Access-Control-Allow-Headers",
                    "cache-control, if-modified-since, pragma, Content-Type, Authorization, "
                            + "Access-Control-Allow-Headers, X-Requested-With, Expires");


            //Checks if Allow-Method and Allow-Origin got set. 200 OK if both are set.
            if (!res.getHeader("Access-Control-Allow-Methods").equals("") && !res.getHeader("Access-Control-Allow-Origin").equals("")) {
                res.setStatus(200);
            }
        } else {
            filterChain.doFilter(req, res);
        }
    }
}
