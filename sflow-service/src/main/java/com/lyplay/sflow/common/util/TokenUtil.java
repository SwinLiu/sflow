package com.lyplay.sflow.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: TokenUtil
 * @Description: token管理工具类
 * @author lyplay
 *
 */

public class TokenUtil {
	
	public static long defaultExpires = 2 * DateUtil.MINUTES_IN_AN_HOUR * DateUtil.SECONDS_IN_A_MINUTE * DateUtil.MILLIS_IN_A_SECOND;
	public static Map<String, Object> keyPair = null;
	
	public static void main(String[] args) {
		
		try {
			keyPair = RSAUtil.genKeyPair();
		} catch (Exception e) {
		}
		
		Map<String,Object> claims = new HashMap<String,Object>();
        claims.put("acessToken", "adasd");
        claims.put("companyname", "CompName");
		
		String token = TokenUtil.getJWTString("subjectDemo", RSAUtil.getPrivateKey(keyPair), claims);
		
		System.out.println(token);
		
		TokenUtil.parseJWT(token);
		
	}
	
	public static String getJWTString(String tel, Key key,	Map<String, Object> claims) {
		if (tel == null) {
			throw new NullPointerException("null username is illegal");
		}
		
		if (key == null) {
			throw new NullPointerException("null key is illegal");
		}
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.RS256;
		
		long nowMillis = System.currentTimeMillis();
		nowMillis += 2 * 60 * 60 * 1000;
		Date now = new Date(nowMillis);
		
		String jwtString = Jwts.builder()
				.setClaims(claims)
				.setIssuer("Jersey-Security-Basic")
				.setSubject(tel)
				.setAudience("user")
				.setExpiration(now)
				.setId("demoId")
				.signWith(signatureAlgorithm, key)
				.compact();
		return jwtString;
	}
	
	//Sample method to validate and read the JWT
	public static void parseJWT(String jwt) {
		//This line will throw an exception if it is not a signed JWS (as expected)
		Claims claims = Jwts.parser()        
		   .setSigningKey(RSAUtil.getPrivateKey(keyPair))
		   .parseClaimsJws(jwt).getBody();
		System.out.println("ID: " + claims.getId());
		System.out.println("Subject: " + claims.getSubject());
		System.out.println("Issuer: " + claims.getIssuer());
		System.out.println("Expiration: " + claims.getExpiration());
	}

	public static boolean isValid(String token, Key key) {
		try {
			Jwts.parser().setSigningKey(key).parseClaimsJws(token.trim());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static String getName(String jwsToken, Key key) {
		if (isValid(jwsToken, key)) {
			Jws<Claims> claimsJws = Jwts.parser().setSigningKey(key)
					.parseClaimsJws(jwsToken);
			String name = String.valueOf(claimsJws.getBody().get("name"));
			return name;
		}
		return null;
	}

	public static String[] getRoles(String jwsToken, Key key) {
		if (isValid(jwsToken, key)) {
			Jws<Claims> claimsJws = Jwts.parser().setSigningKey(key)
					.parseClaimsJws(jwsToken);
			return claimsJws.getBody().getAudience().split(",");
		}
		return new String[] {};
	}

	public static int getVersion(String jwsToken, Key key) {
		if (isValid(jwsToken, key)) {
			Jws<Claims> claimsJws = Jwts.parser().setSigningKey(key)
					.parseClaimsJws(jwsToken);
			return Integer.parseInt(claimsJws.getBody().getId());
		}
		return -1;
	}

}