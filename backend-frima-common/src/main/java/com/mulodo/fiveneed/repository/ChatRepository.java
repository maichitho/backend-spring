package com.mulodo.fiveneed.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mulodo.fiveneed.entity.TblChat;

public interface ChatRepository extends JpaRepository<TblChat, String> {

	public List<TblChat> findByProductIdOrderByCreatedAtDesc(Long productId);

	public Page<TblChat> findByCreatedBy(Long id, Pageable pageRequest);

	@Query(value = "Select u from TblChat u"
			+ " where u.id in (SELECT MAX(u1.id) FROM TblChat u1 where u1.createdBy=:createdBy OR u1.userId=:createdBy GROUP BY u1.productId) ORDER BY u.createdAt")
	public Page<TblChat> searchComment(@Param("createdBy") Long id, Pageable pageRequest);

	public Page<TblChat> findByProductIdAndTypeOrderByCreatedAtDesc(long id, int type, Pageable pageable);
}
