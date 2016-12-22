package com.bluggee.repository;

import org.springframework.data.repository.CrudRepository;

import com.bluggee.models.BlogCategory;
import com.bluggee.models.BlogSourceAndCategory;

public interface BlogSourceAndCategoryRepository extends CrudRepository<BlogSourceAndCategory, Long> {

}
