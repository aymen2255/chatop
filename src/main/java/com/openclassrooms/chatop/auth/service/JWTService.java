package com.openclassrooms.chatop.auth.service;

import java.util.Date;
import java.util.function.Function;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.openclassrooms.chatop.user.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {

	@Value("${jwt_expiration}")
	private long jwtExpiration;

	@Value("${secretkey}")
	private String secreteString;

	public String generateToken(User user) {
		return Jwts.builder().subject(user.getUsername()).claim("userId", user.getId())
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + jwtExpiration)).signWith(getSignInKey()).compact();
	}

	public Integer extractId(String token) {
		return extractClaims(token, claims -> claims.get("userId", Integer.class));
	}

	public String extractUsername(String token) {
		return extractClaims(token, Claims::getSubject);
	}

	private <T> T extractClaims(String token, Function<Claims, T> claimsFunction) {
		return claimsFunction
				.apply(Jwts.parser().verifyWith(getSignInKey()).build().parseSignedClaims(token).getPayload());
	}

	public boolean isTokenValid(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	public boolean isTokenExpired(String token) {
		return extractClaims(token, Claims::getExpiration).before(new Date());
	}

	public String getTokenFromAuthorizationHeader(String authorization) {

		if (authorization == null || authorization.isBlank() || !authorization.startsWith("Bearer ")) {
			throw new IllegalArgumentException("Le format de l'en-tête d'autorisation est incorrect");
		}

		return authorization.substring(7);
	}

	private SecretKey getSignInKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secreteString);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}