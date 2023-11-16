package org.finalproject.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;
@Component
public class JWTUtil {
    @Value("${jwt_secret}")
    private String secret;

    public String generateJWT(String email){
        Date expirationDate = Date.from(ZonedDateTime.now().plusMinutes(60).toInstant());
        return JWT
                .create()
                .withSubject("User details")
                .withIssuedAt(new Date())
                .withExpiresAt(expirationDate)
                .withClaim("username", email)
                .withIssuer("bulletin board")
                .sign(Algorithm.HMAC256(secret));
    }
    public String validateTokenAndGetClaim(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT
                .require(Algorithm.HMAC256(secret))
                .withSubject("User details")
                .withIssuer("bulletin board")
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("username").asString();
    }
}
