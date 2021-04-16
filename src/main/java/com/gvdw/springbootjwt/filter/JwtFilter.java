package com.gvdw.springbootjwt.filter;

import com.gvdw.springbootjwt.services.CustomUserDetailsService;
import com.gvdw.springbootjwt.utilities.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 * Created By Gullian on Apr, 2021\
 *
 * This class authenticate username and token
 */
@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        // namespace: Bearer jwt: eyJhbGciOiJIUzI1NiJ9
        //                        .eyJzdWIiOiJ1c2VyMSIsImV4cCI6MTYxODU4ODg4NCwiaWF0IjoxNjE4NTUyODg0fQ
        //                        .P1y25rUV70o1t7Vb-cgS3WG1hYoYCHKHRIm_hvhpgkQ
        // extract token
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            String username, token = null;
            token = authorizationHeader.substring(7); // JWT token
            // Get username from token
            username = jwtUtil.extractUsername(token);
            // Authenticate username
            if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                UserDetails userDetails =  userDetailsService.loadUserByUsername(username);
                // Validate token
                if(jwtUtil.validateToken(token, userDetails)){
                    // If token is valid, validate user details
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    // User details are valid
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }

                filterChain.doFilter(request, response);
            }

        }
    }
}
