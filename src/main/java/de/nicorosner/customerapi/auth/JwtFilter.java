package de.nicorosner.customerapi.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import javax.crypto.SecretKey;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor("12345678901234567890123456789012".getBytes());

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        System.out.println("🔥 JWT FILTER AKTIVIERT");
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            Claims claims = validateToken(token);
            System.out.println("🔑 Token erhalten: " + token);
            System.out.println("📄 Claims: " + claims);

            if (claims != null) {
                String username = claims.getSubject();
                Long id = claims.get("id", Long.class); // 👈 ID aus dem Token

                // 👤 CustomPrincipal mit ID + Username
                CustomPrincipal principal = new CustomPrincipal(id, username);

                List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(principal, null, authorities);

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);

                Authentication a = SecurityContextHolder.getContext().getAuthentication();
                System.out.println("🛡 SecurityContext auth: " + a);
                System.out.println("👤 Principal ID: " + principal.getId());
                System.out.println("👤 Principal Username: " + principal.getUsername());
                System.out.println("🔒 Authorities: " + (a != null ? a.getAuthorities() : "null"));
            }
        }
        chain.doFilter(request, response);
    }

    private Claims validateToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}