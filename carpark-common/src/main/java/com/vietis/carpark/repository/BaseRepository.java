package com.vietis.carpark.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 * Base Repository
 *
 * @author thomc
 *
 */
@Repository
public abstract class BaseRepository {
	/*-----------------------------------------------
	* Property
	*-----------------------------------------------*/
	@PersistenceContext
	EntityManager entityManager;
}
