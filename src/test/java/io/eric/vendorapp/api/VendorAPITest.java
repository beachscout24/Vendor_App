package io.eric.vendorapp.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.eric.vendorapp.configuration.RestTemplateConfiguration;
import io.eric.vendorapp.model.Vendor;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {RestTemplateConfiguration.class, VendorAPI.class})
class VendorAPITest {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	VendorAPI vendorAPI;
	
	private MockRestServiceServer mockRestServiceServer;
	
	private Vendor vendor;
	
	private ObjectMapper objectMapper;
	private String strVendor, strVendors;
	private List<Vendor> vendors;
	@BeforeEach
	void setUp() throws JsonProcessingException{
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
		mockRestServiceServer = MockRestServiceServer.createServer(restTemplate);
	}
	
	@Test
	void getVendors() throws URISyntaxException {
		mockRestServiceServer.expect(ExpectedCount.once(), requestTo(new URI("http://localhost:8081/vendors?page=0&size=3")))
				.andExpect(method(HttpMethod.GET))
				.andRespond(withStatus(HttpStatus.OK)
						.contentType(MediaType.APPLICATION_JSON)
						.body(strVendors));
		
		String vendorsString = vendorAPI.getVendors(0,3).getBody();
		mockRestServiceServer.verify();
		assertEquals(strVendors, vendorsString);
	}
	
	@Test
	void findVendorById() throws URISyntaxException {
		mockRestServiceServer.expect(ExpectedCount.once(), requestTo(new URI("http://localhost:8081/vendors/getById?id=1234")))
				.andExpect(method(HttpMethod.GET))
				.andRespond(withStatus(HttpStatus.OK)
						.contentType(MediaType.APPLICATION_JSON)
						.body(strVendor));
		
		String vendorString = vendorAPI.findVendorById(vendor.getId()).getBody();
		mockRestServiceServer.verify();
		assertEquals(strVendor, vendorString);
	}
	
	@Test
	void deleteVendor() throws URISyntaxException {
		mockRestServiceServer.expect(ExpectedCount.once(), requestTo(new URI("http://localhost:8081/vendors/1234")))
				.andExpect(method(HttpMethod.DELETE))
				.andRespond(withStatus(HttpStatus.OK)
						.contentType(MediaType.APPLICATION_JSON)
						.body(vendor.getId()));
		
		vendorAPI.deleteVendor(vendor.getId());
		mockRestServiceServer.verify();
	}
}