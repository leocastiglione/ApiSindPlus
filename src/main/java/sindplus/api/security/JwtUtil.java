package sindplus.api.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {	
	
	  @Value("${jwt.secret}")
	  private String secret;
	  
	  private static final long EXPIRATION = 86_400_000L;

	  private SecretKey getKey() {
	    return Keys.hmacShaKeyFor(
	        Decoders.BASE64.decode(secret)
	    );
	  }

	  
	  public String generateToken(String email) {
	    return Jwts.builder()
	        .subject(email)
	        .issuedAt(new Date())
	        .expiration(new Date(System.currentTimeMillis() + EXPIRATION))
	        .signWith(getKey())
	        .compact();
	  }

	  
	  public String extractEmail(String token) {
	    return Jwts.parser()
	        .verifyWith(getKey())
	        .build()
	        .parseSignedClaims(token)
	        .getPayload()
	        .getSubject();
	  }

	  
	  public boolean isValid(String token, UserDetails userDetails) {
	    try {
	      String email = extractEmail(token);
	      return email.equals(userDetails.getUsername());	      
	    } catch (JwtException e) {
	      return false;
	    }
	  }
	}
