package com.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private  JwtTokenProvider tokenProvider;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // TODO Auto-generated method stub
        String token=getJWTfromRequest(request);

        //Validate Token
        try {
            if(StringUtils.hasText(token)&&tokenProvider.validateToken(token)) {
                //get username from token
                String username = tokenProvider.getUsernameFromJWT(token);

                //load User associated with token

                UserDetails userDetails=customUserDetailsService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken authenticationToken= new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        } catch (UsernameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (BlogAPIException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        filterChain.doFilter(request, response);
    }
    //Bearer<accessToken>

    private String getJWTfromRequest(HttpServletRequest request) {
        String bearerToken= request.getHeader("authorization");
        if(StringUtils.hasText(bearerToken)&& bearerToken.startsWith("bearer")) {

            return bearerToken.substring(7,bearerToken.length());
        }
        return null;


    }
}
