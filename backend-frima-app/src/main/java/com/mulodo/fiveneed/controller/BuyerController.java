package com.mulodo.fiveneed.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mulodo.fiveneed.bean.ResponseBean;
import com.mulodo.fiveneed.constant.AppHttpStatus;
import com.mulodo.fiveneed.entity.TblChat;
import com.mulodo.fiveneed.entity.TblProductOrder;
import com.mulodo.fiveneed.entity.TblReportViolation;
import com.mulodo.fiveneed.service.ProductService;

@RestController
@RequestMapping("/buyer")
@PropertySource(value = { "classpath:application.properties" })
public class BuyerController extends BaseController {

	@Autowired
	ProductService productService;

	/**
	 * B07
	 * 
	 * @param page
	 * @param size
	 * @param sortBy
	 * @param sortType
	 * @return
	 */
	@RequestMapping(value = "/product/wish", method = RequestMethod.GET)
	public ResponseEntity<ResponseBean> getProductWish(
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "size", required = false, defaultValue = "10") int size,
			@RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy,
			@RequestParam(value = "sortType", required = false, defaultValue = "asc") String sortType) {
		ResponseBean response = new ResponseBean();
		try {
			productService.searchWish(page, size, sortBy, sortType, response);
			return response(response);
		} catch (Exception e) {
			logger.error("searchUser", e);
			response.setStatus(AppHttpStatus.INTERNAL_SERVER_ERROR);
			response.setData(null);
			response.setMessage(e.getMessage());
			return response(response);
		}
	}

	/**
	 * B03
	 * 
	 * @param page
	 * @param size
	 * @param sortBy
	 * @param sortType
	 * @return
	 */
	@RequestMapping(value = "/product_order/buying", method = RequestMethod.GET)
	public ResponseEntity<ResponseBean> searchBuyingProduct(
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "size", required = false, defaultValue = "10") int size,
			@RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy,
			@RequestParam(value = "sortType", required = false, defaultValue = "asc") String sortType) {
		ResponseBean response = new ResponseBean();
		try {
			productService.searchProductBuying(page, size, sortBy, sortType, response);
			return response(response);
		} catch (Exception e) {
			logger.error("searchBuyingProduct", e);
			response.setStatus(AppHttpStatus.INTERNAL_SERVER_ERROR);
			response.setData(null);
			response.setMessage(e.getMessage());
			return response(response);
		}
	}

	/**
	 * B04
	 * 
	 * @param page
	 * @param size
	 * @param sortBy
	 * @param sortType
	 * @return
	 */
	@RequestMapping(value = "/product_order", method = RequestMethod.GET)
	public ResponseEntity<ResponseBean> getProductOrder(@RequestParam(value = "id", required = true) long id) {
		ResponseBean response = new ResponseBean();
		try {
			productService.getProductOrder(id, response);
			return response(response);
		} catch (Exception e) {
			logger.error("getProductOrder", e);
			response.setStatus(AppHttpStatus.INTERNAL_SERVER_ERROR);
			response.setData(null);
			response.setMessage(e.getMessage());
			return response(response);
		}
	}

	/**
	 * B05
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/product_order/receive", method = RequestMethod.PUT)
	public ResponseEntity<ResponseBean> receiveProductOrder(@RequestParam(value = "id", required = true) long id) {
		ResponseBean response = new ResponseBean();
		try {
			productService.receiveProductOrder(id, response);
			return response(response);
		} catch (Exception e) {
			logger.error("receiveProductOrder", e);
			response.setStatus(AppHttpStatus.INTERNAL_SERVER_ERROR);
			response.setData(null);
			response.setMessage(e.getMessage());
			return response(response);
		}
	}
	
	

	/**
	 * B06
	 * 
	 * @param page
	 * @param size
	 * @param sortBy
	 * @param sortType
	 * @return
	 */
	@RequestMapping(value = "/product_order/bought", method = RequestMethod.GET)
	public ResponseEntity<ResponseBean> searchBoughtProduct(
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "size", required = false, defaultValue = "10") int size,
			@RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy,
			@RequestParam(value = "sortType", required = false, defaultValue = "asc") String sortType) {
		ResponseBean response = new ResponseBean();
		try {
			productService.searchProductBought(page, size, sortBy, sortType, response);
			return response(response);
		} catch (Exception e) {
			logger.error("searchBoughtProduct", e);
			response.setStatus(AppHttpStatus.INTERNAL_SERVER_ERROR);
			response.setData(null);
			response.setMessage(e.getMessage());
			return response(response);
		}
	}

	/**
	 * B02
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/product_order", method = RequestMethod.POST)
	public ResponseEntity<ResponseBean> createProductOrder(@Valid @RequestBody TblProductOrder po) {
		ResponseBean response = new ResponseBean();
		try {
			productService.createProductOrder(po, response);
			return response(response);
		} catch (Exception e) {
			logger.error("createProductOrder", e);
			response.setStatus(AppHttpStatus.INTERNAL_SERVER_ERROR);
			response.setData(null);
			response.setMessage(e.getMessage());
			return response(response);
		}
	}

	/**
	 * B08: get product by id
	 * 
	 * @method GET
	 * @param id
	 * @return TblProduct
	 */
	@RequestMapping(value = "/product/{id}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<ResponseBean> findProduct(@PathVariable("id") long id) {
		ResponseBean response = new ResponseBean();
		try {
			productService.findProduct(id, response);
			return response(response);
		} catch (Exception e) {
			response.setStatus(AppHttpStatus.FAILED_TO_GET_DATA);
			response.setData(null);
			response.setMessage(e.getMessage());
			return response(response);
		}
	}

	/**
	 * B09: update date wish in TblProduct and create new wish in TblProductWish
	 * 
	 * @method PUT
	 * @param id
	 * @return TblProduct
	 */
	@RequestMapping(value = "/product/{id}/wish", method = RequestMethod.PUT, produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<ResponseBean> updateProductWish(@PathVariable("id") long id) {
		ResponseBean response = new ResponseBean();
		try {
			productService.updateProductWish(id, response);
			return response(response);
		} catch (Exception e) {
			response.setStatus(AppHttpStatus.FAILED_TO_GET_DATA);
			response.setData(null);
			response.setMessage(e.getMessage());
			return response(response);
		}
	}

	/**
	 * B10: create new report in TblReportViolation
	 * 
	 * @method POST
	 * @param TblReportViolation
	 * @return TblReportViolation
	 */
	@RequestMapping(value = "/report", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<ResponseBean> createReportViolation(@RequestBody TblReportViolation report) {
		ResponseBean response = new ResponseBean();
		try {
			productService.createReport(report, response);
			return response(response);
		} catch (Exception e) {
			response.setStatus(AppHttpStatus.FAILED_TO_SAVE_DATA);
			response.setData(null);
			response.setMessage(e.getMessage());
			return response(response);
		}
	}

	/**
	 * B11: create new chat or comment in TblChat
	 * 
	 * @method POST
	 * @param TblChat
	 * @return TblChat
	 */
	@RequestMapping(value = "/chat", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<ResponseBean> createChat(@RequestBody TblChat chat) {
		ResponseBean response = new ResponseBean();
		try {
			productService.createChat(chat, response);
			return response(response);
		} catch (Exception e) {
			response.setStatus(AppHttpStatus.FAILED_TO_SAVE_DATA);
			response.setData(null);
			response.setMessage(e.getMessage());
			return response(response);
		}
	}

	/**
	 * B11: find comment into 1 product
	 * 
	 * @method GET
	 * @param product_id,
	 *            type = 0
	 * @return List<TblChat>
	 */
	@RequestMapping(value = "/product/{id}/comment", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<ResponseBean> findCommentByProduct(@PathVariable("id") long id, Pageable pageable) {
		ResponseBean response = new ResponseBean();
		try {
			productService.findCommentByProduct(id, pageable, response);
			return response(response);
		} catch (Exception e) {
			response.setStatus(AppHttpStatus.FAILED_TO_GET_DATA);
			response.setData(null);
			response.setMessage(e.getMessage());
			return response(response);
		}
	}

}
