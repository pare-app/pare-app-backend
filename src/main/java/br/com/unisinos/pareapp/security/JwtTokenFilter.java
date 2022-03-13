package br.com.unisinos.pareapp.security;

import br.com.unisinos.pareapp.model.entity.User;
import br.com.unisinos.pareapp.security.service.impl.JwtTokenService;
import br.com.unisinos.pareapp.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.removeStart;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenService jwtTokenService;
    private final IUserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        final String header = getAuthorizationHeader(request);
        if (isHeaderValid(header)) {
            chain.doFilter(request, response);
            return;
        }

        final String token = getToken(header);
        if(!jwtTokenService.validate(token)){
            chain.doFilter(request, response);
            return;
        }

        User user = userService.findByToken(token)
                            .orElseThrow(()->new UsernameNotFoundException("User not found!"));

        authenticate(request, user);
        chain.doFilter(request, response);
    }

    private void authenticate(HttpServletRequest request, User user) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                user, null,
                ofNullable(user).map(UserDetails::getAuthorities).orElse(new ArrayList<>())
        );
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String getToken(String header) {
        return ofNullable(header).map(value -> removeStart(value, "Bearer"))
                .map(String::trim).orElseThrow(() -> new BadCredentialsException("No Token Found!"));
    }

    private boolean isHeaderValid(String header) {
        return isEmpty(header) || !header.startsWith("Bearer ");
    }

    private String getAuthorizationHeader(HttpServletRequest request) {
        return ofNullable(request.getHeader(AUTHORIZATION)).orElse(request.getParameter("t"));
    }

}
