package com.mulodo.fiveneed.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.mulodo.fiveneed.entity.MstCategory;

public interface CategoryRepository extends JpaRepository<MstCategory, Integer> {
	public List<MstCategory> findByParentId(Integer parentId);

	Page<MstCategory> findAll(Pageable pageable);

}
