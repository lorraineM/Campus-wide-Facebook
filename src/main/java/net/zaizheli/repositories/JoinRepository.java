package net.zaizheli.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;

import net.zaizheli.domains.Join;


public interface JoinRepository extends
		AtomicOperationsRepository<Join, String> {
	
	
	@Query("{ 'joiner': {'$ref': 'user', '$id': { '$oid': ?0 } } }")
	Page<Join> findByJoiner(String id, Pageable pageable);
	
	@Query("{ 'activity': {'$ref': 'activity', '$id': { '$oid': ?0 } } }")
	Page<Join> findByActivity(String id, Pageable pageable);
	
	@Query("{ 'activity': {'$ref': 'activity', '$id': { '$oid': ?0 } }, 'joiner': {'$ref': 'user', '$id': { '$oid': ?1} } }")
	Join findByActivityAndjoiner(String id, String uid);
	
	
}
