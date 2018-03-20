package br.com.wstarsus.webservice.jwt;

import java.security.Key;

import javax.crypto.spec.SecretKeySpec;

public class KeyGenerator {
	public Key generatedKey(){
		//tarsusapp + sha256 + base64
		String keyString = "MDZmMDM1Y2E0MzVlY2ZjNTAyZjYwNmYzY2VlMThhZWUzNGM0YzUzM2QwNDhjNzRiZTc4OTUyNTFiYTEyNzc0OA==";
		Key key = new SecretKeySpec(keyString.getBytes(), 0, keyString.getBytes().length, "HmacSHA256");
		return key;
	}
}
