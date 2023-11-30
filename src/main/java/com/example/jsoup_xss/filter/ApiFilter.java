package com.example.jsoup_xss.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

/*
init()
웹 컨테이너(톰캣)이 시작될 때 필터 최초 한 번 인스턴스 생성

doFilter()
클라이언트의 요청 시 전/후 처리
FilterChain을 통해 전달

public void destroy()
필터 인스턴스가 제거될 때 실행되는 메서드, 종료하는 기능
 */

@WebFilter(urlPatterns = "/*")
public class ApiFilter implements Filter {


    /*
        - 필터 인스턴스 초기화
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws
        IOException,
        ServletException {
        String contentType = request.getContentType();

        // Content-Type이 "application/json"인지 확인합니다.
        if (contentType != null && contentType.toLowerCase().contains("application/json")) {
            chain.doFilter(new RequestBodyWrapper((HttpServletRequest) request), response);
        } else {

            chain.doFilter(new RequestParamWrapper((sHttpServletRequest) request), response);
        }

    }

    /*
			- 필터 인스턴스 종료
		 */
    @Override
    public void destroy() {
    }
}