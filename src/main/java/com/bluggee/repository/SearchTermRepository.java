package com.bluggee.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bluggee.models.SearchTerm;

public interface SearchTermRepository extends CrudRepository<SearchTerm, Long> {
	
}
