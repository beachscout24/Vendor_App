package io.eric.vendorapp.services;

import io.eric.vendorapp.api.VendorAPI;
import io.eric.vendorapp.api.VendorProducer;
import io.eric.vendorapp.model.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class VendorService implements IVendorService{
	
	@Autowired
	VendorAPI vendorAPI;
	
	@Autowired
	VendorProducer vendorProducer;
	
	@Override
	public ResponseEntity<String> getVendors(Integer page, Integer size) {
		return vendorAPI.getVendors(page, size);
	}
	
	@Override
	public String postVendor(Vendor vendor) {
		return vendorProducer.sendVendorToExchange(vendor);
	}
	
	@Override
	public ResponseEntity<String> findVendorById(String id) {
		return vendorAPI.findVendorById(id);
	}
	
	@Override
	public void deleteVendor(String id) {
		vendorAPI.deleteVendor(id);
	}
}
