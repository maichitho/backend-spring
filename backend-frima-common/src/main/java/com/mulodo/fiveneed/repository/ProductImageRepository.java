package com.mulodo.fiveneed.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.mulodo.fiveneed.entity.TblProductImage;

public interface ProductImageRepository
		extends JpaRepository<TblProductImage, Long> {
	public List<TblProductImage> findByProductId(long productId);

}
