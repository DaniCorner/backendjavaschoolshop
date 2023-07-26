package com.javaschoolshop;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//I needed this part to make possible in-page changes
@Component
@Order(Ordered.HIGHEST_PRECEDENCE) // This filter will be executed first
public class CorsFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;//getMethod,getParameter,getHeaderNames
        HttpServletResponse response = (HttpServletResponse) res; //SetStatus code (200, 404), setHeader,SetcontentType

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, Accept, x-requested-with, Cache-Control");

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) { //1º OPTION (preflight, check if CORS is understood and method allowed)
            response.setStatus(HttpServletResponse.SC_OK); //2º Retrieves HTTP method (GET, POST...)
        } else {
            chain.doFilter(req, res); //The petition is not stuck in the filter, it goes forward (servlet)
        }
    }
}
