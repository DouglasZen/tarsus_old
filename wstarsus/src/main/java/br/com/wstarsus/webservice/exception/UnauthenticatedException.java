package br.com.wstarsus.webservice.exception;

public class UnauthenticatedException extends RuntimeException {
	public UnauthenticatedException(String s) {
        super(s);
    }
}
