package br.com.mmt.helpdesk.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private JWTUtil jwtUtil;
    private UserDetailsService userDetailsService;

    public JwtAuthorizationFilter(
            AuthenticationManager authenticationManager,
            JWTUtil jwtUtil,
            UserDetailsService userDetailsService) {
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
        this.userDetailsService =  userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String authorization = request.getHeader("Authorization");
        if(authorization != null && authorization.startsWith("Bearer ")){
            UsernamePasswordAuthenticationToken authToken = getAuthentication(authorization.substring(7));

            if(authToken != null){
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
        if(jwtUtil.isTokenValido(token)){
            String username = jwtUtil.getUsername(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());

        }
        return null;
    }
}
