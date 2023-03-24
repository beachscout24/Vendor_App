package io.eric.vendorapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.eric.vendorapp.model.ResponseObject;
import io.eric.vendorapp.model.Vendor;
import io.eric.vendorapp.services.IVendorService;
import io.eric.vendorapp.utilities.Utility;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Map;

@Controller
@Slf4j
public class VendorController {
	
	@Autowired
	IVendorService vendorService;
	
	@Value("${page.size}")
	private Integer pageSize;
	
	@Value("${page.page}")
	private Integer pageNumber;
	
	@GetMapping("/")
	public String getHomePage(Model model, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size) throws JsonProcessingException {
		page = page == null ? pageNumber : page;
		size = size == null ? pageSize : size;
		ResponseEntity<String> vendors = vendorService.getVendors(page, size);
		ResponseObject responseObject = Utility.convertToObject(vendors.getBody());
		model.addAttribute("vendors", responseObject.getContent());
		model.addAttribute("pages", Utility.getTotalPages(responseObject.getTotalPages()));
		model.addAttribute("pageNumber", page);
		model.addAttribute("size", responseObject.getSize());
		return "index";
	}
	
	@GetMapping("/about")
	public String getAboutPage(){
		return "about";
	}
	
	@GetMapping("/add-a-vendor")
	public String getAddVendorPage(){
		return "addVendor";
	}
	
	@PostMapping("/postVendor")
	public String postVendor(Model model, @Valid @ModelAttribute Vendor vendor, BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			Map<String, String> errors = Utility.mapErrors(bindingResult);
			log.info("errors => {}", errors);
			model.addAttribute("errors", errors);
			if(vendor.getId() != null){
				model.addAttribute("vendor", vendor);
				return "editVendor";
			}
			else{
				return "addVendor";
			}
		}
		else{
			String message = vendorService.postVendor(vendor);
			log.info("Posting vendor was a {}", message);
			return "redirect:/";
		}
	}
	
	@GetMapping("/editVendor")
	public String getEditVendorPage(Model model, @RequestParam String id) throws JsonProcessingException {
		log.info("id:{}", id);
		ResponseEntity<String> vendor = vendorService.findVendorById(id);
		Vendor vendorObj = Utility.convertJsonToVendor(vendor.getBody());
		log.info("Vendor:{}", vendorObj);
		model.addAttribute("vendor", vendorObj);
		return "editVendor";
	}
	
	@GetMapping("/deleteVendor")
	public String deleteVendor(@RequestParam String id){
		log.info("id:{}", id);
		vendorService.deleteVendor(id);
		log.info("deleted response for ID: {}", id);
		return "redirect:/";
	}
}
