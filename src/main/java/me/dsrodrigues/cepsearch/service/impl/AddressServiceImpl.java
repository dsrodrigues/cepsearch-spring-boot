package me.dsrodrigues.cepsearch.service.impl;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import me.dsrodrigues.cepsearch.domain.Address;
import me.dsrodrigues.cepsearch.exception.InvalidZipCodeException;
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
	@Transactional
	public Address save(@NotNull @Valid final Address address) {
		LOGGER.debug("Creating {}", address);
		return repository.save(address);
	}

	@Override
	@Transactional(readOnly = true)
	public Address findByZipCode(@NotNull String zipCode) {
		LOGGER.debug("findByZipCode {}", zipCode);
		Address address = repository.findByZipCode(zipCode);
		for (StringBuilder zip = new StringBuilder(zipCode); address == null && !isZipCodeSufixZero(zip); fillZeroRight(zip)) {
			address = repository.findByZipCode(zip.toString());
		}
		if (address == null)
			throw new InvalidZipCodeException("Invalid Zip Code");
		return address;
	}

	private StringBuilder fillZeroRight(StringBuilder zip) {
		for (int i = zip.length(); i >= 0; i--) {
			if (zip.charAt(i - 1) != '0') {
				zip.replace(i - 1, i, "0");
				break;
			}
		}
		return zip;
	}

	private boolean isZipCodeSufixZero(StringBuilder zip) {
		return zip.toString().substring(5).matches("[0]+");
	}

}
