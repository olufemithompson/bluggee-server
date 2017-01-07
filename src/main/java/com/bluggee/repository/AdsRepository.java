package com.bluggee.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bluggee.models.Ads;

public interface AdsRepository extends CrudRepository<Ads, Long> {

	@Query(value = "select count(o) from Ads o")
    long countAds();
	
	@Query(value = "select o from Ads o")
	List<Ads> random(Pageable pageable);
}
