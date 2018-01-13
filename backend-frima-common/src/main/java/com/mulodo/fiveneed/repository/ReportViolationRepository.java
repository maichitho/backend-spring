package com.mulodo.fiveneed.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.mulodo.fiveneed.entity.TblReportViolation;

public interface ReportViolationRepository
		extends JpaRepository<TblReportViolation, Long> {

	public List<TblReportViolation> findByProductIdOrderByCreatedAtDesc(
			Long productId);

}
