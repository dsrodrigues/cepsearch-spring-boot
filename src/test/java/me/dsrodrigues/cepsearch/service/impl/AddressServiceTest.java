package me.dsrodrigues.cepsearch.service.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import me.dsrodrigues.cepsearch.domain.Address;
import me.dsrodrigues.cepsearch.domain.ZipCode;
import me.dsrodrigues.cepsearch.exception.NotFoundZipCodeException;
import me.dsrodrigues.cepsearch.repository.AddressRepository;
import me.dsrodrigues.cepsearch.service.AddressService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AddressServiceTest {

	@Mock
	private AddressRepository addressRepository;
	private AddressService addressService;

	@Before
	public void setUp() {
		addressService = new AddressServiceImpl(addressRepository);
	}

	@Test
	public void searchAddressByZipCode() {
		ZipCode zipCode = new ZipCode("04675010");
		Address address = new Address();

		when(addressRepository.findByZipCode(zipCode)).thenReturn(address);

		assertThat(addressService.findByZipCode(zipCode), is(address));
	}

	@Test(expected = NotFoundZipCodeException.class)
	public void notFoundZipCode() {
		ZipCode zipCode = new ZipCode("04675010");

		when(addressRepository.findByZipCode(zipCode)).thenReturn(null);

		addressService.findByZipCode(zipCode);
	}

	@Test
	public void changeDigitsZipCode() {
		ZipCode zipCode = new ZipCode("12345678");
		Address address = new Address();

		when(addressRepository.findByZipCode(new ZipCode("12345678"))).thenReturn(null);
		when(addressRepository.findByZipCode(new ZipCode("12345670"))).thenReturn(null);
		when(addressRepository.findByZipCode(new ZipCode("12345600"))).thenReturn(null);
		when(addressRepository.findByZipCode(new ZipCode("12345000"))).thenReturn(address);

		assertThat(addressService.findByZipCode(zipCode), is(address));
	}
}
