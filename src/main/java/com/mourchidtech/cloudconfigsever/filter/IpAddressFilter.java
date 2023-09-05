package com.mourchidtech.cloudconfigsever.filter;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mourchidtech.cloudconfigsever.dto.ResponseObject;
import com.mourchidtech.cloudconfigsever.service.IpAddressHelper;
import org.apache.http.HttpException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Component
public class IpAddressFilter implements Filter {
    private final List<String> ips = List.of("127.0.0.0","192.168.1.1","192.168.1.3");

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        String clientIp = IpAddressHelper.getClientIp();

        String ipFound =  ips.stream()
                                    .filter(ip -> ip.equals(clientIp))
                                    .findAny()
                                    .orElse(null);

        System.out.println("clientIp address =>  "+ clientIp +" ipFound => "+ipFound );
//        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse)servletResponse;

        if (ipFound == null || ipFound.isEmpty()) {
            ObjectMapper mapper = new ObjectMapper();
            ResponseObject object = new ResponseObject();

            object.setStatus(HttpServletResponse.SC_FORBIDDEN);
            object.setMessage("This ip address is not whitelisted");
            object.setError("Forbidden");
            object.setTimestamp(String.valueOf(new Date().getTime()));

            httpServletResponse.reset();
            httpServletResponse.setHeader("Content-Type","application/json;charset=UTF-8");
            httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            String json =  mapper.writeValueAsString(object);

            httpServletResponse.getWriter().write(json);

        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }
}


