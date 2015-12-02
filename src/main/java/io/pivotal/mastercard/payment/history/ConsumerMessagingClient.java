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
		
/*		<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ser="http://service.consumerMessaging.mastercard.com">
		   <soapenv:Header/>
		   <soapenv:Body>
		      <ser:DeviceNotificationRequest>
		         <ApplicationId>APP-01</ApplicationId>
		         <DeviceToken>253ae4f9362a645b340f5d8d23f0db9659a3e9e470f5b18e4c506cff08cf0b3a</DeviceToken>
		         <NotificationText>test message</NotificationText>
		         <Apple>Y</Apple>
		         <Google>N</Google>
		         <CorrelationId/>
		      </ser:DeviceNotificationRequest>
		   </soapenv:Body>
		</soapenv:Envelope>
*/
		      
		// test load - update with method parameters to make it dynamic
		request.setApplicationId("APP-01");
		request.setDeviceToken("253ae4f9362a645b340f5d8d23f0db9659a3e9e470f5b18e4c506cff08cf0b3a");
		request.setApple("Y");
		request.setGoogle("N");
		
		DeviceNotificationResponse response = (DeviceNotificationResponse) 
				this.getWebServiceTemplate().marshalSendAndReceive(
						"http://consumer-messaging-service-dev.pcf1.fe.gopivotal.com/MessagingService",
						request);
		
		return response;
	}
}
