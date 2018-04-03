package net.zaizheli.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;

import net.zaizheli.domains.Message;

public interface MessageRepository extends
			AtomicOperationsRepository<Message, String> {
	
	@Query("{ 'to': {'$ref': 'user', '$id': { '$oid': ?0 } } , 'type' : ?1 }")
	List<Message> findByToAndtype(String id, String type);
	
	@Query("{ 'from': {'$ref': 'user', '$id': { '$oid': ?0 } } , 'type' : ?1 }")
	List<Message> findByFromAndtype(String id, String type);
	
	@Query("{ 'to': {'$ref': 'user', '$id': { '$oid': ?0 } } , 'type' : ?1 , 'status' : ?2 }")
	List<Message> findMes(String id, String type, int status);
	
	@Query("{ 'to': {'$ref': 'user', '$id': { '$oid': ?0 } } , 'type' : ?1 }")
	Page<Message> findByToAndtype(String id, String type, Pageable pageable);
	
	@Query("{ 'to': {'$ref': 'user', '$id': { '$oid': ?0 } } , 'status' : ?1 }")
	List<Message> findByToAndstatus(String id, int status);
	
	@Query("{ 'from': {'$ref': 'user', '$id': { '$oid': ?0 } } , 'type' : ?1 }")
	Page<Message> findByFromAndtype(String id, String type, Pageable pageable);
}
