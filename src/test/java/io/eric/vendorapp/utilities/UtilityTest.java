package io.eric.vendorapp.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.eric.vendorapp.model.ResponseObject;
import io.eric.vendorapp.model.Vendor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DirectFieldBindingResult;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UtilityTest {
	
	private String vendorJson, pageJson;
	
	private BindingResult bindingResult;
	
	@BeforeEach
	void setUp() {
		pageJson = """
				{
				    "content": [
				        {
				            "name": "Test Test",
				            "address": "123 Test Lane",
				            "city": "Test",
				            "state": "TS",
				            "zipCode": "12345",
				            "email": "test@test.com",
				            "phoneNumber": "111-111-1111"
				        }
				    ],
				    "pageable": {
				        "sort": {
				            "empty": true,
				            "unsorted": true,
				            "sorted": false
				        },
				        "offset": 0,
				        "pageNumber": 0,
				        "pageSize": 1,
				        "paged": true,
				        "unpaged": false
				    },
				    "totalPages": 6,
				    "totalElements": 6,
				    "last": false,
				    "size": 1,
				    "number": 0,
				    "sort": {
				        "empty": true,
				        "unsorted": true,
				        "sorted": false
				    },
				    "numberOfElements": 1,
				    "first": true,
				    "empty": false
				}
				""";
		
		vendorJson= """
    				{
				        "name": "Test Test",
				        "address": "123 Test Lane",
				        "city": "Test",
				        "state": "TS",
				        "zipCode": "12345",
				        "email": "test@test.com",
				         "phoneNumber": "111-111-1111"
				     }
				""";
		
		bindingResult = new DirectFieldBindingResult(null, "errors");
	}
	
	@Test
	void convertToObject() throws JsonProcessingException {
		ResponseObject object = Utility.convertToObject(pageJson);
		assertEquals(object.getContent().get(0).getName(), "Test Test");
		assertEquals(object.getContent().get(0).getAddress(), "123 Test Lane");
		assertEquals(object.getContent().get(0).getCity(), "Test");
		assertEquals(object.getContent().get(0).getState(), "TS");
		assertEquals(object.getContent().get(0).getZipCode(), "12345");
		assertEquals(object.getContent().get(0).getPhoneNumber(), "111-111-1111");
		assertEquals(object.getContent().get(0).getEmail(), "test@test.com");
		assertEquals(object.getSize(),1);
		assertEquals(object.getTotalPages(),6);
		assertEquals(object.getTotalElements(),6);
		assertTrue(object.getSort().getEmpty());
	}
	
	@Test
	void getTotalPages() {
		Integer[] intArray = Utility.getTotalPages(3);
		assertEquals(intArray.length, 3);
		assertEquals(intArray[0],0);
		assertEquals(intArray[1],1);
		assertEquals(intArray[2],2);
	}
	
	@Test
	void mapErrors() {
		Map<?,?> errors =  Utility.mapErrors(bindingResult);
		assertNotNull(errors);
	}
	
	@Test
	void convertJsonToVendor() throws JsonProcessingException {
		Vendor vendor = Utility.convertJsonToVendor(vendorJson);
		assertEquals(vendor.getName(), "Test Test");
		assertEquals(vendor.getAddress(), "123 Test Lane");
		assertEquals(vendor.getCity(), "Test");
		assertEquals(vendor.getState(), "TS");
		assertEquals(vendor.getZipCode(), "12345");
		assertEquals(vendor.getPhoneNumber(), "111-111-1111");
		assertEquals(vendor.getEmail(), "test@test.com");
	}
}