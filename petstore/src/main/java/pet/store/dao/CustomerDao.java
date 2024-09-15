package pet.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pet.store.entity.customer;

public interface CustomerDao extends JpaRepository<customer, Long> {

}
