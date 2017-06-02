
package co.siten.myvtg.service;

import java.util.List;
import org.springframework.data.domain.Page;

import co.siten.myvtg.model.myvtg.User;


public interface IUserService {

	public User findById(String id);

	public User findByUserName(String username);
	
	public boolean isExist(User data);

	public User save(User data);

	public void delete(User data);

	public Page<User> findPaginated(int page, int size, String sortBy, int sortType,String name);

//	public void Approve(boolean forAll, List<String> ids,boolean active);

}
