package com.golomt.event.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping(value = "/index")
    public String index() {
        return "index";
    }

    @GetMapping(value = "/**/{path:[^.]*}")
    public String any() {
        return "forward:/";
    }
	
}
