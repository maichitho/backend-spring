package com.mulodo.fiveneed.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.mulodo.fiveneed.entity.TblProduct;
import com.mulodo.fiveneed.entity.TblRequestPayment;

public interface PaymentRepositoryJPA extends JpaRepository<TblRequestPayment, Long> {
	public TblRequestPayment findOneByStatus(int status);

	@Query(value = "Select SUM(payment.total) from TblRequestPayment payment"
			+ " WHERE payment.status = :status AND payment.createdBy = :createdBy")
	public BigDecimal sumBuyStatusAndUser(@Param("status") int status, @Param("createdBy") long userId);

	@Query(value = "Select u from TblRequestPayment u"
			+ " where (u.createdAt >= :createdAtFrom OR :createdAtFrom is null)"
			+ " and (u.createdAt <= :createdAtTo OR :createdAtTo is null)"
			+ " and ((:statusSize =0) OR u.status in (:statusList))")
	public Page<TblRequestPayment> searchRequestPayment(@Param("createdAtFrom") Date createdAtFrom,
			@Param("createdAtTo") Date createdAtTo, @Param("statusList") List<Integer> statusList,
			@Param("statusSize") int statusSize, Pageable pageable);

	public Page<TblRequestPayment> findByCreatedBy(Long id, Pageable pageRequest);
}
