package pet.store.controller.model;

import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import pet.store.entity.customer;
import pet.store.entity.employee;
import pet.store.entity.petStore;
@Data
@NoArgsConstructor
public class PetStoreData {
	
		private Long petStoreId;         
		private String petStoreName;    
		private String petStoreAddress; 
		private String petStoreCity;    
		private String petStoreState;   
		private String petStoreZip;     
		private String petStorePhone;   
		private Set<PetStoreEmployee> employees;  
		private Set<PetStoreCustomer> customers;   
		public PetStoreData(petStore PetStore) {
			petStoreId = (long) PetStore.getPetStoreId();
			petStoreName = PetStore.getPetStoreName();
			petStoreAddress = PetStore.getPetStoreAddress();
			petStoreCity = PetStore.getPetStoreCity();
			petStoreState = PetStore.getPetStoreState();
			petStoreZip = PetStore.getPetStoreAddress();
			petStorePhone = PetStore.getPetStorePhone();
			for (customer customer : PetStore.getCustomers()) {
				customers.add(new PetStoreCustomer(customer));
			}
		
		for (employee employee : PetStore.getEmployees()) {
			employees.add(new PetStoreEmployee(employee));
		}
		}
		
		
}
