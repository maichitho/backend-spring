package com.vietis.carpark.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.vietis.carpark.entity.User;

public interface UserRepository
		extends PagingAndSortingRepository<User, Long> {
	

	public User findOneByAccessTokenAndStatusAndIsDeleted(String first,
			int active, boolean b);

	public Page<User> findByIsSysUserAndIsDeleted(boolean isSysUser,
			boolean isDeleted, Pageable page);

	public User findByEmailAndStatusAndIsDeleted(String email, int active,
			boolean b);

	//Danhloc 
	public User findByEmailAndIsSysUserAndStatusAndProviderType(String email,
			boolean isSysUser, int status, String providerType);
	
	public User findOneByEmailAndStatusAndIsDeleted(String email,
			int status, boolean isDeleted);
	
	public User findOneByActivationKey(String activationKey);

	public User findByEmailAndIsDeleted(String email, boolean b);

}
