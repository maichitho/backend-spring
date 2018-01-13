package com.mulodo.fiveneed.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mulodo.fiveneed.bean.ResponseBean;
import com.mulodo.fiveneed.bean.response.ProductResponseBean;
import com.mulodo.fiveneed.bean.response.SaleInfoResponseBean;
import com.mulodo.fiveneed.common.util.CommonUtil;
import com.mulodo.fiveneed.common.util.StringUtils;
import com.mulodo.fiveneed.constant.AppHttpStatus;
import com.mulodo.fiveneed.constant.Constants;
import com.mulodo.fiveneed.entity.MstCategory;
import com.mulodo.fiveneed.entity.MstDataType;
import com.mulodo.fiveneed.entity.MstUser;
import com.mulodo.fiveneed.entity.TblChat;
import com.mulodo.fiveneed.entity.TblProduct;
import com.mulodo.fiveneed.entity.TblProductImage;
import com.mulodo.fiveneed.entity.TblProductOrder;
import com.mulodo.fiveneed.entity.TblProductWish;
import com.mulodo.fiveneed.entity.TblReportViolation;
import com.mulodo.fiveneed.entity.TblRequestPayment;
import com.mulodo.fiveneed.entity.TblTodo;
import com.mulodo.fiveneed.repository.CategoryRepository;
import com.mulodo.fiveneed.repository.ChatRepository;
import com.mulodo.fiveneed.repository.DataTypeRepository;
import com.mulodo.fiveneed.repository.PaymentRepositoryJPA;
import com.mulodo.fiveneed.repository.ProductImageRepository;
import com.mulodo.fiveneed.repository.ProductOrderRepository;
import com.mulodo.fiveneed.repository.ProductRepository;
import com.mulodo.fiveneed.repository.ProductWishRepository;
import com.mulodo.fiveneed.repository.ReportViolationRepository;
import com.mulodo.fiveneed.repository.TodoRepository;
import com.mulodo.fiveneed.repository.UserRepository;

@Service("ProductService")
@Transactional(rollbackFor = Exception.class)
public class ProductService extends BaseService {

	@Autowired
	DataSource dataSource;

	@Autowired
	UserRepository userDao;

	@Autowired
	ProductRepository productDAO;

	@Autowired
	ProductImageRepository productImageDAO;

	@Autowired
	ProductOrderRepository productOrderRepo;

	@Autowired
	TodoRepository todoRepo;

	@Autowired
	ProductWishRepository productWishRepo;

	@Autowired
	PaymentRepositoryJPA paymentRepo;

	@Autowired
	ReportViolationRepository reportRepository;

	@Autowired
	DataTypeRepository dataTypeRepository;

	@Autowired
	ChatRepository chatRepository;
	
	@Autowired
	CategoryRepository categoryRepository;

	private TblProduct product;

	public void getSaleInfo(ResponseBean response) {
		MstUser user = checkTokenInSession();
		if (user == null) {
			response.setStatus(AppHttpStatus.AUTH_FAILED);
			return;
		}
		SaleInfoResponseBean data = new SaleInfoResponseBean();
		// Total revenue
		BigDecimal totalRevenue = productOrderRepo.totalRevenueByUser(TblProductOrder.STATUS_4_WAIT_ADMIN_PAY_SELLER,
				user.getId());
		// Total need to request
		BigDecimal totalNeedToRequest = productOrderRepo
				.totalNeedRequestByUser(TblProductOrder.STATUS_4_WAIT_ADMIN_PAY_SELLER, user.getId());
		// Total Requested
		BigDecimal totalRequested = paymentRepo.sumBuyStatusAndUser(TblRequestPayment.STATUS_NOT_PAID, user.getId());

		data.setRevenue(totalRevenue);
		data.setUnrequest(totalNeedToRequest);
		data.setRequested(totalRequested);
		response.setData(data);
	}

	public void searchHistory(int page, int size, String sortBy, String sortType, ResponseBean response) {
		MstUser user = checkTokenInSession();
		if (user == null) {
			response.setStatus(AppHttpStatus.AUTH_FAILED);
			return;
		}
		String sortByProperty = StringUtils.snakeCaseToCamelCase(sortBy);
		Sort.Order order = new Sort.Order(
				Constants.ORDER_ASC.equalsIgnoreCase(sortType) ? Direction.ASC : Direction.DESC, sortByProperty)
						.ignoreCase();

		Page<TblProductOrder> resultPage = productOrderRepo.findByUserSellIdAndStatusGreaterThan(user.getId(),
				TblProductOrder.STATUS_4_WAIT_ADMIN_PAY_SELLER, new PageRequest(page, size, new Sort(order)));
		response.setData(resultPage);

	}

	public void searchNeedRequestPayment(int page, int size, String sortBy, String sortType, ResponseBean response) {
		MstUser user = checkTokenInSession();
		if (user == null) {
			response.setStatus(AppHttpStatus.AUTH_FAILED);
			return;
		}
		String sortByProperty = StringUtils.snakeCaseToCamelCase(sortBy);
		Sort.Order order = new Sort.Order(
				Constants.ORDER_ASC.equalsIgnoreCase(sortType) ? Direction.ASC : Direction.DESC, sortByProperty)
						.ignoreCase();

		Page<TblProductOrder> resultPage = productOrderRepo
				.findByUserSellIdAndStatusGreaterThanAndRemainPaymentGreaterThan(user.getId(),
						TblProductOrder.STATUS_4_WAIT_ADMIN_PAY_SELLER, BigDecimal.ZERO,
						new PageRequest(page, size, new Sort(order)));
		response.setData(resultPage);

	}

	public void searchRequestPayment(int page, int size, String sortBy, String sortType, ResponseBean response) {
		MstUser user = checkTokenInSession();
		if (user == null) {
			response.setStatus(AppHttpStatus.AUTH_FAILED);
			return;
		}
		String sortByProperty = StringUtils.snakeCaseToCamelCase(sortBy);
		Sort.Order order = new Sort.Order(
				Constants.ORDER_ASC.equalsIgnoreCase(sortType) ? Direction.ASC : Direction.DESC, sortByProperty)
						.ignoreCase();

		Page<TblRequestPayment> resultPage = paymentRepo.findByCreatedBy(user.getId(),
				new PageRequest(page, size, new Sort(order)));
		response.setData(resultPage);

	}

	public void searchWish(int page, int size, String sortBy, String sortType, ResponseBean response) {
		MstUser user = checkTokenInSession();
		if (user == null) {
			response.setStatus(AppHttpStatus.AUTH_FAILED);
			return;
		}
		String sortByProperty = StringUtils.snakeCaseToCamelCase(sortBy);
		Sort.Order order = new Sort.Order(
				Constants.ORDER_ASC.equalsIgnoreCase(sortType) ? Direction.ASC : Direction.DESC, sortByProperty)
						.ignoreCase();
		Page<TblProductWish> resultPage = productWishRepo.findByUserBuyId(user.getId(),
				new PageRequest(page, size, new Sort(order)));
		response.setData(resultPage);

	}

	public void searchProductBuying(int page, int size, String sortBy, String sortType, ResponseBean response) {
		MstUser user = checkTokenInSession();
		if (user == null) {
			response.setStatus(AppHttpStatus.AUTH_FAILED);
			return;
		}
		String sortByProperty = StringUtils.snakeCaseToCamelCase(sortBy);
		Sort.Order order = new Sort.Order(
				Constants.ORDER_ASC.equalsIgnoreCase(sortType) ? Direction.ASC : Direction.DESC, sortByProperty)
						.ignoreCase();
		Page<TblProductOrder> resultPage = productOrderRepo.findByUserBuyIdAndStatusBetween(user.getId(),
				TblProductOrder.STATUS_1_WAIT_BUYER_PAY, TblProductOrder.STATUS_3_WAIT_BUYER_RECEIVE,
				new PageRequest(page, size, new Sort(order)));
		response.setData(resultPage);
	}

	public void getProductOrder(long id, ResponseBean response) {
		MstUser user = checkTokenInSession();
		if (user == null) {
			response.setStatus(AppHttpStatus.AUTH_FAILED);
			return;
		}
		TblProductOrder or = productOrderRepo.findOne(id);
		response.setData(or);
	}

	public void receiveProductOrder(long id, ResponseBean response) {
		MstUser user = checkTokenInSession();
		if (user == null) {
			response.setStatus(AppHttpStatus.AUTH_FAILED);
			return;
		}
		TblProductOrder or = productOrderRepo.findOne(id);
		or.setStatus(TblProductOrder.STATUS_4_WAIT_ADMIN_PAY_SELLER);
		or.setReceivedAt(CommonUtil.getCurrentTime());
		or.setRemainPayment(or.getPrice());
		productOrderRepo.save(or);

		// TODO: tạo todo là đã nhận, hãy đánh giá đi
		TblTodo todo = new TblTodo();
		todo.setTodoType(TblTodo.TODO_TYPE_5_RATE);
		todo.setCreatedAt(CommonUtil.getCurrentTime());
		todo.setCreatedBy(user.getId());
		todo.setFromUserId(user.getId());
		todo.setFromUserName(user.getUserName());
		todo.setImageUrl(or.getProductImageUrl());
		todo.setIsRead(false);
		todo.setProductId(or.getProductId());
		todo.setProductName(or.getProductName());
		todo.setReceiveUserId(or.getUserSellId());
		todo.setTodoContent(TblTodo.TODO_CONTENT_5_RATE);

		todoRepo.save(todo);

		response.setData(or);

	}

	/**
	 * API S06
	 * 
	 * @author thomc
	 * @param id
	 * @param response
	 */
	public void productOrderDeliver(long id, ResponseBean response) {
		MstUser user = checkTokenInSession();
		if (user == null) {
			response.setStatus(AppHttpStatus.AUTH_FAILED);
			return;
		}
		TblProductOrder or = productOrderRepo.findOne(id);
		or.setStatus(TblProductOrder.STATUS_3_WAIT_BUYER_RECEIVE);
		or.setDeliverAt(CommonUtil.getCurrentTime());
		productOrderRepo.save(or);

		// TODO: tạo todo là đã gửi, hãy nhận đi
		TblTodo todo = new TblTodo();
		todo.setTodoType(TblTodo.TODO_TYPE_2_RECEIVE);
		todo.setCreatedAt(CommonUtil.getCurrentTime());
		todo.setCreatedBy(user.getId());
		todo.setFromUserId(user.getId());
		todo.setFromUserName(user.getUserName());
		todo.setImageUrl(or.getProductImageUrl());
		todo.setIsRead(false);
		todo.setProductId(or.getProductId());
		todo.setProductName(or.getProductName());
		todo.setReceiveUserId(or.getUserBuyId());
		todo.setTodoContent(TblTodo.TODO_CONTENT_2_RECEIVE);

		todoRepo.save(todo);

		response.setData(or);

	}

	public void searchProductBought(int page, int size, String sortBy, String sortType, ResponseBean response) {
		MstUser user = checkTokenInSession();
		if (user == null) {
			response.setStatus(AppHttpStatus.AUTH_FAILED);
			return;
		}
		String sortByProperty = StringUtils.snakeCaseToCamelCase(sortBy);
		Sort.Order order = new Sort.Order(
				Constants.ORDER_ASC.equalsIgnoreCase(sortType) ? Direction.ASC : Direction.DESC, sortByProperty)
						.ignoreCase();
		Page<TblProductOrder> resultPage = productOrderRepo.findByUserSellIdAndStatusGreaterThan(user.getId(),
				TblProductOrder.STATUS_4_WAIT_ADMIN_PAY_SELLER, new PageRequest(page, size, new Sort(order)));
		response.setData(resultPage);

	}

	public void createProductOrder(TblProductOrder po, ResponseBean response) {
		MstUser user = checkTokenInSession();
		if (user == null) {
			response.setStatus(AppHttpStatus.AUTH_FAILED);
			return;
		}

		po.setCreatedAt(CommonUtil.getCurrentTime());
		po.setCreatedBy(user.getId());
		po.setIsDeleted(false);
		po.setUserBuyId(user.getId());
		po.setStatus(TblProductOrder.STATUS_1_WAIT_BUYER_PAY);

		productOrderRepo.save(po);

	}

	/*
	 * API S01
	 * 
	 * @author Danhloc
	 * 
	 * @param request
	 * 
	 * @return
	 */
	public void productUpload(TblProduct product, ResponseBean response) {
		ProductResponseBean bean = new ProductResponseBean();
		// check authen
		MstUser user = checkTokenInSession();
		if (user == null) {
			response.setStatus(AppHttpStatus.AUTH_FAILED);
			return;
		}
		product.setId(null);
		product.setName(product.getName());
		product.setCategoryId(product.getCategoryId());
		product.setCreatedAt(CommonUtil.getCurrentTime());
		product.setCreatedBy(user.getId());
		product.setDescription(product.getDescription());
		product.setVolumnId(product.getVolumnId());
		product.setVolumnValue(product.getVolumnValue());
		product.setStoreTypeId(product.getStoreTypeId());
		product.setProductStatusId(product.getProductStatusId());
		product.setTags(product.getTags());
		product.setShipFeeTypeId(product.getShipFeeTypeId());
		product.setShipMethodId(product.getShipMethodId());
		product.setPrefectureId(product.getPrefectureId());
		product.setDeliverDayId(product.getDeliverDayId());
		product.setPrice(product.getPrice());
		product.setFee(product.getFee());
		product.setProfit(product.getProfit());
		product.setProductImageUrl(product.getProductImageUrl());
		product.setStatus(product.getStatus());
		product.setProductType(product.getProductType());
       
		// Save product to get id
		productDAO.save(product);
		// Save image url
		List<String> imageUrl = product.getImageUrlList();
		List<TblProductImage> imageList = new LinkedList<>();
		int countImage = 0;
		for (String url : imageUrl) {
			TblProductImage image = new TblProductImage();
			image.setImageUrl(url);
			image.setProductId(product.getId());
			image.setImageOrder(countImage);
			countImage++;
			imageList.add(image);
		}
		 
		
		// save productimage
		
		productImageDAO.save(imageList);
		bean.setData(product);
	
		
	}

	/*
	 * API S02
	 * 
	 * @author Danhloc
	 * 
	 * @param request
	 * 
	 * @return
	 */

	public void updateProduct(long product_id,TblProduct tblProduct, ResponseBean response) {
		ProductResponseBean bean = new ProductResponseBean();

		MstUser mstUser = checkTokenInSession();
		if (mstUser == null) {
			response.setStatus(AppHttpStatus.AUTH_FAILED);
			return;
		}

      TblProduct product = productDAO.findOne(product_id);
		if (product == null) {
			response.setStatus(AppHttpStatus.FAILED_TO_GET_DATA);
			return;
		}
	
		product.setName(tblProduct.getName());
		product.setCategoryId(tblProduct.getCategoryId());
		product.setUpdatedAt(CommonUtil.getCurrentTime());
		product.setUpdatedBy(mstUser.getId());
		product.setDescription(tblProduct.getDescription());
		product.setVolumnId(tblProduct.getVolumnId());
		product.setVolumnValue(tblProduct.getVolumnValue());
		product.setStoreTypeId(tblProduct.getStoreTypeId());
		product.setProductStatusId(tblProduct.getProductStatusId());
		product.setTags(tblProduct.getTags());
		product.setShipFeeTypeId(tblProduct.getShipFeeTypeId());
		product.setShipMethodId(tblProduct.getShipMethodId());
		product.setPrefectureId(tblProduct.getPrefectureId());
		product.setDeliverDayId(tblProduct.getDeliverDayId());
		product.setPrice(tblProduct.getPrice());
		product.setFee(tblProduct.getFee());
		product.setProfit(tblProduct.getProfit());
		product.setProductImageUrl(tblProduct.getProductImageUrl());
		product.setStatus(tblProduct.getStatus());
		product.setProductType(tblProduct.getProductType());
			
			// Save product to get id
			productDAO.save(product);
			
			//delete img_url_string
			List<TblProductImage> findimg = productImageDAO.findByProductId(product_id);
			productImageDAO.delete(findimg);
			// Save image url


			List<String> imageUrl = tblProduct.getImageUrlList();
			List<TblProductImage> imageList = new LinkedList<>();
			int countImage = 0;
			for (String url : imageUrl) {
				TblProductImage image = new TblProductImage();
				image.setImageUrl(url);
				image.setProductId(product.getId());
				image.setImageOrder(countImage);
				countImage++;
				imageList.add(image);
			}
			// save productimage
			productImageDAO.save(imageList);
	 
		
		bean.setData(product);
	}

	/*
	 * API S03
	 * 
	 * @author Danhloc
	 * 
	 * @param request
	 * 
	 * @return
	 */

	public void getProduct(int page, int size, String sortBy, String sortType, ResponseBean response) {
		MstUser user = checkTokenInSession();
		if (user == null) {
			response.setStatus(AppHttpStatus.AUTH_FAILED);
			return;
		}
		String sortByProperty = StringUtils.snakeCaseToCamelCase(sortBy);
		Sort.Order order = new Sort.Order(
				Constants.ORDER_ASC.equalsIgnoreCase(sortType) ? Direction.ASC : Direction.DESC, sortByProperty)
						.ignoreCase();
		Page<TblProduct> listproductSeller = productDAO.findByStatusBetweenAndCreatedBy(TblProduct.STATUS_PUBLISHED,TblProduct.STATUS_DRAFT,
				user.getId(), new PageRequest(page, size, new Sort(order)));
		response.setData(listproductSeller);

	}

	/*
	 * API S04
	 * 
	 * @author Danhloc
	 * 
	 * @param request
	 * 
	 * @return
	 */

	public void getProductOrderSeller(int page, int size, String sortBy, String sortType, ResponseBean response) {
		MstUser user = checkTokenInSession();
		if (user == null) {
			response.setStatus(AppHttpStatus.AUTH_FAILED);
			return;
		}
		String sortByProperty = StringUtils.snakeCaseToCamelCase(sortBy);
		Sort.Order order = new Sort.Order(
				Constants.ORDER_ASC.equalsIgnoreCase(sortType) ? Direction.ASC : Direction.DESC, sortByProperty)
						.ignoreCase();
		Page<TblProductOrder> productListOrders = productOrderRepo.findByStatusAndUserSellId(
				TblProductOrder.STATUS_5_COMPLETED, user.getId(), new PageRequest(page, size, new Sort(order)));

		response.setData(productListOrders);

	}

	/*
	 * API S05
	 * 
	 * @author Danhloc
	 * 
	 * @param request
	 * 
	 * @return
	 */
	public void productDetail(long id, ResponseBean response) {
		ProductResponseBean bean = new ProductResponseBean();
		MstUser user = checkTokenInSession();
		if (user == null) {
			response.setStatus(AppHttpStatus.AUTH_FAILED);
			return;
		}
		TblProduct product = productDAO
				.findByStatusBetweenAndCreatedByAndId(TblProduct.STATUS_PUBLISHED,TblProduct.STATUS_DRAFT,user.getId(), id);
		
		List<String> imgString= new ArrayList();
		List<TblProductImage> listimgproduct= productImageDAO.findByProductId(product.getId());
		
		for (TblProductImage imgproduct : listimgproduct) {
			
			imgString.add(imgproduct.getImageUrl());
			
		}
		
		bean.setListString_img(imgString);
		bean.setProduct(product);
	    response.setData(bean);
	
		
		
		
		
		
		
	}

	/*
	 * API S07
	 * 
	 * @author Danhloc
	 * 
	 * @param request
	 * 
	 * @return
	 */
	public void getAllMyseller(int page, int size, String sortBy, String sortType, ResponseBean response) {
		MstUser user = checkTokenInSession();
		if (user == null) {
			response.setStatus(AppHttpStatus.AUTH_FAILED);
			return;
		}
		String sortByProperty = StringUtils.snakeCaseToCamelCase(sortBy);
		Sort.Order order = new Sort.Order(
				Constants.ORDER_ASC.equalsIgnoreCase(sortType) ? Direction.ASC : Direction.DESC, sortByProperty)
						.ignoreCase();
		Page<TblProduct> listMyseller = productDAO.findByProductTypeAndCreatedBy(TblProduct.PRODUCT_TYPE_COLLECTION,
				user.getId(), new PageRequest(page, size, new Sort(order)));

		response.setData(listMyseller);
	}

	public void findAllProduct(int page, int size, String sortBy, String sortType, ResponseBean response) {
		String sortByProperty = StringUtils.snakeCaseToCamelCase(sortBy);
		Sort.Order order = new Sort.Order(
				Constants.ORDER_ASC.equalsIgnoreCase(sortType) ? Direction.ASC : Direction.DESC, sortByProperty)
						.ignoreCase();

		Page<TblProduct> product = productDAO.findAll(new PageRequest(page, size, new Sort(order)));
		response.setData(product);
	}

	public void findProductByCategory(String category, int page, int size, String sortBy, String sortType,
			ResponseBean response) {
		String sortByProperty = StringUtils.snakeCaseToCamelCase(sortBy);
		Sort.Order order = new Sort.Order(
				Constants.ORDER_ASC.equalsIgnoreCase(sortType) ? Direction.ASC : Direction.DESC, sortByProperty)
						.ignoreCase();
		Page<TblProduct> product = productDAO.findProductByCategory(category, new PageRequest(page, size, new Sort(order)));
		response.setData(product);

	}

	public void findProduct(long id, ResponseBean response) {
		MstUser user = checkTokenInSession();
		if (user == null) {
			response.setStatus(AppHttpStatus.AUTH_FAILED);
			return;
		}
		TblProduct product = productDAO.findOne(id);
		if (product == null) {
			response.setStatus(AppHttpStatus.FAILED_TO_GET_DATA);
		} else {
			response.setData(product);
		}
	}

	public void updateProductWish(long id, ResponseBean response) {
		MstUser user = checkTokenInSession();
		if (user == null) {
			response.setStatus(AppHttpStatus.AUTH_FAILED);
			return;
		}
		TblProductWish wish = new TblProductWish();
		TblProduct product = productDAO.findOne(id);
		TblProductWish productWish = productWishRepo.findByProductIdAndUserBuyId(id, user.getId());
		if (productWish == null) {
			wish.setPrice(product.getPrice());
			wish.setImageUrl(product.getProductImageUrl());
			wish.setProductName(product.getName());
			wish.setProductId(id);
			wish.setUserBuyId(user.getId());
			productWishRepo.save(wish);
		}

	}

	public void createReport(TblReportViolation report, ResponseBean response) {
		MstUser user = checkTokenInSession();
		if (user == null) {
			response.setStatus(AppHttpStatus.AUTH_FAILED);
			return;
		}
		TblProduct product = productDAO.findOne(report.getProductId());
		// convert long to int
		Integer dataTypeId = report.getDataTypeId().intValue();
		MstDataType dataType = dataTypeRepository.findOne(dataTypeId);
		if (product != null && dataType != null) {
			reportRepository.save(report);
		} else {
			response.setStatus(AppHttpStatus.FAILED_TO_SAVE_DATA);
		}
	}

	public void createChat(TblChat chat, ResponseBean response) {
		MstUser user = checkTokenInSession();
		if (user == null) {
			response.setStatus(AppHttpStatus.AUTH_FAILED);
			return;
		}
		TblProduct checkProduct = productDAO.findOne(chat.getProductId());
		if (checkProduct != null && user != null) {
			chat.setUserId(user.getId());
			chatRepository.save(chat);
		}
	}

	public void findCommentByProduct(long id, Pageable pageable, ResponseBean response) {
		int type = 0;
		TblProduct product = productDAO.findOne(id);
		if (product != null) {
			chatRepository.findByProductIdAndTypeOrderByCreatedAtDesc(id, type, pageable);
		}

	}

}
