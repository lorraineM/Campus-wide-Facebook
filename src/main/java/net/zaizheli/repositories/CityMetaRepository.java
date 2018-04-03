package net.zaizheli.repositories;

import net.zaizheli.domains.CityMeta;

public interface CityMetaRepository extends
		AtomicOperationsRepository<CityMeta, String> {
	
	CityMeta getByPinyin(String pinyin);
	
	CityMeta getByName(String name);
}
