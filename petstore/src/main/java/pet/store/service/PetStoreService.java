package pet.store.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pet.store.controller.model.PetStoreCustomer;
import pet.store.controller.model.PetStoreData;
import pet.store.controller.model.PetStoreEmployee;
import pet.store.dao.CustomerDao;
import pet.store.dao.EmployeeDao;
import pet.store.dao.PetStoreDao;
import pet.store.entity.customer;
import pet.store.entity.employee;
import pet.store.entity.petStore;

@Service
public class PetStoreService {
	@Autowired 
private EmployeeDao employeeDao;
	@Autowired
private CustomerDao customerDao;
	@Autowired
private PetStoreDao petStoreDao;
@Transactional(readOnly = false)
	public PetStoreData savePetStore(PetStoreData petStoreData) {
		petStore PetStore = findOrCreatePetStore(petStoreData.getPetStoreId());
		copyPetStoreFields(PetStore, petStoreData);
	return new PetStoreData(petStoreDao.save(PetStore));
	}

	private petStore findOrCreatePetStore(Long id) {
if (id == null) {
	return new petStore();
}
return findPetStoreById(id);
	}
	
	
	 private petStore findPetStoreById(Long petStoreId) {
	        return petStoreDao.findById(petStoreId).orElseThrow(() -> new NoSuchElementException("No pet store with that ID was found"));
	    }
	 public void copyPetStoreFields(petStore PetStore, PetStoreData petStoreData) {
	      
	        PetStore.setPetStoreName(petStoreData.getPetStoreName());
	        PetStore.setPetStoreAddress(petStoreData.getPetStoreAddress());
	        PetStore.setPetStorePhone(petStoreData.getPetStorePhone());
	        PetStore.setPetStoreCity(petStoreData.getPetStoreCity());    
			PetStore.setPetStoreState(petStoreData.getPetStoreState());
			PetStore.setPetStoreZip(petStoreData.getPetStoreZip());
}
	 private void copyEmployeeFields(employee Employee, PetStoreEmployee EmployeeData) {
	      
	        Employee.setEmployeeFirstName(EmployeeData.getEmployeeFirstName());
	        Employee.setEmployeeId(EmployeeData.getEmployeeId());
	        Employee.setEmployeePhone(EmployeeData.getEmployeePhone());
	        Employee.setEmployeeJobTitle(EmployeeData.getEmployeeJobTitle());    
			Employee.setEmployeeLastName(EmployeeData.getEmployeeLastName());
		
}
	 private void copyCustomerFields(customer Customer, PetStoreCustomer CustomerData) {
	      
	        Customer.setCustomerFirstName(CustomerData.getCustomerFirstName());
	        Customer.setCustomerId(CustomerData.getCustomerId());
	        Customer.setCustomerEmail(CustomerData.getCustomerEmail());
			Customer.setCustomerLastName(CustomerData.getCustomerLastName());
}
	private employee findOrCreateEmployee(Long petStoreId, Long employeeId) {
		if (Objects.isNull(employeeId)) {
			return new employee();
		}
		return findEmployeeById(petStoreId, employeeId);
	}
	private employee findEmployeeById(Long petStoreId, Long employeeId) {
		employee Employee = employeeDao.findById(employeeId).orElseThrow(() -> new NoSuchElementException("Employee with ID = " + employeeId + " does not exist"));
		
		if (Employee.getPetStore().getPetStoreId() != petStoreId) {
			throw new IllegalArgumentException("This employee with ID = " + employeeId + " is not employed at that store");
		}
		return Employee;
	}
	private customer findcustomerById(Long petStoreId, Long customerId) {
		customer customer = customerDao.findById(customerId).orElseThrow(() -> new NoSuchElementException("customer with ID = " + customerId + " does not exist"));
		
		boolean found = false;
		for (petStore Petstore : customer.getPetStores())
			if (Petstore.getPetStoreId() == petStoreId) {
				found = true;
				break;
			}
		if (!found) {
			throw new IllegalArgumentException("Customer with ID = " + customerId + " is not a member at the store with the ID = " + petStoreId);
		}
		return customer;
	}

	private customer findOrCreatecustomer(Long petStoreId, Long customerId) {
		if (Objects.isNull(customerId)) {
			return new customer();
		}
		return findcustomerById(petStoreId, customerId);
	}
	@Transactional(readOnly = false)
	public PetStoreEmployee saveEmployee(Long petStoreId, PetStoreEmployee employee1) {
		petStore Petstore = findPetStoreById(petStoreId);
		Long employeeId = Long.valueOf(employee1.getEmployeeId());
		employee Employee = findOrCreateEmployee(petStoreId, employeeId);
		copyEmployeeFields(Employee, employee1);
		Employee.setPetStore(Petstore);
		Petstore.getEmployees().add(Employee);
		employee dbEmployee = employeeDao.save(Employee);
		return new PetStoreEmployee(dbEmployee);
		
		
	}
	@Transactional
	public PetStoreCustomer savecustomer(Long petStoreId, PetStoreCustomer customer1) {
		petStore Petstore = findPetStoreById(petStoreId);
		Long customerId = Long.valueOf(customer1.getCustomerId());
		customer customer = findOrCreatecustomer(petStoreId, customerId);
		copyCustomerFields(customer, customer1);
		customer.getPetStores().add(Petstore);
		Petstore.getCustomers().add(customer);
		customer dbcustomer = customerDao.save(customer);
		return new PetStoreCustomer(dbcustomer);
	}
	@Transactional(readOnly = false)
	public List<PetStoreData> retrieveAllPetStores(){
		List<petStore> petStores = petStoreDao.findAll();
		List<PetStoreData> result = new LinkedList<>();
		for (petStore Petstore : petStores) {
		PetStoreData psd = new PetStoreData(Petstore);
		psd.getCustomers().clear();
		psd.getEmployees().clear();
		result.add(psd);
		}
		return result;
	}
	@Transactional(readOnly = true)
	public PetStoreData retrievePetStoreById(Long petStoreId) {
		return new PetStoreData(findPetStoreById(petStoreId));
	}
	@Transactional(readOnly = false)
	public void deletePetStoreById(Long petStoreId) {
		petStore Petstore = findPetStoreById(petStoreId);
		petStoreDao.delete(Petstore);
	}
}
