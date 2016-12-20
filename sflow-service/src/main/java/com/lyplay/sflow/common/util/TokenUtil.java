package com.lyplay.sflow.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: TokenUtil
 * @Description: token管理工具类
 * @author lyplay
 *
 */

public class TokenUtil {
	
	private static Logger logger = LoggerFactory.getLogger(TokenUtil.class);
	
	/**
	 * SECONDS
	 */
	public static long DEFAULT_EXPIRES = DateUtil.HOURS_IN_A_DAY * DateUtil.MINUTES_IN_AN_HOUR * DateUtil.SECONDS_IN_A_MINUTE;
	
	public static Map<String, Object> keyPair;
	
	static {
		try {
			keyPair = RSAUtil.genKeyPair();
		} catch (Exception e) {
			logger.error("Load local RSA key failed.");
			logger.error(e.getMessage(), e);
		}
	}
	
	public static String getJWTString(Map<String, Object> claims) {
		return getJWTString(claims, null);
	}
	
	public static String getJWTString(Map<String, Object> claims, Long expires) {
		if (MapUtils.isEmpty(claims)) {
			throw new NullPointerException("null claims is illegal");
		}
		
		claims.put("secret", RandomUtil.generateString(6));
		
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.RS256;
		
		long nowMillis = System.currentTimeMillis();
		long expiresMillis = nowMillis;
		if(expires != null){
			expiresMillis += expires * DateUtil.MILLIS_IN_A_SECOND;
		}else{
			expiresMillis += DEFAULT_EXPIRES * DateUtil.MILLIS_IN_A_SECOND;
		}
		
		Date now = new Date(nowMillis);
		Date expirationDate = new Date(expiresMillis);
		
		String jwtString = Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(now)
				.setExpiration(expirationDate)
				.signWith(signatureAlgorithm, RSAUtil.getPrivateKey(keyPair))
				.compact();
		return jwtString;
	}
	
	public static Claims parseJWT(String jwt) {
		if(isValid(jwt)){
			return Jwts.parser()        
				   .setSigningKey(RSAUtil.getPrivateKey(keyPair))
				   .parseClaimsJws(jwt).getBody();
		} else {
			return null;
		}
	}
	
	public static boolean isValid(String token) {
		try {
			Jwts.parser().setSigningKey(RSAUtil.getPrivateKey(keyPair)).parseClaimsJws(token.trim());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static String getName(String jwsToken) {
		if (isValid(jwsToken)) {
			Jws<Claims> claimsJws = Jwts.parser().setSigningKey(RSAUtil.getPrivateKey(keyPair))
					.parseClaimsJws(jwsToken);
			String name = String.valueOf(claimsJws.getBody().get("name"));
			return name;
		}
		return null;
	}

	public static String[] getRoles(String jwsToken) {
		if (isValid(jwsToken)) {
			Jws<Claims> claimsJws = Jwts.parser().setSigningKey(RSAUtil.getPrivateKey(keyPair))
					.parseClaimsJws(jwsToken);
			return claimsJws.getBody().getAudience().split(",");
		}
		return new String[] {};
	}

	public static int getVersion(String jwsToken) {
		if (isValid(jwsToken)) {
			Jws<Claims> claimsJws = Jwts.parser().setSigningKey(RSAUtil.getPrivateKey(keyPair))
					.parseClaimsJws(jwsToken);
			return Integer.parseInt(claimsJws.getBody().getId());
		}
		return -1;
	}

}