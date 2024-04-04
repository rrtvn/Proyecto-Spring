package cl.nvrrt.cvseguro.config.security.jwt.filter;

import java.io.IOException;


import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import cl.nvrrt.cvseguro.config.UserDetailsImpl;
import cl.nvrrt.cvseguro.config.UserDetailsServiceImpl;
import cl.nvrrt.cvseguro.config.security.jwt.service.JWTUtil;


@Component
public class JwtFilter extends OncePerRequestFilter {

    private Logger logger = LoggerFactory.getLogger(OncePerRequestFilter.class);

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // Authorization=Bearer token
        String requestHeader = request.getHeader("Authorization");
        logger.info("Header: {}", requestHeader);
        String token = null;
        String username = null;
        if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
            
            token = requestHeader.substring(7);
            username = jwtUtil.getUsrFromToken(token);
            
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
    
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

    
                if (jwtUtil.validateJwtToken(token, userDetails)) {
    
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
            
        }


        filterChain.doFilter(request, response);
    }

    

}
