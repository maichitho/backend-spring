package com.vietis.carpark.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vietis.carpark.entity.TblProduct;

public interface ProductRepository extends JpaRepository<TblProduct, Long> {
	public List<TblProduct> findByStatusAndCreatedBy(String status, long userId);

	@Query(value = "Select u from TblProduct u" + " where (u.id= :id OR :id = 0)"
			+ " and (u.name like :name OR :name ='%%')" + " and (u.createdBy = :createdBy OR :createdBy =0)"
			+ " and (u.createdAt >= :createdAtFrom OR :createdAtFrom is null)"
			+ " and (u.createdAt <= :createdAtTo OR :createdAtTo is null)"
			+ " and ((:statusSize =0) OR u.status in (:statusList))"
			+ " and ((:categorySize =0) OR u.categoryId in (:categoryList))")
	public Page<TblProduct> searchProduct(@Param("id") long id, @Param("name") String name,
			@Param("createdAtFrom") Date createdAtFrom, @Param("createdAtTo") Date createdAtTo,
			@Param("createdBy") long createdBy, @Param("statusList") List<String> statusList,
			@Param("statusSize") int statusSize, @Param("categoryList") List<Integer> categoryList,
			@Param("categorySize") int categorySize, Pageable pageable);

}
