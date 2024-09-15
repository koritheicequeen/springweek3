package pet.store.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import pet.store.entity.customer;

@Data
@NoArgsConstructor
public class PetStoreCustomer {
	
	private int customerId;       
	    private String customerFirstName;
	    private String customerLastName;  
	    private String customerEmail;    

public PetStoreCustomer(customer customer) {
customerId = customer.getCustomerId();
customerFirstName = customer.getCustomerFirstName();
customerLastName = customer.getCustomerLastName();
customerEmail = customer.getCustomerEmail();
}
}
