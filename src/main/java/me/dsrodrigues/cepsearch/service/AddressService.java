package me.dsrodrigues.cepsearch.service;

import me.dsrodrigues.cepsearch.domain.Address;
import me.dsrodrigues.cepsearch.domain.ZipCode;

public interface AddressService {
	Address findByZipCode(ZipCode zipCode);
}
