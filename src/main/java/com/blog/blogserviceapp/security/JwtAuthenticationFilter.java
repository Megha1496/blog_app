package com.blog.blogserviceapp.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String requestToken=request.getHeader("Authorization");
        System.out.println("Token is :: "+requestToken);
        String userName=null;
        String token=null;

        if (requestToken!=null && requestToken.startsWith("Bearer")){
            token= requestToken.substring(7);
            try {
                userName=this.jwtTokenHelper.extractUsername(token);

            }catch (IllegalArgumentException illegalArgumentException){
                System.out.println("Unable to get jwt token");

            }catch (ExpiredJwtException expiredJwtException){
                System.out.println("jwt token has expired");
            }catch (MalformedJwtException malformedJwtException){
                System.out.println("Invalid jwt token");
            }
        }else {
            System.out.println("jwt token does not begin with Bearer");
        }

        //once we get the token then we validate the token
        if (userName!=null && SecurityContextHolder.getContext().getAuthentication()==null){

            UserDetails userDetails= this.userDetailsService.loadUserByUsername(userName);
            if (this.jwtTokenHelper.validateToken(token,userDetails)){
                //authentication
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
               usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }else {
                System.out.println("Invalid jwt token");
            }

        }else {
            System.out.println("Username is null or context is null");

        }
        filterChain.doFilter(request,response);
    }
}
