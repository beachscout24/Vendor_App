package io.eric.vendorapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor @ToString
public class Sort {
	
	private Boolean empty;
	private Boolean unsorted;
	private Boolean sorted;
}
