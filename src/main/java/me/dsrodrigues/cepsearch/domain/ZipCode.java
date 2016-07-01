package me.dsrodrigues.cepsearch.domain;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import me.dsrodrigues.cepsearch.exception.InvalidZipCodeException;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonValue;

@EqualsAndHashCode
public class ZipCode implements Serializable {

	private static final long serialVersionUID = -2123937916089068382L;

	private static final String VALID_ZIP = "\\d{8}";

	private final String value;

	@JsonValue
	public String getValue() {
		return value;
	}

	public ZipCode(final String value) {
		if (StringUtils.isEmpty(value) || !value.matches(VALID_ZIP))
			throw new InvalidZipCodeException("Invalid Zip Code");
		this.value = value;
	}

	public ZipCode next() {
		if (this.hasNext())
			return new ZipCode(this.value.replaceAll("[1-9](?!.*[1-9])", "0"));
		throw new InvalidZipCodeException("Invalid Zip Code");
	}

	public boolean hasNext() {
		return !this.value.substring(5).matches("[0]+");
	}
}
