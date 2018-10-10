/*
 * File:   DaoException.java
 *
 * Created on 29/08/18, 19:44
 */
package br.com.ftec.higor.agendaweb.dao;

/**
 *
 * @author higor
 */
public class DaoException extends RuntimeException {

	public DaoException(String message) {
		super(message);
	}

	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}

	public DaoException(Throwable cause) {
		super(cause);
	}
}
