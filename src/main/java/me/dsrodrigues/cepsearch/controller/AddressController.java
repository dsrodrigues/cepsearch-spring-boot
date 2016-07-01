package me.dsrodrigues.cepsearch.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import javax.inject.Inject;
import javax.validation.Valid;

import me.dsrodrigues.cepsearch.domain.Address;
import me.dsrodrigues.cepsearch.domain.ZipCode;
import me.dsrodrigues.cepsearch.service.AddressService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
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

	@RequestMapping(method = GET, produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Address findByZipCode(@RequestParam("zipcode") String zipCode) {
		LOGGER.debug("Received request to find the {}", zipCode);
		return addressService.findByZipCode(new ZipCode(zipCode));
	}

	@RequestMapping(method = POST, produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@Valid @RequestBody Address address) {
		LOGGER.debug("Received request to create the {}", address);
		addressService.create(address);
	}

	@RequestMapping(method = GET, produces = APPLICATION_JSON_VALUE, value = "{id}")
	public Address show(@PathVariable Long id) {
		return addressService.find(id);
	}

	@RequestMapping(method = PUT, consumes = APPLICATION_JSON_VALUE, value = "{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public void update(@PathVariable Long id, @Valid @RequestBody Address address) {
		addressService.update(id, address);
	}

	@RequestMapping(method = DELETE, value = "{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		addressService.delete(id);
	}
}
