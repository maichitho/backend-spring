package co.siten.myvtg.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.siten.myvtg.bean.MessageBean;
import co.siten.myvtg.exception.MyResourceNotFoundException;
import co.siten.myvtg.model.myvtg.Service;
import co.siten.myvtg.service.IServiceService;
import co.siten.myvtg.util.CommonUtil;
import co.siten.myvtg.util.MessageUtil;

@RestController
@RequestMapping(value = "/api/${version}")
public class ServiceController extends BaseController {

	@Autowired
	private IServiceService service;

	@Autowired
	private MessageUtil messageUtil;

	@RequestMapping(value = "/services", method = RequestMethod.GET)
	public Page<Service> searchData(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "size", required = false, defaultValue = "5") int size,
			@RequestParam(value = "sortBy", required = false, defaultValue = "name") String sortBy,
			@RequestParam(value = "sortType", required = false, defaultValue = "0") int sortType,
			@RequestParam(value = "serviceGroupId", required = false) String serviceGroupId,
			@RequestParam(value = "language", required = false) String language,
			@RequestParam(value = "name", required = false, defaultValue = "") String name) {

		Page<Service> resultPage = service.findServicePaginated(page, size, sortBy, sortType, serviceGroupId, language,
				name);
		if (page > resultPage.getTotalPages()) {
			throw new MyResourceNotFoundException();
		}
		return resultPage;
	}

	@RequestMapping(value = "/services/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Service> getById(@PathVariable("id") String id) {
		Service data = service.findById(id);
		if (data == null) {
			return new ResponseEntity<Service>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Service>(data, HttpStatus.OK);
	}

	@RequestMapping(value = "/services", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<MessageBean> create(@Valid @RequestBody Service inputData) {
		try {

			if (service.findOneByCodeAndLanguage(inputData.getCode(), inputData.getLanguage()) != null) {
				return new ResponseEntity<MessageBean>(new MessageBean("2", "Code đã tồn tại không thể thêm"),
						HttpStatus.BAD_REQUEST);
			}

			Service data = new Service();

			data.setCode(inputData.getCode());
			data.setName(inputData.getName());
			data.setServiceGroupId(inputData.getServiceGroupId());
			data.setLanguage(inputData.getLanguage());
			data.setShortCode(inputData.getShortCode());
			data.setActionType(inputData.getActionType());
			data.setServiceType(inputData.getServiceType());
			data.setShortDes(inputData.getShortDes());
			data.setFullDes(inputData.getFullDes());
			data.setIconUrl(inputData.getIconUrl());
			data.setImgDesUrl(inputData.getImgDesUrl());
			data.setWebLink(inputData.getWebLink());

			// Auto set info
			data.setStatus(1);
			data.setCreatedTime(CommonUtil.GetCurrentTime());
			data.setCreatedBy(getCurrentUserId());

			service.save(data);

			return new ResponseEntity<MessageBean>(new MessageBean("", messageUtil.getMessage(""), data),
					HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<MessageBean>(new MessageBean("", e.getMessage()), HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(value = "/services/{id}", method = RequestMethod.PUT)
	public ResponseEntity<MessageBean> update(@PathVariable("id") String id, @Valid @RequestBody Service inputData) {
		try {

			Service data = service.findById(id);
			if (data == null) {
				return new ResponseEntity<MessageBean>(new MessageBean("", messageUtil.getMessage("")),
						HttpStatus.NOT_FOUND);
			}

			Service exist = service.findOneByCodeAndLanguage(inputData.getCode(), inputData.getLanguage());
			if (exist != null && !exist.getId().equals(id)) {
				return new ResponseEntity<MessageBean>(new MessageBean("2", "Code đã tồn tại"), HttpStatus.BAD_REQUEST);
			}

			// TODO setAll Information
			data.setCode(inputData.getCode());
			data.setName(inputData.getName());
			data.setServiceGroupId(inputData.getServiceGroupId());
			data.setLanguage(inputData.getLanguage());
			data.setShortCode(inputData.getShortCode());
			data.setActionType(inputData.getActionType());
			data.setServiceType(inputData.getServiceType());
			data.setShortDes(inputData.getShortDes());
			data.setFullDes(inputData.getFullDes());
			data.setIconUrl(inputData.getIconUrl());
			data.setImgDesUrl(inputData.getImgDesUrl());
			data.setWebLink(inputData.getWebLink());

			// auto set info
			data.setLastUpdatedTime(CommonUtil.GetCurrentTime());
			data.setLastUpdatedBy(getCurrentUserId());

			service.save(data);
			return new ResponseEntity<MessageBean>(new MessageBean("", messageUtil.getMessage(""), data),
					HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<MessageBean>(new MessageBean("", e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/services/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<MessageBean> delete(@PathVariable("id") String id) {

		Service data = service.findById(id);
		if (data == null) {
			return new ResponseEntity<MessageBean>(new MessageBean("", messageUtil.getMessage("")),
					HttpStatus.NOT_FOUND);
		}
		data.setStatus(0);

		service.save(data);
		return new ResponseEntity<MessageBean>(HttpStatus.NO_CONTENT);
	}

}
