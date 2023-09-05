package com.mourchidtech.cloudconfigsever.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mourchidtech.cloudconfigsever.dto.ResponseObject;
import com.mourchidtech.cloudconfigsever.exception.ApiException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Component
public class AppKeyFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        System.out.println("Audit Filter Invoked...");

        HttpServletRequest httpReq = (HttpServletRequest) request;

        String headerValue = httpReq.getHeader("app-key");
        if (headerValue == null || headerValue.isEmpty()) {

            ObjectMapper mapper = new ObjectMapper();
            ResponseObject object = new ResponseObject();
            object.setStatus(HttpServletResponse.SC_FORBIDDEN);
            object.setMessage("Header app-key is required");
            object.setError("Forbidden");
            object.setTimestamp(String.valueOf(new Date().getTime()));
            HttpServletResponse httpResp = (HttpServletResponse) response;
            httpResp.reset();
            httpResp.setHeader("Content-Type","application/json;charset=UTF-8");
            httpResp.setStatus(HttpServletResponse.SC_FORBIDDEN);
            String json =  mapper.writeValueAsString(object);
            response.getWriter().write(json);
        } else {
            // Proceed with the request
            chain.doFilter(request, response);
        }
    }
}
