package co.siten.myvtg.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseStatus;

import co.siten.myvtg.bean.RequestBean;
import co.siten.myvtg.dao.UserDao;
import co.siten.myvtg.model.myvtg.User;
import co.siten.myvtg.util.Constants;
import co.siten.myvtg.validation.ValidationError;
import co.siten.myvtg.validation.ValidationErrorBuilder;

public class BaseController {
	
	@Autowired
	UserDao userDao;

	public BaseController() {

	}

	public String getCurrentUserId() {
		String userName = getPrincipal();
		User user = userDao.findOneByUsername(userName);
		if (user == null)
			return "";
		return user.getId();
	}

	public String getPrincipal() {
		String userName = null;
		if (SecurityContextHolder.getContext().getAuthentication() == null) {
			userName = "";
		} else {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			if (principal instanceof UserDetails) {
				userName = ((UserDetails) principal).getUsername();
			}
		}
		return userName;
	}

	protected boolean validateRequest(RequestBean request) {
		String apiKey = request.getApiKey();
		return (Constants.API_KEY.equalsIgnoreCase(apiKey));
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ValidationError handleException(MethodArgumentNotValidException exception) {
		return createValidationError(exception);
	}

	private ValidationError createValidationError(MethodArgumentNotValidException e) {

		return ValidationErrorBuilder.fromBindingErrors(e.getBindingResult());
	}

}
