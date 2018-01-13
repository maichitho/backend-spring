package com.mulodo.fiveneed.repository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.mulodo.fiveneed.common.util.DateUtils;
import com.mulodo.fiveneed.entity.TblRequestPayment;

@Repository

public class PaymentRepository extends BaseRepository {
	public List<TblRequestPayment> findByStatusAndCreateAt(
			Class<TblRequestPayment> entity, int page, int size, String sortBy,
			String sortType, int status, long startDay, long endDay) {
		// Create Query
		StringBuffer sb = new StringBuffer();
		Date start1 = new Date(startDay);
		Date end1 = new Date(endDay);
		Date start = DateUtils.beginOfDay(start1);
		Date end = DateUtils.endOfDay(end1);

		sb.append(
				"select * from tbl_request_payment where created_at >= :startDay and created_at <=created_at<= :endDay and status=:status limit :page , :size ");
		List abc = super.entityManager
				.createNativeQuery(sb.toString(), TblRequestPayment.class)
				.setParameter("startDay", start).setParameter("endDay", end)
				.setParameter("status", status)
				.setParameter("page", ((page - 1) * size))
				.setParameter("size", size).getResultList();
		return abc;
	}


	public List<TblRequestPayment> findById(Class<TblRequestPayment> entity,
			int page, int size, String sortBy, String sortType, int id) {
		StringBuffer sb = new StringBuffer();
		sb.append(
				"select * from tbl_request_payment where id=:id limit :page , :size ");
		List abc = super.entityManager
				.createNativeQuery(sb.toString(), TblRequestPayment.class)
				.setParameter("id", id)
				.setParameter("page", ((page - 1) * size))
				.setParameter("size", size).getResultList();
		return abc;
	}

}
