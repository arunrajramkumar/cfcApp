/**
 * 
 */
package com.mycompany.webapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.appfuse.Constants;
import org.appfuse.dao.SearchException;
import org.appfuse.model.Role;
import org.appfuse.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.webapp.model.CFCUser;
import com.mycompany.webapp.model.CFCUserType;
import com.mycompany.webapp.service.CFCUserManager;


/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/user/search*")
public class SearchController {

	 private Logger log = Logger.getLogger(SearchController.class);
	 
	 private CFCUserManager userManager = null;

	    @Autowired
	    public void setUserManager(CFCUserManager userManager) {
	        this.userManager = userManager;
	    }

	/**
	 * 
	 */
	public SearchController() {
		// TODO Auto-generated constructor stub
	}

	/*@RequestMapping(method = RequestMethod.GET)
    public ModelAndView handleRequest(@RequestParam(required = false, value = "q") String query) throws Exception {
        Model model = new ExtendedModelMap();
       
        try {
            model.addAttribute(Constants.USER_LIST, userManager.search(query));
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(userManager.getUsers());
        }
        return new ModelAndView("user/search", model.asMap());
    }*/
	
	 @ModelAttribute
	    @RequestMapping(method = RequestMethod.GET)
	    protected CFCUser showForm(final HttpServletRequest request, final HttpServletResponse response)
	            throws Exception {
	       
		 return new CFCUser();
	    }
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView onSubmit(CFCUser user, BindingResult errors, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		if (log.isDebugEnabled()) {
			log.debug("Search User form submit called.");
		}
		
      /*  request.setAttribute("searchResults", "true");
		ModelAndView mv = new ModelAndView("usermgmt/searchUser");

		
		String paging = (String) request.getParameter("pagingSearchOrders");
		
		if (paging != null)
		{
			return mv;
		}
*/		
	
		
		List<CFCUser> usersLst = userManager.searchUser(user);
		if (log.isDebugEnabled()) {
			log.debug("Search Results -> "+usersLst.size());
		}
		HttpSession session = request.getSession();
		session.removeAttribute("usersLst");
		session.setAttribute("usersLst", usersLst);
		return new ModelAndView("user/search");
		
	}
	
	@ModelAttribute("userTypeList")
	public CFCUserType[] populateUserTypeList() {
		return CFCUserType.values();
	}

}
