package io.eric.vendorapp.services;

import io.eric.vendorapp.model.Vendor;
import org.springframework.http.ResponseEntity;

public interface IVendorService {
	ResponseEntity<String> getVendors(Integer page, Integer size);
	
	String postVendor(Vendor vendor);
	
	ResponseEntity<String> findVendorById(String id);
	
	void deleteVendor(String id);
}
