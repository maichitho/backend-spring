package com.mulodo.fiveneed.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.mulodo.fiveneed.entity.MstDataType;

public interface DataTypeRepository extends JpaRepository<MstDataType, Integer> {

	List<MstDataType> findByTypeInOrderByOrderNoAsc(List<Integer> typeList);

	List<MstDataType> findByType(Integer type);

	Page<MstDataType> findAll(Pageable pageable);

}
