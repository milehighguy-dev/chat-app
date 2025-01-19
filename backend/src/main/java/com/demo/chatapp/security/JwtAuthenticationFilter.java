package com.demo.chatapp.security;

import com.demo.chatapp.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    JwtAuthenticationFilter( JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader("Authorization");

        if ( token != null && token.startsWith("Bearer")) {
            token = token.substring(7);
            String userName = jwtUtil.extractUsername(token);

            if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                //TODO authentication
            }
        }
        filterChain.doFilter(request,response);

    }
}
