package com.mulodo.fiveneed.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mulodo.fiveneed.entity.TblProductOrder;

public interface ProductOrderRepository extends JpaRepository<TblProductOrder, Long> {
	@Query(value = "Select SUM(ord.price) from TblProductOrder ord"
			+ " WHERE ord.status >= :status AND ord.userSellId = :createdBy")
	public BigDecimal totalRevenueByUser(@Param("status") int status, @Param("createdBy") long userId);

	@Query(value = "Select SUM(ord.price) from TblProductOrder ord" + " WHERE ord.status = "
			+ TblProductOrder.STATUS_5_COMPLETED + " AND ord.userSellId = :createdBy")
	public BigDecimal totalPaidByUser(@Param("createdBy") long userId);

	public List<TblProductOrder> findByRequestPaymentIdAndIsDeleted(long requestPaymentId, boolean isDeleted);

	@Query(value = "Select u from TblProductOrder u" + " where  (u.id=:id OR :id=0) "
			+ " and (u.userSellId=:userSellId OR :userSellId=0)"
			+ " and ((u.userSellId IN (SELECT us.userName from MstUser us WHERE us.userName like :userSellName)) OR :userSellName='')"
			+ " and (u.userBuyId=:userBuyId OR :userBuyId=0)"
			+ " and ((u.userBuyId IN (SELECT us.userName from MstUser us WHERE us.userName like :userBuyName)) OR :userBuyName='')"
			+ " and ((:statusSize =0) OR u.status in (:statusList))"
			+ " and ((:statusAlertSize =0) OR (u.status in (:statusAlertList)))")
	public Page<TblProductOrder> search(@Param("id") long id, @Param("userSellId") long userSellId,
			@Param("userSellName") String userSellName, @Param("userBuyId") long userBuyId,
			@Param("userBuyName") String userBuyName, @Param("statusList") List<Integer> statusList,
			@Param("statusSize") int statusSize, @Param("statusAlertList") List<Integer> statusAlertList,
			@Param("statusAlertSize") int statusAlertSize, Pageable pageRequest);

	@Query(value = "Select SUM(ord.remainPayment) from TblProductOrder ord"
			+ " WHERE ord.status >= :status AND ord.userSellId = :createdBy")
	public BigDecimal totalNeedRequestByUser(@Param("status") int status, @Param("createdBy") long id);

	@Query(value = "Select SUM(ord.remainPayment) from TblProductOrder ord"
			+ " WHERE ord.status = :status AND ord.userSellId = :createdBy and ord.receivedAt <=:exprireDate")
	public BigDecimal totalExpriedByUser(@Param("status") int status, @Param("createdBy") long id,
			@Param("exprireDate") Date date);

	public Page<TblProductOrder> findByUserSellIdAndStatusGreaterThan(Long id, int statusWaitAdminPaySeller,
			Pageable pageRequest);

	public Page<TblProductOrder> findByUserSellIdAndStatusGreaterThanAndRemainPaymentGreaterThan(Long id,
			int statusWaitAdminPaySeller, BigDecimal zero, Pageable pageRequest);

	public Page<TblProductOrder> findByUserBuyIdAndStatusBetween(Long id, int statusWaitBuyerPay,
			int statusWaitBuyerReceive, Pageable pageRequest);

	public List<TblProductOrder> findByUserSellIdAndStatus(long id, int statusWaitAdminPaySeller);
	
	public Page<TblProductOrder> findByStatusAndUserSellId(int status, long UserSellerId, Pageable pageRequest);

}
