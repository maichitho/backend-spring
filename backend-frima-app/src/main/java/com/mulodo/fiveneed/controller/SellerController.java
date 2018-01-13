package com.mulodo.fiveneed.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mulodo.fiveneed.bean.ResponseBean;
import com.mulodo.fiveneed.constant.AppHttpStatus;
import com.mulodo.fiveneed.entity.TblProduct;
import com.mulodo.fiveneed.service.ProductService;

@RestController
@RequestMapping("/seller")
@PropertySource(value = {"classpath:application.properties"})
public class SellerController extends BaseController {
     
	 @Autowired
	 ProductService productService;
	/**
	 * API S01
	 * 
	 * @author Danhloc
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/product", method = RequestMethod.POST)
	public ResponseEntity<?> addProduct(@RequestBody TblProduct product ) {
		ResponseBean response = new ResponseBean();
		try {
			productService.productUpload(product, response);
			return response(response);
		} catch (Exception e) {
			response.setStatus(AppHttpStatus.INTERNAL_SERVER_ERROR);
			return response(response);
		}
	}
	
	/**
	 * API S02
	 * 
	 * @author Danhloc
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/product/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> productDetail(@PathVariable("id") long id,@RequestBody TblProduct product  ) {
		ResponseBean response = new ResponseBean();
		try {
		
			productService.updateProduct( id,product,response);
			return response(response);
		} catch (Exception e) {
			response.setStatus(AppHttpStatus.INTERNAL_SERVER_ERROR);
			return response(response);
		}
	}
	
	/**
	 * API S03
	 * 
	 * @author Danhloc
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/product", method = RequestMethod.GET)
	public ResponseEntity<?> getProduct(
			@RequestParam(value="page", required=false, defaultValue="0") int page,
			@RequestParam(value="size", required=false, defaultValue="10") int size,
			@RequestParam(value="sortBy",required=false,defaultValue="id") String sortBy ,
			@RequestParam(value="sortType",required=false,defaultValue="asc") String sortType
			) {
		ResponseBean response = new ResponseBean();
		try {
			productService.getProduct(page, size, sortBy, sortType,response);
			return response(response);
		} catch (Exception e) {
			response.setStatus(AppHttpStatus.INTERNAL_SERVER_ERROR);
			return response(response);
		}
	}
	
	
	/**
	 * API S04
	 * 
	 * @author Danhloc
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/product_order", method = RequestMethod.GET)
	public ResponseEntity<?> getProductOrderSeller(
			@RequestParam(value="page", required=false, defaultValue="0")int page,
			@RequestParam(value="size", required=false, defaultValue="10")int size,
			@RequestParam(value="sortBy", required=false, defaultValue="id")String sortBy,
			@RequestParam(value="sortType", required=false, defaultValue="asc")String sortType
			
			) 
	{
		ResponseBean response = new ResponseBean();
		try {
			
			 productService.getProductOrderSeller(page, size, sortBy, sortType, response);
			return response(response);
		} catch (Exception e) {
			response.setStatus(AppHttpStatus.INTERNAL_SERVER_ERROR);
			return response(response);
		}
	}
	
	/**
	 * API S05
	 * 
	 * @author Danhloc
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getProductDetail(@PathVariable("id") long id ) {
		ResponseBean response = new ResponseBean();
		try {
			productService.productDetail(id, response);
			return response(response);
		} catch (Exception e) {
			response.setStatus(AppHttpStatus.INTERNAL_SERVER_ERROR);
			return response(response);
		}
	}
	
	/**
	 * API S06
	 * 
	 * @author Danhloc
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/product/product_order/{id}/deliver", method = RequestMethod.PUT)
	public ResponseEntity<?> productOrderDeliver(@PathVariable("id") long id ) {
		ResponseBean response = new ResponseBean();
		try {
			productService.productOrderDeliver(id, response);
			return response(response);
		} catch (Exception e) {
			response.setStatus(AppHttpStatus.INTERNAL_SERVER_ERROR);
			return response(response);
		}
	}
	
	
	/**
	 * API S07
	 * 
	 * @author Danhloc
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/product/myseller", method = RequestMethod.GET)
	public ResponseEntity<?> productMyseller(
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "size", required = false, defaultValue = "10") int size,
			@RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy,
			@RequestParam(value = "sortType", required = false, defaultValue = "asc") String sortType) {
		ResponseBean response = new ResponseBean();
		try {
			productService.getAllMyseller(page, size, sortBy, sortType,response);
			return response(response);
		} catch (Exception e) {
			response.setStatus(AppHttpStatus.INTERNAL_SERVER_ERROR);
			return response(response);
		}
	}
	
}
