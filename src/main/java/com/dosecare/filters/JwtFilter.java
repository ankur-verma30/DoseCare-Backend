package com.dosecare.filters;

import com.dosecare.repository.UserRepository;
import com.dosecare.service.CustomUserDetailsService;
import com.dosecare.utils.JwtUtil;
import com.dosecare.utils.UserPrincipal;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    private final CustomUserDetailsService customUserDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
          if(jwtUtil.validateToken(token)){
          String email=jwtUtil.extractClaims(token).getSubject();

              UserPrincipal userPrincipal=(UserPrincipal) customUserDetailsService.loadUserByUsername(email);

              UsernamePasswordAuthenticationToken authenticationToken =
                      new UsernamePasswordAuthenticationToken(userPrincipal,null, userPrincipal.getAuthorities());
              System.out.println("Authorities: " + authenticationToken.getAuthorities());



              SecurityContextHolder.getContext().setAuthentication(authenticationToken);
              System.out.println("this is the jwtUtil.extractClaims(token): " + jwtUtil.extractClaims(token));


          }
        }
        filterChain.doFilter(request, response);
    }
}
