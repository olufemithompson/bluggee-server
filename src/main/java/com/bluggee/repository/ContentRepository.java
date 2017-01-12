package com.bluggee.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.bluggee.models.Content;

public interface ContentRepository extends CrudRepository<Content, Long> {
	
	@Query(value = "select o from Content o  where o.uniqueId =:uniqueId")
	List<Content> findByUniqueId(Pageable pageable, @Param("uniqueId") String uniqueId);
	
	@Query(value = "select o from Content o  group by o.title order  by o.id DESC")
    List<Content> list(Pageable pageable);
	
	
	@Query(value = "select o from Content o where o.source.id in :ids  group by o.title order by o.id DESC")
    List<Content> list(Pageable pageable, @Param("ids")  List<Long> ids);
	
	
	
}
