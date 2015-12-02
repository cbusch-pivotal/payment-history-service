package io.pivotal.mastercard.payment.history.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.pivotal.mastercard.payment.history.entity.Payment;
import io.pivotal.mastercard.payment.history.repository.PaymentRepository;

/**
 * REST API controller for payments.
 * @author Brian Jimerson
 *
 */
@RestController
public class PaymentHistoryController {
	
	@Autowired
	PaymentRepository paymentRepository;
	
		
	private static final Log log = LogFactory.getLog(PaymentHistoryController.class);
	
	/**
	 * Gets all payment history recorded
	 * @return A list of all payments
	 */
	@RequestMapping(value="/payments/", method=RequestMethod.GET)
	public List<Payment> getPaymentHistory() {
		
		List<Payment> paymentList = new ArrayList<Payment>();
		for (Payment p : paymentRepository.findAll()) {
			paymentList.add(p);
		}
		log.debug(String.format("All payments found = %s", paymentList));
		return paymentList;
	}

	/**
	 * Action to initiate shutdown of the system.  In CF, the application 
	 * <em>should</em>f restart.  In other environments, the application
	 * runtime will be shut down.
	 */
	@RequestMapping(value = "/kill", method = RequestMethod.GET)
	public void kill() {
		
		log.warn("*** The Payment History app instance is shutting down. ***");
		System.exit(-1);		
	}

}
