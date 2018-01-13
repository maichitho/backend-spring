package com.mulodo.fiveneed.controller;

import java.io.File;
import java.io.FileOutputStream;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.mulodo.fiveneed.bean.ResponseBean;
import com.mulodo.fiveneed.bean.request.FileUploadBean;
import com.mulodo.fiveneed.constant.AppHttpStatus;
import com.mulodo.fiveneed.service.CategoryService;
import com.mulodo.fiveneed.service.DataTypeService;
import com.mulodo.fiveneed.service.FTPService;
import com.mulodo.fiveneed.service.MasterDataService;

@RestController
@RequestMapping("/common")
@PropertySource(value = { "classpath:application.properties" })
public class CommonController extends BaseController {
	@Autowired
	private MasterDataService masterDataService;

	@Autowired
	private DataTypeService dataTypeService;

	@Autowired
	private FTPService ftpService;

	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = "/category", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody ResponseEntity<ResponseBean> getCategory(
			@RequestParam(value = "parent_id", required = false) Integer parentId) {
		ResponseBean response = new ResponseBean();

		try {
			masterDataService.getCategoryByParentId(parentId, response);
			return response(response);
		} catch (Exception e) {
			response.setStatus(AppHttpStatus.INTERNAL_SERVER_ERROR);
			return response(response);
		}
	}

	@RequestMapping(value = "/upload_image", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<ResponseBean> uploadImage(@Valid @RequestBody FileUploadBean bean) {
		// Upload by FTP
		ResponseBean response = new ResponseBean();
		try {
			String content = bean.getContent();
			if (content.contains(",")) {
				content = content.split(",")[1];
			}
			byte[] imageByte = Base64Utils.decodeFromString(content);

			File tempFile = File.createTempFile("frima", "frima", null);
			FileOutputStream fos = new FileOutputStream(tempFile);
			fos.write(imageByte);
			String fileName = java.util.UUID.randomUUID().toString() + "." + bean.getExtension();
			String url = ftpService.uploadFileToFtp(tempFile, fileName, bean.getFolder());
			fos.close();
			response.setData(url);
			return response(response);
		} catch (Exception e) {
			logger.error("", e);
			response.setStatus(AppHttpStatus.INTERNAL_SERVER_ERROR);
			return response(response);
		}
	}

	/**
	 * API C01: get list data_type
	 */
	@RequestMapping(value = "/data_type/{id}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<ResponseBean> findDataType(@PathVariable("type") Integer type) {
		ResponseBean response = new ResponseBean();
		try {
			dataTypeService.findDataType(type, response);
			return response(response);
		} catch (Exception e) {
			response.setData(null);
			response.setMessage(e.getMessage());
			response.setStatus(AppHttpStatus.FAILED_TO_GET_DATA);
			return response(response);
		}

	}

	/**
	 * API C02: get list category
	 */
	@RequestMapping(value = "/category/{parentId}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<ResponseBean> findCategory(@PathVariable("parentId") Integer parentId) {

		ResponseBean response = new ResponseBean();
		try {
			categoryService.findCategory(parentId, response);
			return response(response);
		} catch (Exception e) {
			response.setData(null);
			response.setMessage(e.getMessage());
			response.setStatus(AppHttpStatus.FAILED_TO_GET_DATA);
			return response(response);
		}

	}
}
