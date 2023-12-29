package com.nimko.testrestproject.security;

import com.nimko.testrestproject.utils.JwtTokenUtils;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter{
  private final JwtTokenUtils utils;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                  FilterChain filterChain) throws ServletException, IOException {
    String authHeader = request.getHeader("Authorization");
    String username = null;
    String jwt;
    if (authHeader != null && authHeader.startsWith("Bearer ")) {
      jwt = authHeader.substring(7);
      try{
        username = utils.getUserName(jwt);
        log.info(username);
      } catch (ExpiredJwtException e) {
        log.debug("Token lifetime period is out!");
      } catch (SignatureException e) {
        log.debug("Signature is wrong!");
      }
    }
    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
      var token = new UsernamePasswordAuthenticationToken(
          username,null, null);
      SecurityContextHolder.getContext().setAuthentication(token);
    }
    filterChain.doFilter(request,response);
  }
}
