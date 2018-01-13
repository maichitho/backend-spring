package com.vietis.carpark.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.vietis.carpark.entity.MstUser;

public interface UserRepository
		extends PagingAndSortingRepository<MstUser, Long> {
	

	public MstUser findOneByAccessTokenAndStatusAndIsDeleted(String first,
			String active, boolean b);

	public Page<MstUser> findByIsSysUserAndIsDeleted(boolean isSysUser,
			boolean isDeleted, Pageable page);

	public MstUser findByEmailAndStatusAndIsDeleted(String email, String active,
			boolean b);

	//Danhloc 
	public MstUser findByEmailAndIsSysUserAndStatusAndProviderType(String email,
			boolean isSysUser, String status, String providerType);
	
	public MstUser findOneByEmailAndStatusAndIsDeleted(String email,
			String status, boolean isDeleted);
	
	public MstUser findOneByActivationKey(String activationKey);

	public MstUser findByEmailAndIsDeleted(String email, boolean b);

}
