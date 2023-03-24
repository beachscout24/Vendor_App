package io.eric.vendorapp;

import io.eric.vendorapp.controller.VendorController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class VendorAppApplicationTests {

	@Autowired
	VendorController vendorController;
	
	@Test
	void contextLoads() {
		assertThat(vendorController).isNotNull();
	}

}
