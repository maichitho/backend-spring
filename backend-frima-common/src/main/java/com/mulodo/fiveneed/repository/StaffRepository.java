package com.mulodo.fiveneed.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mulodo.fiveneed.entity.MstUser;

public interface StaffRepository extends JpaRepository<MstUser, Long> {

	Page<MstUser> findByIsSysUser(boolean isSysUser, Pageable pageable);

	MstUser findByEmail(String email);

}
