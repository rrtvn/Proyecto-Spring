package cl.nvrrt.cvseguro.config.security.jwt.filter;

import java.io.IOException;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import cl.nvrrt.cvseguro.config.UserDetailsServiceImpl;
import cl.nvrrt.cvseguro.config.security.jwt.service.JWTUtil;


@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private Logger logger = LoggerFactory.getLogger(OncePerRequestFilter.class);

    @Autowired 
    private JWTUtil jwtUtil;

    @Autowired
    private UserDetailsServiceImpl userService;

    @Override
    protected void doFilterInternal(
             HttpServletRequest request, 
            @NonNull HttpServletResponse response, 
            @NonNull FilterChain filterChain
            ) throws ServletException, IOException {
        // Authorization=Bearer token
        String authHeader = request.getHeader("Authorization");
        // logger.info("Header: {}", requestHeader);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            
            String token = authHeader.substring(7);
             
            String userEmail = jwtUtil.extractUsername(token);
            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
    
                UserDetails userDetails = userService.loadUserByUsername(userEmail.toString());
    
    
                if (jwtUtil.isTokenValid(token, userDetails)) {

              UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
    
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }
            
            
            filterChain.doFilter(request, response);
        }


    }

    


