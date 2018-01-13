package com.mulodo.fiveneed.repository;

import java.math.BigDecimal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import com.mulodo.fiveneed.entity.MstUser;
import com.mulodo.fiveneed.entity.MstUserProfile;

public interface UserProfileRepository
		extends PagingAndSortingRepository<MstUserProfile, Long> {
	@Query(value = "Select u from MstUserProfile u"
			+ " where u.isDeleted= :isDeleted"
			+ " and (u.id= :id OR :id = 0)"
			+ " and (u.email like :email OR :email ='')"
			+ " and (u.name like :username OR :username ='')"
			+ " and (u.totalRevenue>=:revenueFrom OR :revenueFrom=0)"
			+ " and (u.totalRevenue <= :revenueTo OR :revenueTo=0)"
			+ " and (u.totalProduct>=:productFrom OR :productFrom=0)"
			+ " and (u.totalProduct<=:productTo OR :productTo=0)")
	Page<MstUserProfile> findUserByParams(@Param("isDeleted") boolean isDeleted,
			@Param("id") long id, @Param("username") String username,
			@Param("email") String email,
			@Param("revenueFrom") BigDecimal revenueFrom,
			@Param("revenueTo") BigDecimal revenueTo,
			@Param("productFrom") int productFrom,
			@Param("productTo") int productTo, Pageable pageable);

}
	
	
