package com.bluggee.repository;

import org.springframework.data.repository.CrudRepository;

import com.bluggee.models.Content;

public interface ContentRepository extends CrudRepository<Content, Long> {
	
	Content findByUniqueId(String uniqueId);
}
