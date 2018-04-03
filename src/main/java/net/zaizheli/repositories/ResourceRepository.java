package net.zaizheli.repositories;

import java.util.List;

import net.zaizheli.domains.Resource;


public interface ResourceRepository extends 
		AtomicOperationsRepository<Resource, String> {
	
	Resource getByResId(String resId);
	
	List<Resource> findByActId(String actId);
}
