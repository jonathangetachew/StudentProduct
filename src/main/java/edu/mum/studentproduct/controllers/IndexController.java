package edu.mum.studentproduct.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Jonathan on 10/16/2019.
 */

@Controller
public class IndexController {

	@GetMapping("/")
	public String index() {
		return "index";
	}
}
