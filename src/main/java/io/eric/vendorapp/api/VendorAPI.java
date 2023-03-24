package io.eric.vendorapp.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class VendorAPI {
	
	@Autowired
	
	RestTemplate restTemplate;
	
	@Value("${get.url}")
	private String getUrl;
	
	@Value("${get.by.id.url}")
	private String getByIdUrl;
	
	@Value("${delete.url}")
	private String deleteUrl;
	
	public ResponseEntity<String> getVendors(Integer page, Integer size) {
		ResponseEntity<String> vendors = restTemplate.getForEntity(getUrl, String.class, page, size);
		return vendors;
	}
	
	public ResponseEntity<String> findVendorById(String id) {
		ResponseEntity<String> vendor = restTemplate.getForEntity(getByIdUrl, String.class, id);
		return vendor;
	}
	
	public void deleteVendor(String id) {
		restTemplate.delete(deleteUrl, id);
		log.info("Deleted Vendor with ID: {}", id);
	}
}
