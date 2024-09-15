package pet.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pet.store.entity.petStore;

public interface PetStoreDao extends JpaRepository<petStore, Long> {

}
