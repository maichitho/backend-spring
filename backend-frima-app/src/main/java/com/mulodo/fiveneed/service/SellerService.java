package com.mulodo.fiveneed.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mulodo.fiveneed.bean.ResponseBean;
import com.mulodo.fiveneed.common.util.CommonUtil;
import com.mulodo.fiveneed.constant.AppHttpStatus;
import com.mulodo.fiveneed.entity.MstUser;
import com.mulodo.fiveneed.entity.TblRequestPayment;
import com.mulodo.fiveneed.repository.PaymentRepositoryJPA;

@Service("SellerService")
@Transactional(rollbackFor = Exception.class)
public class SellerService extends BaseService {
	@Autowired
	PaymentRepositoryJPA paymentRepo;

	public void createRequestPayment(TblRequestPayment rp, ResponseBean response) {
		MstUser user = checkTokenInSession();
		if (user == null) {
			response.setStatus(AppHttpStatus.AUTH_FAILED);
			return;
		}
		rp.setCreatedAt(CommonUtil.getCurrentTime());
		rp.setCreatedBy(user.getId());
		rp.setStatus(TblRequestPayment.STATUS_NOT_PAID);
		paymentRepo.save(rp);
	}

}
