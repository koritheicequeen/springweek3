package pet.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pet.store.entity.employee;

public interface EmployeeDao extends JpaRepository<employee, Long> {

}
