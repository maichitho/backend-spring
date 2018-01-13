package com.mulodo.fiveneed.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mulodo.fiveneed.entity.TblTodo;

public interface TodoRepository extends JpaRepository<TblTodo, Long> {
	Page<TblTodo> findAll(Pageable pageable);

}
