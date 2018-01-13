package com.mulodo.fiveneed.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mulodo.fiveneed.bean.ResponseBean;
import com.mulodo.fiveneed.constant.AppHttpStatus;
import com.mulodo.fiveneed.entity.MstDataType;
import com.mulodo.fiveneed.entity.MstUser;
import com.mulodo.fiveneed.repository.DataTypeRepository;

@Service("DataTypeService")
@Transactional(rollbackFor = Exception.class)
public class DataTypeService extends BaseService {
	@Autowired
	private DataTypeRepository dataTypeRepository;

	public void findDataType(Integer type, ResponseBean response) {
		MstUser user = checkTokenInSession();
		if (user == null) {
			response.setStatus(AppHttpStatus.AUTH_FAILED);
			return;
		}
		List<MstDataType> dataType = new ArrayList<MstDataType>();
		if (type == -1) {
			dataType = dataTypeRepository.findAll();
		} else if (type > -1) {
			dataType = dataTypeRepository.findByType(type);
		}
		response.setData(dataType);

	}

}
