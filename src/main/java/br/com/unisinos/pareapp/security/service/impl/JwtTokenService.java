package br.com.unisinos.pareapp.security.service.impl;

import br.com.unisinos.pareapp.security.service.TokenService;
import com.google.common.collect.ImmutableMap;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.compression.GzipCompressionCodec;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Map;
import java.util.function.Supplier;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;
import static io.jsonwebtoken.impl.TextCodec.BASE64;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtTokenService implements Clock, TokenService {
    private static final GzipCompressionCodec COMPRESSION_CODEC = new GzipCompressionCodec();

    @Value("${app.security.issuer}")
    private final String issuer = "pare-app";
    @Value("${app.security.secretKey}")
    private final String secretKey = BASE64.encode("www.pareapp.com.br");
    @Value("${app.security.tokenExpirationMinutes}")
    private final int tokenExpirationMinutes = 5;

    public String newToken(Map<String, String> attributes) {
        DateTime now = DateTime.now();
        Claims claims = Jwts.claims()
                .setIssuer(issuer)
                .setIssuedAt(now.toDate());

        claims.putAll(attributes);
        return Jwts.builder()
                .setClaims(claims)
                .signWith(HS256, secretKey)
                .setExpiration(
                        Date.from(ZonedDateTime.now()
                            .plusMinutes(tokenExpirationMinutes)
                            .toInstant()))
                .compressWith(COMPRESSION_CODEC)
                .compact();
    }

    @Override
    public Map<String, String> verify(String token) {
        JwtParser parser = Jwts.parser().requireIssuer(issuer).setClock(this).setSigningKey(secretKey);
        return parseClaims(() -> parser.parseClaimsJws(token).getBody());
    }

    @Override
    public boolean validate(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature - {}", ex.getMessage());
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token - {}", ex.getMessage());
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token - {}", ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token - {}", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty - {}", ex.getMessage());
        }
        return false;
    }

    private static Map<String, String> parseClaims(Supplier<Claims> toClaims) {
        try {
            Claims claims = toClaims.get();
            ImmutableMap.Builder<String, String> builder = ImmutableMap.builder();
            for (Map.Entry<String, Object> e: claims.entrySet()) {
                builder.put(e.getKey(), String.valueOf(e.getValue()));
            }
            return builder.build();
        } catch (IllegalArgumentException | JwtException e) {
            return ImmutableMap.of();
        }
    }

    @Override
    public Date now() {
        DateTime now = DateTime.now();
        return now.toDate();
    }
}
