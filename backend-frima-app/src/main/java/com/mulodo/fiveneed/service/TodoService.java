package com.mulodo.fiveneed.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mulodo.fiveneed.bean.ResponseBean;
import com.mulodo.fiveneed.common.util.CommonUtil;
import com.mulodo.fiveneed.common.util.StringUtils;
import com.mulodo.fiveneed.constant.AppHttpStatus;
import com.mulodo.fiveneed.constant.Constants;
import com.mulodo.fiveneed.entity.MstUser;
import com.mulodo.fiveneed.entity.TblTodo;
import com.mulodo.fiveneed.repository.TodoRepository;

@Service("TodoService")
@Transactional(rollbackFor = Exception.class)
public class TodoService extends BaseService {

	@Autowired
	private TodoRepository todoRepository;

	public void findAllTodo(int page, int size, String sortBy, String sortType, ResponseBean response) {
		String sortByProperty = StringUtils.snakeCaseToCamelCase(sortBy);
		Sort.Order order = new Sort.Order(
				Constants.ORDER_ASC.equalsIgnoreCase(sortType) ? Direction.ASC : Direction.DESC, sortByProperty)
						.ignoreCase();
		MstUser user = checkTokenInSession();
		if (user == null) {
			response.setStatus(AppHttpStatus.AUTH_FAILED);
			return;
		}
		Page<TblTodo> todo = todoRepository.findAll(new PageRequest(page, size, new Sort(order)));
		response.setData(todo);

	}

	public void updateTodo(long id, ResponseBean response) {
		TblTodo todo = todoRepository.findOne(id);
		MstUser user = checkTokenInSession();
		if (user == null) {
			response.setStatus(AppHttpStatus.AUTH_FAILED);
			return;
		}
		if (todo == null) {
			response.setStatus(AppHttpStatus.FAILED_TO_UPDATE_DATA);
		} else {
			todo.setIsRead(true);
			todo.setUpdatedAt(CommonUtil.getCurrentTime());
			todoRepository.save(todo);
			response.setStatus(AppHttpStatus.SUCCESS);
		}

	}

}
