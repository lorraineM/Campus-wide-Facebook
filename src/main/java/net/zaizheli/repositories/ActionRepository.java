package net.zaizheli.repositories;

import java.util.List;

import net.zaizheli.constants.ActionType;
import net.zaizheli.domains.Action;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;

public interface ActionRepository extends
	AtomicOperationsRepository<Action, String> {
	
	Page<Action> findByOwner(String id, Pageable pageable);
	
	Action getByOwnerAndTargetActivityAndType(String uid, String sid, String type);
	
	@Query("{ 'owner': {$in : ?0}, 'type': { $in : ?1 } }")
	Page<Action> findByOwnerInAndTypeIn(List<String> uids, List<String> types, Pageable pageable);
	
	@Query("{ 'owner': ?0, 'type': { $in : ?1 } }")
	List<Action> findByOwnerAndTypeIn(String id, List<String> types);
	
	@Query("{ 'targetActivity': {$in : ?0}, 'type': { $in : ?1 } }")
	Page<Action> findByTragetActivityInAndTypeIn(List<String> sids, List<String> types, Pageable pageable);
	
	@Query("{ 'targetUser': ?0, 'type': { $in : ?1 } }")
	Page<Action> findByTragetUserAndTypeIn(String id, List<String> types, Pageable pageable);
	
	@Query("{ 'basedOn': {$in : ?0}, 'type': { $in : ?1 }, 'owner': { $ne : ?2 } }")
	Page<Action> findByBasedOnInAndTypeInAndOwnerNot(List<String> bids, List<String> types, String oid, Pageable pageable);
	
	Page<Action> findByOwnerAndType(String id, ActionType type, Pageable pageable);
}
