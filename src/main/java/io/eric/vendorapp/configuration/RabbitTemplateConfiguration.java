package io.eric.vendorapp.configuration;

import io.eric.vendorapp.constants.RabbitMQConstants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitTemplateConfiguration {
	
	@Bean
	public Queue queue(){
		return new Queue(RabbitMQConstants.QUEUE_NAME, false);
	}
	
	@Bean
	public DirectExchange directExchange(){
		return new DirectExchange(RabbitMQConstants.EXCHANGE_NAME);
	}
	
	@Bean
	public Binding bind(Queue queue, DirectExchange directExchange){
		return BindingBuilder
				.bind(queue)
				.to(directExchange)
				.with(RabbitMQConstants.ROUTING_KEY);
	}
	
	@Bean
	public MessageConverter messageConverter(){
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(messageConverter());
		return rabbitTemplate;
	}
}
