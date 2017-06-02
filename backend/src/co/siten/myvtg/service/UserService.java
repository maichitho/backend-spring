
package co.siten.myvtg.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import co.siten.myvtg.dao.UserDao;
import co.siten.myvtg.model.myvtg.User;


@org.springframework.stereotype.Service("IUserService")
public class UserService implements IUserService {
	@Autowired
	UserDao dao;

	public User findById(String id) {
		User data = dao.findOne(id);
		return data;
	}


	public boolean isExist(User data) {
		return dao.findOneByUsername(data.getUsername()) != null;
	}


	public User save(User data) {
		User savedData = dao.save(data);
		return savedData;
	}

	@Override
	public void delete(User data) {
		dao.delete(data);
	}

	public Page<User> findPaginated(int page, int size, String sortBy, int sortType,String name) {
		if (name != null && !name.equals("")) {
			return dao.findByFullNameContainingAndStatus(name,1, new PageRequest(page, size,
					new Sort(new Order(sortType == 0 ? Direction.ASC : Direction.DESC, sortBy))));
		} else {
			return dao.findByStatus(1, new PageRequest(page, size,
					new Sort(new Order(sortType == 0 ? Direction.ASC : Direction.DESC, sortBy))));
		}
	}


	@Override
	public User findByUserName(String username) {
		return dao.findOneByUsername(username);
	}


	
}
