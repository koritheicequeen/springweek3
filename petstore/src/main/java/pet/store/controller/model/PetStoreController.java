package pet.store.controller.model;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import lombok.extern.slf4j.Slf4j;
import pet.store.service.PetStoreService;

@RestController
@RequestMapping("/pet_store")
@Slf4j
public class PetStoreController {

	@Autowired
PetStoreService petStoreService;
	
	 @PostMapping
	 @ResponseStatus(code = HttpStatus.CREATED)
	    public PetStoreData InsertPetStore(@RequestBody PetStoreData petStoreData) {
	     log.info("Creating Pet Store {}", petStoreData);
	    
	        return petStoreService.savePetStore(petStoreData);
	    }
	 @PutMapping("/{petStoreId}")
	 public PetStoreData updatePetStore(@PathVariable int petStoreId, @RequestBody PetStoreData petStoreData) {
		 
		 petStoreData.setPetStoreId(Long.valueOf(petStoreId));
		  log.info("Updating Pet Store {}", petStoreData);
		  return petStoreService.savePetStore(petStoreData);
	 }
	 @PostMapping("/{petStoreId}/employee")
	 @ResponseStatus(code = HttpStatus.CREATED)
	    public PetStoreEmployee AddEmployee(@PathVariable Long petStoreId, @RequestBody PetStoreEmployee petStoreEmployee) {
	     log.info("Adding Employee {} to pet Store with ID = {}", petStoreEmployee, petStoreId);
	     
	       return petStoreService.saveEmployee(petStoreId, petStoreEmployee);
	    }
	 @PostMapping("/{petStoreId}/customer")
	 @ResponseStatus(code = HttpStatus.CREATED)
	    public PetStoreCustomer Addcustomer(@PathVariable Long petStoreId, @RequestBody PetStoreCustomer petStorecustomer) {
	     log.info("Adding Customer {} to pet Store with ID = {}", petStorecustomer, petStoreId);
	    
	       return petStoreService.savecustomer(petStoreId, petStorecustomer);
	    }
	 @GetMapping
	 public List<PetStoreData> retrieveAllPetStores(){
		 log.info("Retrieving all Pet Stores");
		 return petStoreService.retrieveAllPetStores();
	 }
	 @GetMapping("/{petStoreId}")
	 public PetStoreData retrievePetStoreById(@PathVariable Long petStoreId){
		 log.info("Retrieving pet store with ID = {}", petStoreId);
		 return petStoreService.retrievePetStoreById(petStoreId);
	 }
	 @DeleteMapping("/{petStoreId}")
	 public Map<String, String> deletePetStoreById(@PathVariable Long petStoreId){
		 log.info("Deleting Pet Store with ID = {}", petStoreId);
		 petStoreService.deletePetStoreById(petStoreId);
		 return Map.of("message",  "Deleted Pet Store with ID = " + petStoreId);
	 }
}
