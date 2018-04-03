package net.zaizheli.repositories;

import net.zaizheli.constants.Gender;
import net.zaizheli.domains.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepository extends 
		AtomicOperationsRepository<User, String> {
	
	User getByEmail(String email);
	
	User getByName(String name);
	
	//search api......................................................
	
	@Query("{ '$where': 'function() { return this.status != \"INVALID\" && " +
			" (?0 ? this.gender== ?0 : true) && (?1 ? (this.name? this.name.indexOf(?1)!=-1 : false) : true); } ' }")
	Page<User> search(String gender, String nameLike, Pageable pageable);
	
	Page<User> findByCityAndGenderAndNameLike(String city, Gender gender, String keyword,
			Pageable pageable);
	
	Page<User> findByCityAndGender(String city, Gender gender, Pageable pageable);
	
	Page<User> findByGenderAndNameLike(Gender gender, String keyword, Pageable pageable);
	
	Page<User> findByCityAndNameLike(String city, String keyword, Pageable pageable);
	
	Page<User> findByCity(String city, Pageable pageable);
	
	Page<User> findByGender(Gender gender, Pageable pageable);
	
	Page<User> findByNameLike(String keyword, Pageable pageable);	
	
	//..................................................................
}
