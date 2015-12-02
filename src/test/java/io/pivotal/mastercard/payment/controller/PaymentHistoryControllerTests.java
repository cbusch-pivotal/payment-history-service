package io.pivotal.mastercard.payment.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedWebApplicationContext;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.pivotal.mastercard.payment.history.PaymentHistoryServiceApplication;
import io.pivotal.mastercard.payment.history.entity.Payment;
import junit.framework.TestCase;

/**
 * Test case for the PaymentController class.
 * 
 * @author Brian Jimerson
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {PaymentHistoryServiceApplication.class})
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class PaymentHistoryControllerTests {
	
	private static final Log log = LogFactory.getLog(PaymentHistoryControllerTests.class);
	
	@Autowired
	EmbeddedWebApplicationContext server;
	
	MockMvc mvc;
	
	/**
	 * Sets up the test cases
	 */
	@Before
	public void setup() {
		this.mvc = MockMvcBuilders.webAppContextSetup(server).build();
	}
	
	/**
	 * Tests the controller's get payment history method
	 */
	@Test
	public void testGetPaymentHistory() {
		
		try {
			this.mvc.perform(
					MockMvcRequestBuilders.get("/payments/")
					)
					.andDo(MockMvcResultHandlers.print())
					.andExpect(MockMvcResultMatchers.status().isOk());
		} catch (Exception e) {
			log.error(e);
			TestCase.fail(e.getMessage());
		}
	}

}
