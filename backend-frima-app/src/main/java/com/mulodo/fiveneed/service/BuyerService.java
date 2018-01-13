package com.mulodo.fiveneed.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("BuyerService")
@Transactional(rollbackFor = Exception.class)
public class BuyerService extends BaseService {
	
}
