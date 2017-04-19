/**
 * 
 */
package br.com.makersweb.oauth.web.exception;

import org.springframework.security.core.AuthenticationException;

/**
 *
 * @author anderson.aristides
 *
 */
public class UserNotActivatedException extends AuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5329088119898413734L;

	public UserNotActivatedException(String msg, Throwable t) {
		super(msg, t);
	}

	public UserNotActivatedException(String msg) {
		super(msg);
	}

}
