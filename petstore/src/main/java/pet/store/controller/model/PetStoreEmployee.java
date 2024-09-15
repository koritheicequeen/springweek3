package pet.store.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import pet.store.entity.customer;
import pet.store.entity.employee;

@Data
@NoArgsConstructor
public class PetStoreEmployee {

	  
	private int employeeId;           
	          
	    private String employeeFirstName; 
	    private String employeeLastName;  
	    private String employeePhone;  
	    private String employeeJobTitle;  
public PetStoreEmployee(employee employee) {
	
		employeeId = employee.getEmployeeId();
		employeeFirstName = employee.getEmployeeFirstName();
		employeeLastName = employee.getEmployeeLastName();
		employeeJobTitle = employee.getEmployeeJobTitle();
		employeePhone = employee.getEmployeePhone();
		}
	}
