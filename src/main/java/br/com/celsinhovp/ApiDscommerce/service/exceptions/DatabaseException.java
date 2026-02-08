package br.com.celsinhovp.ApiDscommerce.service.exceptions;

@SuppressWarnings("serial")
public class DatabaseException extends RuntimeException {
    public DatabaseException(String msg) {
        super(msg);
    }
}
