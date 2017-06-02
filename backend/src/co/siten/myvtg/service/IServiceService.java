package co.siten.myvtg.service;

import java.util.List;

import org.springframework.data.domain.Page;

import co.siten.myvtg.model.myvtg.Service;


public interface IServiceService {

	public Service findById(String id);


	public boolean isExist(Service data);


	public String save(Service data);

	public void delete(Service data);

	public Page<Service> findServicePaginated(int page, int size, String sortBy, int sortType, String groupId,
			String lang, String name);

	
	public Service findOneByCodeAndLanguage(String code, String lang);
	
	public void Approve(boolean forAll, List<String> ids,boolean active);
	
}
