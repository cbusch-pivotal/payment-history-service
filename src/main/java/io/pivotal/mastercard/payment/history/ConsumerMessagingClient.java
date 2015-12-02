/**
 * 
 */
package io.pivotal.mastercard.payment.history;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import io.pivotal.mastercard.payment.consumermessaging.client.ConsumerMessageRequest;
import io.pivotal.mastercard.payment.consumermessaging.client.ConsumerMessageRequest.ContactInfo;
import io.pivotal.mastercard.payment.consumermessaging.client.ConsumerMessageResponse;
import io.pivotal.mastercard.payment.consumermessaging.client.DeviceNotificationRequest;
import io.pivotal.mastercard.payment.consumermessaging.client.DeviceNotificationResponse;

/**
 * @author brian
 *
 */
public class ConsumerMessagingClient extends WebServiceGatewaySupport {
	
	public ConsumerMessageResponse getConsumerMessageResponse() {
		
		ConsumerMessageRequest request = new ConsumerMessageRequest();
		ContactInfo contactInfo = new ContactInfo();
		contactInfo.setEmail("bjimerson@pivotal.io");
		request.setContactInfo(contactInfo);
		//Set parameters on request if necessary
		
		ConsumerMessageResponse response = (ConsumerMessageResponse)
				this.getWebServiceTemplate().marshalSendAndReceive(
						"http://consumer-messaging-service-dev.pcf1.fe.gopivotal.com/MessagingService",
						request);
						
		return response;
		
		
	}
	
	public DeviceNotificationResponse getDeviceNotificationResponse() {
		
		DeviceNotificationRequest request = new DeviceNotificationRequest();
		request.setApplicationId("APP-01");
		request.setDeviceToken("253ae4f9362a645b340f5d8d23f0db9659a3e9e470f5b18e4c506cff08cf0b3a");
		
		DeviceNotificationResponse response = (DeviceNotificationResponse) 
				this.getWebServiceTemplate().marshalSendAndReceive(
						"http://consumer-messaging-service-dev.pcf1.fe.gopivotal.com/MessagingService",
						request);
		
		return response;
		
	}

}
