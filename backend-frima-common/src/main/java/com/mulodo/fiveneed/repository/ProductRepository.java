package com.mulodo.fiveneed.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.mulodo.fiveneed.entity.TblProduct;

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

	@Query("select count(e) from TblProduct e where e.status in (:statusList)")
	public long countByStatus(@Param("statusList") List<String> statusProductList);
	
	@Query("SELECT u FROM TblProduct u WHERE u.categoryId "
			+ "IN (SELECT c.id FROM MstCategory c WHERE c.parentsId LIKE CONCAT('%-',:category,'-%'))")
	public Page<TblProduct> findProductByCategory(@Param("category") String category, Pageable pageable);

	public Page<TblProduct> findByProductTypeAndCreatedBy(int productType,long userId ,Pageable pageable);
	
	public Page<TblProduct> findByStatusBetweenAndCreatedBy(String statusPublished,String statusDraft, long user_Id,Pageable pageable);
	
	public TblProduct findByStatusBetweenAndCreatedByAndId(String statusPublished,String statusDraft,long user_Id,long id);
	
	public TblProduct findByCreatedByAndId(long user_id,long id);
	
	
}
