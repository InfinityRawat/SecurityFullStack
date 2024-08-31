package com.Security.FullStack.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	private byte[] encodeSecret;
	
	public JwtService() {
		try {
			SecretKey key = KeyGenerator.getInstance("HmacSHA256").generateKey();
			
			encodeSecret = Base64.getEncoder().encode(key.getEncoded());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

		public String generateJWTToken(String username) {
				Map<String,String >claims = new HashMap<>();
				return Jwts.builder().claims().add(claims).subject(username).issuedAt(new Date(System.currentTimeMillis()))
						.expiration(new Date(System.currentTimeMillis()+60*60*1000))
						.and()
						.signWith(sighKey())
						.compact();
		}
		
		private SecretKey sighKey() {
			return Keys.hmacShaKeyFor(encodeSecret);
 
		}

		public String extractUserNameFromToken(String token) {
	        // extract the username from jwt token
	        return extractClaim(token, Claims::getSubject);
	    }

	    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
	        final Claims claims = extractAllClaims(token);
	        return claimResolver.apply(claims);
	    }

	    private Claims extractAllClaims(String token) {
	        return Jwts.parser().verifyWith(sighKey())
	                .build()
	                .parseSignedClaims(token)
	                .getPayload();
	    }
	    
	    
	    private Date extractExpiration(String token) {
	        return extractClaim(token, Claims::getExpiration);
	    }
	    
	    private boolean isTokenExpired(String token) {
	        return extractExpiration(token).before(new Date());
	    }

		public  boolean validateToken(String jwt, UserDetails userByUsername) {
			String userName = extractUserNameFromToken(jwt);
			return userName.equals(userByUsername.getUsername()) && !isTokenExpired(jwt);
		}

}
