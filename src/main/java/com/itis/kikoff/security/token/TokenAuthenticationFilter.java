package com.itis.kikoff.security.token;

import com.itis.kikoff.repositories.TokenRepository;
import com.itis.kikoff.repositories.UserRepository;
import com.itis.kikoff.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("X-TOKEN");
        if (token != null) {
            if (tokenService.check(token)) {
                TokenAuthentication tokenAuthentication = new TokenAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(tokenAuthentication);
                filterChain.doFilter(request, response);
            } else {
                throw new IllegalStateException();
            }
        }
        filterChain.doFilter(request, response);
    }
}
