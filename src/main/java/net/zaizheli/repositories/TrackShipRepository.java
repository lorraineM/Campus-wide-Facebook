package net.zaizheli.repositories;

import java.util.List;

import net.zaizheli.domains.TrackShip;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;

public interface TrackShipRepository extends
		AtomicOperationsRepository<TrackShip, String> {

	@Query("{ 'target': {'$ref': 'activity', '$id': { '$oid': ?0 } } , " +
		    " 'tracked': {'$ref': 'user', '$id': { '$oid': ?1 } }}")
	TrackShip getByTargetAndTracked(String targetId, String trackedId);
	
	@Query("{ 'tracked': {'$ref': 'user', '$id': { '$oid': ?0 } } , " +
			" 'status':  ?1 }")
	Page<TrackShip> findByTrackedAndStatus(String trackedId, int status, Pageable pageable);
	
	@Query("{ 'tracked': {'$ref': 'user', '$id': { '$oid': ?0 } } , " +
			" 'status':  ?1 }")
	List<TrackShip> findByTrackedAndStatus(String trackedId, int status);
}
