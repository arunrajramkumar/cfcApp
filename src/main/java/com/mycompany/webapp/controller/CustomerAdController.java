package com.mycompany.webapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.webapp.model.CFCUser;
import com.mycompany.webapp.model.CFCUserType;
import com.mycompany.webapp.model.CustomerAd;
import com.mycompany.webapp.service.CFCUserManager;
import com.mycompany.webapp.service.CustomerAdManager;

@Controller
@RequestMapping("/user/postCustomerAds*")
public class CustomerAdController  {

	 @Autowired
	 private CustomerAdManager customerAdManager;
	    
	 private Logger log = Logger.getLogger(SearchController.class);
	    

		public CustomerAdController() {
	      //  setCancelView("redirect:login");
	     //   setSuccessView("redirect:mainMenu");
	    }
	
	@ModelAttribute
	@RequestMapping(method = RequestMethod.GET)
	public CustomerAd showForm() {
		
		if (log.isDebugEnabled()) {
			log.debug("SCustomerAdController GET showForm called.");
		}
		
		return new CustomerAd();
	}
		
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView onSubmit(CustomerAd user, BindingResult errors, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		if (log.isDebugEnabled()) {
			log.debug("SCustomerAdController POST called.");
		}
		
		
		 customerAdManager.save(user);
		if (log.isDebugEnabled()) {
			//log.debug("Search Results -> "+usersLst.size());
		}
		
		return new ModelAndView("user/postCustomerAds");
		
	}
	
	@ModelAttribute("userTypeList")
	public CFCUserType[] populateUserTypeList() {
		return CFCUserType.values();
	}
	
	
}
