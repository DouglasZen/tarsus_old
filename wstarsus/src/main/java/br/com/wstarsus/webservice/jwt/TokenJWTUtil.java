package br.com.wstarsus.webservice.jwt;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenJWTUtil {
	private static KeyGenerator keyGenerator = new KeyGenerator();
	
	public static String gerarToken(String email){
		Calendar now = Calendar.getInstance();
		Calendar tomorrow = Calendar.getInstance();
		int hoje = now.get(Calendar.DAY_OF_MONTH);
		tomorrow.set(Calendar.DAY_OF_MONTH, hoje + 1);
		tomorrow.set(Calendar.HOUR_OF_DAY, 6);
		tomorrow.set(Calendar.MINUTE, 0);
		Long dif = ((tomorrow.getTimeInMillis() - now.getTimeInMillis())/ 1000)/60;
		
		Key key = keyGenerator.generatedKey();
		String jwtToken = Jwts.builder()
							.signWith(SignatureAlgorithm.HS256, key)
							.setHeaderParam("typ", "JWT")
							.setSubject(email)
							.setIssuer("Tarsus")
							.setIssuedAt(new Date())
							.setExpiration(toDate(LocalDateTime.now().plusMinutes(dif)))
							.claim("role", "")
							.compact();
		return jwtToken;
	}

	private static Date toDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	public static boolean tokenValido(String token, Key key) {
		try{
			Jwts.parser().setSigningKey(key).parseClaimsJws(token);
			return true;
		}catch(Exception e){
			return false;
		}
		
	}

	public static String recuperarEmail(String token, Key key) {
		Jws<Claims> claimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(token);
		return claimsJws.getBody().getSubject();
	}
	
}
