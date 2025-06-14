package com.playpass.backend.auth.infraestructure.conf;

import com.playpass.backend.auth.application.service.JwtService;
import com.playpass.backend.auth.domain.repository.TokenRepository;
import com.playpass.backend.auth.infraestructure.entity.Token;
import com.playpass.backend.user.infraestructure.entity.User;
import com.playpass.backend.user.domain.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {


    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(
           @NonNull HttpServletRequest request,
           @NonNull HttpServletResponse response,
           @NonNull FilterChain filterChain) throws ServletException, IOException {

        if(request.getServletPath().contains("/auth")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        final String jwtToken = authHeader.substring(7);
        final String userEmail=jwtService.extractUsername(jwtToken);

        if(userEmail == null|| SecurityContextHolder.getContext().getAuthentication() != null) {
            return;
        }
        final Token token=tokenRepository.findByToken(jwtToken).orElse(null);

        if(token == null||token.isExpired()||token.isRevoked()) {
            filterChain.doFilter(request, response);
            return;
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
        final Optional<User> user=userRepository.findByEmail(userDetails.getUsername());

        if(user.isEmpty()){
            filterChain.doFilter(request, response);
            return;
        }

        final boolean isTokenValid=jwtService.isTokenValid(jwtToken,user.get());

        if(!isTokenValid){
            return;
        }
        String role = jwtService.extractRole(jwtToken);
        Collection<? extends GrantedAuthority> authorities =
                List.of(new SimpleGrantedAuthority("ROLE_" + role));



        final UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(
                userDetails, null,authorities);
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);

        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        filterChain.doFilter(request, response);
    }
}
