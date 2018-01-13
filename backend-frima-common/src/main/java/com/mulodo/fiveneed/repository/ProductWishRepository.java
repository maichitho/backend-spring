package com.mulodo.fiveneed.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mulodo.fiveneed.entity.TblProductWish;

public interface ProductWishRepository extends JpaRepository<TblProductWish, Long> {
	public List<TblProductWish> findByProductId(Long productId);

	public Page<TblProductWish> findByUserBuyId(Long id, Pageable pageRequest);

	TblProductWish findByProductIdAndUserBuyId(Long productId, Long userBuyId);
	
	
}
