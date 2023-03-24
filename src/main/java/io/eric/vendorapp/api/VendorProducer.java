package io.eric.vendorapp.api;

import io.eric.vendorapp.constants.RabbitMQConstants;
import io.eric.vendorapp.model.Vendor;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VendorProducer {
	
	@Autowired
	DirectExchange directExchange;
	
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	public String sendVendorToExchange(Vendor vendor) {
		rabbitTemplate.convertAndSend(directExchange.getName(), RabbitMQConstants.ROUTING_KEY, vendor);
		return "success";
	}
}
