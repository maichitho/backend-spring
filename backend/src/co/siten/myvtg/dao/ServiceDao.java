package co.siten.myvtg.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.siten.myvtg.model.myvtg.Service;



@Repository("ServiceDao")
public interface ServiceDao extends JpaRepository<Service, String> {
	public Page<Service> findByNameContainingAndServiceGroupIdAndLanguageAndStatus(String name, String serviceGroupId,
			String language,int status, Pageable pageRequest);

	public Page<Service> findByNameContainingAndServiceGroupIdAndStatus(String name, String serviceGroupId,int status,
			Pageable pageRequest);

	public Page<Service> findByNameContainingAndLanguageAndStatus(String name, String language,int status, Pageable pageRequest);

	public Page<Service> findByNameContainingAndStatus(String name,int status, Pageable pageRequest);
	
	public Service findOneByCodeAndLanguageAndStatus(String code,String language,int status);
	
	public Service findOneByIdAndStatus(String id, int status);
	
	public List<Service> findByApprovedAndStatus(int approved, int status);
	
	public List<Service> findByApprovedAndIdInAndStatus(int approved,List<String> ids,int status);
}
