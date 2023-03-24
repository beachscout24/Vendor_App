package io.eric.vendorapp.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.eric.vendorapp.model.ResponseObject;
import io.eric.vendorapp.model.Vendor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utility {
	
	public static ResponseObject convertToObject(String json) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, ResponseObject.class);
	}
	
	public static Integer[] getTotalPages(Integer totalPages) {
		Integer[] intArray = new Integer[totalPages];
		for(int x = 0; x < totalPages; x++){
			intArray[x] = x;
		}
		return intArray;
	}
	
	public static Map<String, String> mapErrors(BindingResult bindingResult) {
		List<FieldError> fieldErrors = bindingResult.getFieldErrors();
		Map<String, String> errors = new HashMap<String, String>();
		for(FieldError fieldError : fieldErrors){
			errors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		return errors;
	}
	
	public static Vendor convertJsonToVendor(String json) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, Vendor.class);
	}
}
