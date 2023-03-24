package io.eric.vendorapp.api;

import io.eric.vendorapp.model.Vendor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class VendorProducerTest {
	
	private Vendor vendor;
	
	@Mock
	private RabbitTemplate rabbitTemplate;
	
	@Mock
	private DirectExchange directExchange;
	
	@InjectMocks
	private VendorProducer vendorProducer;
	
	@BeforeEach
	void setUp() {
		vendor = Vendor.builder()
				.id("1234")
				.name("Test Test")
				.address("123 Test Lane")
				.city("Test")
				.state("TS")
				.zipCode("12345")
				.phoneNumber("111-111-1111")
				.email("test@test.com")
				.build();
	}
	
	@Test
	void sendVendorToExchange() {
		String success = vendorProducer.sendVendorToExchange(vendor);
		assertEquals(success, "success");
	}
}