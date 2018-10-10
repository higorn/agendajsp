package br.com.ftec.higor.agendaweb.service.exception;

/**
 * @author higor
 */
public class AutenticacaoException extends Exception {

  public AutenticacaoException(String message) {
    super(message);
  }

  public AutenticacaoException(String message, Throwable cause) {
    super(message, cause);
  }
}
