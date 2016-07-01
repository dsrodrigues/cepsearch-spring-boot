package me.dsrodrigues.cepsearch.service.impl;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;

import me.dsrodrigues.cepsearch.domain.Address;
import me.dsrodrigues.cepsearch.domain.ZipCode;
import me.dsrodrigues.cepsearch.exception.NotFoundZipCodeException;
import me.dsrodrigues.cepsearch.repository.AddressRepository;
import me.dsrodrigues.cepsearch.service.AddressService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class AddressServiceImpl implements AddressService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AddressServiceImpl.class);

	private final AddressRepository repository;

	@Inject
	public AddressServiceImpl(AddressRepository repository) {
		this.repository = repository;
	}

	@Override
	@Transactional(readOnly = true)
	public Address findByZipCode(@NotNull ZipCode zipCode) {
		LOGGER.debug("findByZipCode {}", zipCode);
		Address address = repository.findByZipCode(zipCode);
		if (address == null && zipCode.hasNext())
			address = this.findByZipCode(zipCode.next());
		if (address == null)
			throw new NotFoundZipCodeException("Not Found Zip Code");
		return address;
	}
}
