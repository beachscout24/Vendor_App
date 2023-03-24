package io.eric.vendorapp.services;

import ch.qos.logback.core.testUtil.MockInitialContext;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.eric.vendorapp.api.VendorAPI;
import io.eric.vendorapp.api.VendorProducer;
import io.eric.vendorapp.model.Vendor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class VendorServiceTest {
	
	@Mock
	private VendorAPI vendorAPI;
	
	@Mock
	private VendorProducer vendorProducer;
	
	@InjectMocks
	private VendorService vendorService;
	
	private Vendor vendor;
	
	private List<Vendor> vendors;
	
	private ObjectMapper objectMapper;
	
	private String strVendor, strVendors;
	
	@BeforeEach
	void setUp() throws JsonProcessingException {
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
		
		objectMapper = new ObjectMapper();
		strVendor = objectMapper.writeValueAsString(vendor);
		
		vendors = new ArrayList<>();
		vendors.add(vendor);
		strVendors = objectMapper.writeValueAsString(vendors);
	}
	
	@Test
	void getVendors() {
		Mockito.when(vendorService.getVendors(0,3)).thenReturn(ResponseEntity.ok(strVendors));
		ResponseEntity<String> vendorsString = vendorService.getVendors(0,3);
		assertEquals(strVendors, vendorsString.getBody());
	}
	
	@Test
	void postVendor() {
		Mockito.when(vendorService.postVendor(vendor)).thenReturn(strVendor);
		String vendorString = vendorService.postVendor(vendor);
		assertEquals(strVendor, vendorString);
	}
	
	@Test
	void findVendorById() {
		Mockito.when(vendorService.findVendorById(vendor.getId())).thenReturn(ResponseEntity.ok(strVendor));
		ResponseEntity<String> vendorString = vendorService.findVendorById(vendor.getId());
		assertEquals(strVendor, vendorString.getBody());
	}
	
	@Test
	void deleteVendor() {
		vendorService.deleteVendor(vendor.getId());
		Mockito.verify(vendorAPI).deleteVendor(vendor.getId());
	}
}