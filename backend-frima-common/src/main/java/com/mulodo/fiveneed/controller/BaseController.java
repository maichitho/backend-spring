package com.mulodo.fiveneed.controller;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.mulodo.fiveneed.bean.ResponseBean;
import com.mulodo.fiveneed.common.util.StringUtils;
import com.mulodo.fiveneed.constant.AppHttpStatus;

/**
 * BaseController
 *
 * @author thomc
 *
 */
@RestController
public abstract class BaseController {
	/*-----------------------------------------------
	* Property
	*-----------------------------------------------*/
	/**
	 * Logger
	 */
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	/*-----------------------------------------------
	* Protected
	*-----------------------------------------------*/

	/**
	 * Create response
	 *
	 * @param response
	 * @return response
	 */
	protected ResponseEntity<ResponseBean> response(ResponseBean response) {
		if (response == null) {
			throw new IllegalArgumentException("Please set ResponseBean.");
		}

		if (StringUtils.isEmpty(response.getStatus())) {
			response.setStatus(AppHttpStatus.SUCCESS);
		}
		return new ResponseEntity<ResponseBean>(response, HttpStatus.OK);
	}

	/**
	 * create file response
	 *
	 * @param fileName file name
	 * @param file byte array
	 * @return response
	 * @throws Exception
	 */
	protected ResponseEntity<byte[]> response(String fileName, byte[] file)
			throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename*=UTF-8''"
				+ URLEncoder.encode(fileName, StandardCharsets.UTF_8.name()));

		// Create response
		return ResponseEntity.ok().headers(headers).contentLength(file.length)
				.contentType(
						MediaType.parseMediaType("application/octet-stream"))
				.body(file);
	}

}
