package com.mulodo.fiveneed.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mulodo.fiveneed.bean.ResponseBean;
import com.mulodo.fiveneed.repository.CategoryRepository;


@Service("MasterDataService")
@Transactional(rollbackFor = Exception.class)
public class MasterDataService extends BaseService {
	@Autowired
	private CategoryRepository categoryRepo;

	public void getCategoryByParentId(Integer parentId, ResponseBean response) {
		if (parentId.intValue() == -1) {
			response.setData(categoryRepo.findAll());
		} else {
			response.setData(categoryRepo.findByParentId(parentId));
		}

	}

}
