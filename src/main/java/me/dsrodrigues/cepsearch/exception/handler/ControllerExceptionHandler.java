package me.dsrodrigues.cepsearch.exception.handler;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import me.dsrodrigues.cepsearch.exception.InvalidZipCodeException;
import me.dsrodrigues.cepsearch.exception.NotFoundZipCodeException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import com.fasterxml.jackson.databind.ObjectMapper;

@ControllerAdvice
public class ControllerExceptionHandler extends DefaultHandlerExceptionResolver {
	private static final Logger log = LoggerFactory.getLogger(ControllerExceptionHandler.class);

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidZipCodeException.class)
	void handleInvalidZipCodeException(InvalidZipCodeException e, HttpServletResponse response) throws IOException {
		response.setStatus(HttpStatus.BAD_REQUEST.value());
		Errors errors = Errors.valueOf(response.getStatus(), e.getMessage());
		writeErrorResponse(errors, response);
	}

	@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
	@ExceptionHandler(NotFoundZipCodeException.class)
	void handleNotFoundZipCodeException(NotFoundZipCodeException e, HttpServletResponse response) throws IOException {
		response.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
		Errors errors = Errors.valueOf(response.getStatus(), e.getMessage());
		writeErrorResponse(errors, response);
	}

	private void writeErrorResponse(Errors errors, HttpServletResponse response) {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		try {
			String json = new ObjectMapper().writeValueAsString(errors);
			response.getWriter().write(json);
		} catch (Exception ex) {
			log.error("cannot possible to create json content.", ex);
		}
	}

	@AllArgsConstructor
	@RequiredArgsConstructor
	public static class Errors implements Serializable {

		private static final long serialVersionUID = -5847644332155869298L;

		@Getter
		@NonNull
		private Integer statusCode;
		@Getter
		private List<Error> errors = new ArrayList<Error>();

		public void add(Error error) {
			this.errors.add(error);
		}

		public static Errors valueOf(Integer statusCode, String message) {
			Errors errors = new Errors(statusCode);
			errors.add(new Error(message));
			return errors;
		}
	}

	@AllArgsConstructor
	public static class Error implements Serializable {

		private static final long serialVersionUID = 4569677584586056456L;

		@Getter
		private final String message;
	}
}
