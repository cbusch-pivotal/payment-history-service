package io.pivotal.mastercard.payment.history;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;


/**
 * The main entry point for the Spring Boot application
 * 
 * @author Brian Jimerson
 *
 */
@ComponentScan
@EnableAutoConfiguration
@EnableDiscoveryClient
public class PaymentHistoryServiceApplication {
	
	
	/**
	 * Gets a queue to work with.
	 * @return A queue to work with.
	 */
	@Bean
	Queue queue() {
		return new Queue("payment-queue", true);
	}
	
	/**
	 * Gets an exchange to work with.
	 * @return An exchange to work with.
	 */
	@Bean
	TopicExchange exchange() {
		return new TopicExchange("payment-exchange");
	}
	
	/**
	 * Gets a queue / exchange binding.
	 * @param queue The queue to bind.
	 * @param exchange The exchange to bind to.
	 * @return A queue/exchange binding.
	 */
	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("payment-queue");
	}
	
	/**
	 * Gets a new message receiver.
	 * @return A new message receiver.
	 */
	@Bean
	MessageReceiver messageReceiver() {
		return new MessageReceiver();
	}
	
	/**
	 * Gets a message listener adapter
	 * @param messageReceiver The receiver delegate to use
	 * @return A message listener adapter
	 */
	@Bean
	MessageListenerAdapter listenerAdapter(MessageReceiver messageReceiver) {
		return new MessageListenerAdapter(messageReceiver);
	}
	
	/**
	 * Gets a new message listener container
	 * @param connectionFactory The connection factory to use
	 * @param listenerAdapter The listener adapter to use
	 * @return A configured message listener container
	 */
	@Bean
	SimpleMessageListenerContainer container(
			ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setMessageListener(listenerAdapter);
		container.setQueueNames("payment-queue");
		return container;
	}
	
	/**
	 * The main entry point method.
	 * @param args Any command line arguments passed.
	 */
	public static void main(String[] args) {
		SpringApplication.run(PaymentHistoryServiceApplication.class, args);
	}

}
