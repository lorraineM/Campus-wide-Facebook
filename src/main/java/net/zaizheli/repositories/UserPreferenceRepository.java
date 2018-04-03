package net.zaizheli.repositories;

import net.zaizheli.domains.User;
import net.zaizheli.domains.UserPreference;

public interface UserPreferenceRepository extends
		AtomicOperationsRepository<UserPreference, String> {
	
	UserPreference getByUser(User user);
}
