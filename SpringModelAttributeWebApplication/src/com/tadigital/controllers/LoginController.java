package com.tadigital.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tadigital.entity.Vendor;
import com.tadigital.service.VendorService;

@Controller
public class LoginController {
	
	private VendorService vs;
	
	@Autowired
	public LoginController(VendorService vs) {
		this.vs = vs;
	}


	@RequestMapping(value="/vendorlogin", method = RequestMethod.POST)
	public String loginTask(@ModelAttribute Vendor vendor,HttpServletRequest req) {
		boolean b = vs.loginVendor(vendor);
		
		if(b) {
			return "loginSuccess.jsp";
		}
		return "loginFailure.jsp";
	}
}
