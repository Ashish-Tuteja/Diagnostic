package com.auction.pro.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController {
	@RequestMapping(value = "/404", method = RequestMethod.GET)
	public ModelAndView accessDenied(HttpServletRequest request) {
		request.getSession(false).invalidate();
		return new ModelAndView("404");
	}
}
