package io.pivotal.mastercard.payment.history;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.pivotal.mastercard.payment.consumermessaging.client.DeviceNotificationResponse;
import io.pivotal.mastercard.payment.history.entity.Payment;
import io.pivotal.mastercard.payment.history.repository.PaymentRepository;

/**
 * Simple receiver for AMQP messages.
 * @author Brian Jimerson
 *
 */
public class MessageReceiver {

	@Autowired
	PaymentRepository paymentRepository;
	
	@Autowired
	ConsumerMessagingClient consumerMessagingClient;
	
	private static final Log log = LogFactory.getLog(MessageReceiver.class);

	public void handleMessage(String message) {
		log.debug(String.format("Message received = [%s].", message));
		ObjectMapper objectMapper = new ObjectMapper();
		Payment payment = null;
		try {
			payment = objectMapper.readValue(message, Payment.class);
		} catch (IOException e) {
			log.error(String.format("Error deserializing message [%s] into Payment.", message), e);
			return;
		}
		paymentRepository.save(payment);
		log.debug(String.format("Saved payment message received: %s", payment));
		
		DeviceNotificationResponse response = consumerMessagingClient.getDeviceNotificationResponse();
		log.debug(String.format("Received device notification response [%s]", response.getNotifyStatus()));
	}
}
