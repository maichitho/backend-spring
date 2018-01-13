package com.mulodo.fiveneed.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mulodo.fiveneed.bean.ResponseBean;
import com.mulodo.fiveneed.constant.AppHttpStatus;
import com.mulodo.fiveneed.entity.MstCategory;
import com.mulodo.fiveneed.entity.MstUser;
import com.mulodo.fiveneed.repository.CategoryRepository;

@Service("CategoryService")
@Transactional(rollbackFor = Exception.class)
public class CategoryService extends BaseService {

	@Autowired
	private CategoryRepository categoryRepository;

	public void findCategory(Integer parentId, ResponseBean response) {
		
		List<MstCategory> category = new ArrayList<MstCategory>();
		if (parentId == -1) {
			category = categoryRepository.findAll();
		} else if (parentId > -1) {
			category = categoryRepository.findByParentId(parentId);
		}
		if (category == null) {
			response.setStatus(AppHttpStatus.FAILED_TO_GET_DATA);
		} else {
			response.setStatus(AppHttpStatus.SUCCESS);
			response.setData(category);
		}

	}

}