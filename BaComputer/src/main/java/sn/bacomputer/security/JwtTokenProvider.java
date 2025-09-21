package sn.bacomputer.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import sn.bacomputer.exceptions.SecException;

import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;
@Component
public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-access-expiration}")
    private long jwtAccessExpiration;

    @Value("${app.jwt-refresh-expiration}")
    private long jwtRefreshExpiration;

    // génère access token (court)
    public String generateAccessToken(Authentication authentication) {
        String username = authentication.getName();
        String role = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtAccessExpiration);

        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key())
                .compact();
    }

    // génère refresh token (long)
    public String generateRefreshToken( Authentication authentication ) {
        String username = authentication.getName();
        String role = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtRefreshExpiration);


        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)

                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key())
                .compact();
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public String getUsername(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public String getRoles(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.get("role", String.class);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key())
                    .build()
                    .parse(token);
            return true;
        } catch (MalformedJwtException ex) {
            throw new SecException("le jeton JWT est invalide.", HttpStatus.UNAUTHORIZED);
        } catch (ExpiredJwtException ex) {
            throw new SecException("le jeton JWT a expiré.", HttpStatus.UNAUTHORIZED);
        } catch (UnsupportedJwtException ex) {
            throw new SecException("jeton JWT non supporté.", HttpStatus.UNAUTHORIZED);
        } catch (IllegalArgumentException ex) {
            throw new SecException("la chaîne claims est vide.", HttpStatus.UNAUTHORIZED);
        }
    }


}
