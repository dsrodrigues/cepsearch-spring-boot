package me.dsrodrigues.cepsearch.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.inject.Inject;
import javax.validation.Valid;

import me.dsrodrigues.cepsearch.domain.Address;
import me.dsrodrigues.cepsearch.service.AddressService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
public class AddressController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AddressController.class);

	private final AddressService addressService;

	@Inject
	public AddressController(final AddressService addressService) {
		this.addressService = addressService;
	}

	@RequestMapping(method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public Address createAddress(@RequestBody @Valid final Address address) {
		LOGGER.debug("Received request to create the {}", address);
		return addressService.save(address);
	}

	@RequestMapping(method = GET, produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Address findByZipCode(@RequestParam("zipcode") String zipCode) {
		LOGGER.debug("Received request to find the {}", zipCode);
		return addressService.findByZipCode(zipCode);
	}

}
