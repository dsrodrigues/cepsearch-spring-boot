package me.dsrodrigues.cepsearch.integrations;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import me.dsrodrigues.cepsearch.Application;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class ZipCodeIntegrationTest {

	@Autowired
	private WebApplicationContext context;
	private MockMvc mock;

	@Before
	public void setUp() {
		this.mock = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void returnValidAddress() throws Exception {
		mock.perform(get("/address/?zipcode=04675010")).andExpect(status().isOk()).andExpect(jsonPath("$.zipCode", is("04675010")));
	}

	@Test
	public void invalidZipCode() throws Exception {
		mock.perform(get("/address/?zipcode=04675")).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errors[0].message", is("Invalid Zip Code")));
	}

	@Test
	public void notFoundZipCode() throws Exception {
		mock.perform(get("/address/?zipcode=98765432")).andExpect(status().isUnprocessableEntity())
				.andExpect(jsonPath("$.errors[0].message", is("Not Found Zip Code")));
	}

	@Test
	public void returnValidAddressWithNextZipCode() throws Exception {
		mock.perform(get("/address/?zipcode=22345678")).andExpect(status().isOk()).andExpect(jsonPath("$.zipCode", is("22345600")));
	}
}
