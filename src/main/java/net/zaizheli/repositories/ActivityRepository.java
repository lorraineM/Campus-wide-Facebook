package net.zaizheli.repositories;


import net.zaizheli.domains.Activity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;

public interface ActivityRepository extends 
		AtomicOperationsRepository<Activity, String> {
	
	//search api......................................................
	
	@Query("{ '$where': 'function() { return this.status != \"INVALID\" && (?0 ? this.location== ?0 : true) && " +
			" (?1 ? this.type== ?1 : true) && (?2 ? this.startDate>= ?2 : true) && (?3 ? this.endDate<= ?3 : true) && " +
			"(?4 ? (this.title? this.title.indexOf(?4)!=-1 : false) : true); } ' }")
	Page<Activity> search(String city, String type, String startTime, String endTime,
			String contentLike, Pageable pageable);
	
	@Query("{ '$where': 'function() { return this.status != \"INVALID\" && (?0 ? this.location== ?0 : true) && " +
			" (?1 ? this.type== ?1 : true) && (?2 ? this.startDate>= ?2 : true) && (?3 ? this.endDate<= ?3 : true) && " +
			"(?4 ? (this.title? this.title.indexOf(?4)!=-1 : false) : true); } ' }")
	Page<Activity> searchMarker(String city, String type, String startTime, String endTime,
			String contentLike, Pageable pageable);
	
	//..............................................................
	
	@Query("{ 'createdBy': {'$ref': 'user', '$id': { '$oid': ?0 } } , 'type' : ?1 }")
	Page<Activity> findByCreatedByAndtype(String id, String type,
			Pageable pageable);
	
	@Query("{ 'createdBy': {'$ref': 'user', '$id': { '$oid': ?0 } } }")
	Page<Activity> findByCreatedBy(String id, Pageable pageable);
	
	@Query("{'lngLat' : { '$nearSphere' : [ ?0 , ?1] , '$maxDistance' : ?2 } }")
	Page<Activity> findByLngLatNear(Double lng, Double lat, Double distance,
			Pageable pageable);
	
	Page<Activity> findByCity(String city, Pageable pageable);
	
}
