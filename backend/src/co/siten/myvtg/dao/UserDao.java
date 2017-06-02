
package co.siten.myvtg.dao;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.siten.myvtg.model.myvtg.User;

@Repository("UserDao")
public interface UserDao extends JpaRepository<User, String> {

	public User findOneByUsername(String username);

	public Page<User> findByFullNameContainingAndStatus(String fullName,int status, Pageable pageRequest);

	public Page<User> findByStatus(int status, Pageable pageRequest);

	//public List<User> findByApprovedAndStatus(int approved, int status);
	//
	//public List<User> findByApprovedAndIdInAndStatus(int approved,List<String> ids,int status);
}
