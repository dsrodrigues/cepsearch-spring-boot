package me.dsrodrigues.cepsearch.domain;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import me.dsrodrigues.cepsearch.exception.InvalidZipCodeException;

import org.junit.Test;

public class ZipCodeTest {

	@Test
	public void validZipCode() {
		ZipCode zipCode = new ZipCode("04675010");
		assertThat(zipCode.getValue(), is("04675010"));
	}

	@Test(expected = InvalidZipCodeException.class)
	public void invalidZipCodeException() {
		new ZipCode("1");
	}

	@Test(expected = InvalidZipCodeException.class)
	public void invalidZipCodeExceptionValueIsNull() {
		new ZipCode(null);
	}

	@Test
	public void nextValidZipCodeReplaceLastThreeDigits() {
		ZipCode zipCode = new ZipCode("12345678");
		assertThat(zipCode.next().getValue(), is("12345670"));
		assertThat(zipCode.next().next().getValue(), is("12345600"));
		assertThat(zipCode.next().next().next().getValue(), is("12345000"));
	}

	@Test(expected = InvalidZipCodeException.class)
	public void noMoreThreeDigitsZero() {
		new ZipCode("12345000").next();
	}
	
	@Test
	public void validHasNextZipCode() {
		assertThat(new ZipCode("12345100").hasNext(), is(true));
	}
	
	@Test
	public void invalidHasNextZipCode() {
		assertThat(new ZipCode("12345000").hasNext(), is(false));
	}
}
