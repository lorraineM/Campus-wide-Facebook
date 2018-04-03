package net.zaizheli.repositories;

import java.util.List;

import net.zaizheli.domains.Comment;
import net.zaizheli.repositories.AtomicOperationsRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;

public interface CommentRepository extends 
		AtomicOperationsRepository<Comment, String> {
	
	@Query("{ 'activity': {'$ref': 'activity', '$id': { '$oid': ?0 } }}")
	List<Comment> findByActivity(String id);
	
	@Query("{ 'activity': {'$ref': 'activity', '$id': { '$oid': ?0 } }}")
	Page<Comment> findByActivity(String id, Pageable pageable);
	
}
