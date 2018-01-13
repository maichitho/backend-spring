package com.mulodo.fiveneed.repository;

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

	
	
	// @Query(value = "Select u from MstUser u where u.isSysUser= :isSysUser and
	// u.isDeleted= :isDeleted"
	// + " and (u.id= :id OR :id = 0) and (email like :email OR :email ='') and
	// (name like :username OR :username ='') and (:revenueFrom>=:revenueFrom )
	// and "
	// + ":revenueTo <= :revenueTo and :productFrom>=:productFrom and
	// :productTo<=:productTo")
	// Page<MstUser> findUserByParams(@Param("isSysUser") boolean isSysUser,
	// @Param("isDeleted") boolean isDeleted, @Param("id") int id,
	// @Param("username") String username, @Param("email") String email,
	// @Param("revenueFrom") BigDecimal revenueFrom,
	// @Param("revenueTo") BigDecimal revenueTo,
	// @Param("productFrom") int productFrom,
	// @Param("productTo") int productTo, Pageable pageable);
}
