package bbl.agent.banking.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * @author Nusrat Jahan Tarin
 */

public class AgentBankingException{

	@Getter
	@RequiredArgsConstructor
	public static class ServiceException extends RuntimeException {
		private final int code;
		private final String message;
	}

	public static class AuthenticationException extends ServiceException {
		public AuthenticationException(String message) {
			super(HttpStatus.UNAUTHORIZED.value(), message);
		}
	}

	public static class ProxyAuthenticationException extends ServiceException {
		public ProxyAuthenticationException(String message) {
			super(HttpStatus.PROXY_AUTHENTICATION_REQUIRED.value(), message);
		}
	}

	public static class AlreadyExistsException extends ServiceException {
		public AlreadyExistsException(String message) {
			super(HttpStatus.CONFLICT.value(), message);
		}
	}

	public static class NotFoundException extends ServiceException {
		public NotFoundException(String message) {
			super(HttpStatus.NOT_FOUND.value(), message);
		}
	}

	public static class BadRequestException extends ServiceException {
		public BadRequestException() {
			super(HttpStatus.BAD_REQUEST.value(), "Sorry, your request can't be processed.");
		}

		public BadRequestException(String message) {
			super(HttpStatus.BAD_REQUEST.value(), message);
		}
	}

	public static class InvalidDateFormatException extends ServiceException {
		public InvalidDateFormatException(String message) {
			super(HttpStatus.BAD_REQUEST.value(), message);
		}
	}

	public static class InvalidDataException extends ServiceException {
		public InvalidDataException(String message) {
			super(HttpStatus.BAD_REQUEST.value(), message);
		}
	}

}