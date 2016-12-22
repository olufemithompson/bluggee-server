package com.bluggee.repository;

import org.springframework.data.repository.CrudRepository;

import com.bluggee.models.BlogCategory;

public interface BlogCategoryRepository extends CrudRepository<BlogCategory, Long> {

}
