package io.eric.vendorapp.model;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor @NoArgsConstructor
@ToString @Builder
@Component
public class Vendor {
	
	private String id;
	
	@Length(min=3, message="Name must have 3 characters or more")
	private String name;
	
	@Length(min=3, message="Address must have 3 characters or more")
	private String address;
	
	@Length(min=3, message="City must have 3 characters or more")
	private String city;
	
	@Length(min=2, max=2, message="State must have 3 characters or more")
	private String state;
	
	@Pattern(message="Zipcode is zip5 or zip9", regexp="^[0-9]{5}(?:-[0-9]{4})?$")
	private String zipCode;
	
	@Pattern(message="Invalid Phone Number: format (555) 555-1212", regexp="^((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$")
	private  String phoneNumber;
	
	@Email(message = "Invalid Email", regexp="^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")
	private String email;
}
