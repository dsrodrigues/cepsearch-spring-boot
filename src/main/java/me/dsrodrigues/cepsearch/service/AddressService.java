package me.dsrodrigues.cepsearch.service;

import me.dsrodrigues.cepsearch.domain.Address;

public interface AddressService {

	Address save(Address address);
	
	Address findByZipCode(String zipCode);
}
