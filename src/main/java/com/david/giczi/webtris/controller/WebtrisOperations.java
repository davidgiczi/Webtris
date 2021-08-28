package com.david.giczi.webtris.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/webtris")
public class WebtrisOperations {

	
	@RequestMapping("")
	public String goLoginPage() {
		return "login";
	}
	
	
	
}
