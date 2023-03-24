package io.eric.vendorapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.eric.vendorapp.model.Vendor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.ui.ConcurrentModel;
import org.springframework.validation.DirectFieldBindingResult;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(VendorController.class)
class VendorControllerTest {
	
	@MockBean
	private VendorController mockController;
	
	private Vendor vendor;
	
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
	void getHomePage() throws JsonProcessingException {
		Mockito.when(mockController.getHomePage(new ConcurrentModel(), 0, 3)).thenReturn("index");
		String page = mockController.getHomePage(new ConcurrentModel(), 0, 3);
		assertEquals(page, "index");
	}
	
	@Test
	void getAboutPage() {
		Mockito.when(mockController.getAboutPage()).thenReturn("about");
		String page = mockController.getAboutPage();
		assertEquals(page,"about");
	}
	
	@Test
	void getAddVendorPage() {
		Mockito.when(mockController.getAddVendorPage()).thenReturn("addVendor");
		String page = mockController.getAddVendorPage();
		assertEquals(page, "addVendor");
	}
	
	@Test
	void postVendor() {
		Mockito.when(mockController.postVendor(new ConcurrentModel(), vendor, new DirectFieldBindingResult(null, "error"))).thenReturn("redirect:/");
		String page = mockController.postVendor(new ConcurrentModel(), vendor, new DirectFieldBindingResult(null, "error"));
		assertEquals(page,"redirect:/");
	}
	
	@Test
	void getEditVendorPage() throws JsonProcessingException {
		Mockito.when(mockController.getEditVendorPage(new ConcurrentModel(), "1234")).thenReturn("editVendor");
		String page = mockController.getEditVendorPage(new ConcurrentModel(), "1234");
		assertEquals(page, "editVendor");
	}
	
	@Test
	void deleteVendor() {
		Mockito.when(mockController.deleteVendor("1234")).thenReturn("redirect:/");
		String page = mockController.deleteVendor("1234");
		assertEquals(page, "redirect:/");
	}
}