package me.dsrodrigues.cepsearch.repository.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import me.dsrodrigues.cepsearch.domain.ZipCode;

@Converter
public class ZipCodeConverter implements AttributeConverter<ZipCode, String> {

	@Override
	public String convertToDatabaseColumn(ZipCode zipCode) {
		return zipCode.getValue();
	}

	@Override
	public ZipCode convertToEntityAttribute(String value) {
		return new ZipCode(value);
	}

}
