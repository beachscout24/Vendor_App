package io.eric.vendorapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor
@ToString
public class ResponseObject {
	List<Vendor> content;
	
	Pageable pageable;
	
	private Boolean last;
	private Integer totalElements;
	private Integer totalPages;
	private Integer size;
	private Integer number;
	
	private Sort sort;
	
	private Boolean first;
	private  Integer numberOfElements;
	private  Boolean empty;
}
