package com.bluggee.search;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.bluggee.models.Content;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class ContentSearch {
	
	// ------------------------
	  // PRIVATE FIELDS
	  // ------------------------
	  
	  // Spring will inject here the entity manager object
	  @PersistenceContext
	  private EntityManager entityManager;


	  // ------------------------
	  // PUBLIC METHODS
	  // ------------------------
	  
	  /**
	   * A basic search for the entity User. The search is done by exact match per
	   * keywords on fields name, city and email.
	   * 
	   * @param text The query text.
	   */
	  public List<Content> search(String text, int page) {
	    
	    // get the full text entity manager
	    FullTextEntityManager fullTextEntityManager =
	      org.hibernate.search.jpa.Search.
	      getFullTextEntityManager(entityManager);
	    
	    // create the query using Hibernate Search query DSL
	    QueryBuilder queryBuilder = 
	      fullTextEntityManager.getSearchFactory()
	      .buildQueryBuilder().forEntity(Content.class).get();
	    
	    // a very basic query by keywords
	    org.apache.lucene.search.Query query =
	      queryBuilder
	        .keyword()
	        .onFields("title", "description")
	        
	        .matching(text)
	        .createQuery();

	    // wrap Lucene query in an Hibernate Query object
	    org.hibernate.search.jpa.FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(query, Content.class);
	  
	    // execute search and return results (sorted by relevance as default)
	    @SuppressWarnings("unchecked")
	    List<Content> results = jpaQuery.setFirstResult(page).setMaxResults(20).getResultList();
	    
	    return results;
	  } // method search


}
