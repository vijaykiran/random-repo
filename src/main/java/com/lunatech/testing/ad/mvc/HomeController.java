package com.lunatech.testing.ad.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping("/home")
    public String renderHomePage(Model model) {
        return "index";
    }

}
