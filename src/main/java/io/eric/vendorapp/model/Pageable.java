package io.eric.vendorapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor @ToString
public class Pageable {
	
	private Sort sort;
	private Integer offset;
	private Integer pageSize;
	private Integer pageNumber;
	private  Boolean paged;
	private Boolean unpaged;
	
}
