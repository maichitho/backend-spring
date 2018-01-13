package com.mulodo.fiveneed.bean.response;

import java.util.List;

import com.mulodo.fiveneed.bean.ResponseBean;
import com.mulodo.fiveneed.entity.TblProduct;
import com.mulodo.fiveneed.entity.TblProductImage;

public class ProductResponseBean extends ResponseBean {

	private TblProduct product;

	private List<TblProduct> list_product;

	private TblProductImage list_img;
    
	private List<String> listString_img;
	
	public List<String> getListString_img() {
		return listString_img;
	}

	public void setListString_img(List<String> listString_img) {
		this.listString_img = listString_img;
	}

	public TblProduct getProduct() {
		return product;
	}

	public void setProduct(TblProduct product) {
		this.product = product;
	}

	public List<TblProduct> getList_product() {
		return list_product;
	}

	public void setList_product(List<TblProduct> list_product) {
		this.list_product = list_product;
	}

	
	public TblProductImage getList_img() {
		return list_img;
	}

	public void setList_img(TblProductImage list_img) {
		this.list_img = list_img;
	}

}
