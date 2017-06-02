package co.siten.myvtg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import co.siten.myvtg.dao.ServiceDao;
import co.siten.myvtg.model.myvtg.Service;


@org.springframework.stereotype.Service("IServiceService")
public class ServiceService implements IServiceService {
	@Autowired
	ServiceDao dao;

	
	public Service findById(String id) {
		Service data = dao.findOneByIdAndStatus(id,1);
		return data;
	}

	
	public boolean isExist(Service data) {
		return dao.findOneByIdAndStatus(data.getId(),1) != null;
	}


	public String save(Service data) {
		Service id = dao.save(data);
		return id.getId();
	}

	@Override
	public void delete(Service data) {
		dao.delete(data);
	}

	public Page<Service> findServicePaginated(int page, int size, String sortBy, int sortType, String groupId,
			String lang, String name) {
		if (lang != null && groupId != null && lang!="") {
			return dao.findByNameContainingAndServiceGroupIdAndLanguageAndStatus(name, groupId, lang,1,
					new PageRequest(page, size,
							new Sort(new Order(sortType == 0 ? Direction.ASC : Direction.DESC, sortBy))));
		} else if (groupId != null) {
			return dao.findByNameContainingAndServiceGroupIdAndStatus(name, groupId,1, new PageRequest(page, size,
					new Sort(new Order(sortType == 0 ? Direction.ASC : Direction.DESC, sortBy))));
		} else if (lang != null && lang!="") {
			return dao.findByNameContainingAndLanguageAndStatus(name, lang,1, new PageRequest(page, size,
					new Sort(new Order(sortType == 0 ? Direction.ASC : Direction.DESC, sortBy))));
		} else {
			return dao.findByNameContainingAndStatus(name,1, new PageRequest(page, size,
					new Sort(new Order(sortType == 0 ? Direction.ASC : Direction.DESC, sortBy))));
		}
	}

	@Override
	public Service findOneByCodeAndLanguage(String code,String lang) {		
		return dao.findOneByCodeAndLanguageAndStatus(code,lang,1);
	}

	public void Approve(boolean forAll, List<String> ids,boolean active){
		List<Service> data;
		if(forAll){
			data = dao.findByApprovedAndStatus(active?0:1, 1);
		}
		else{
			data = dao.findByApprovedAndIdInAndStatus(active?0:1,ids,1);
		}
		
		for (Service d : data) {
			d.setApproved(active?1:0);
			dao.save(d);
		}
	}
	
}
