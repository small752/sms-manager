package com.tardis.sms.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tardis.sms.service.HelloWorldService;

@RestController
@RequestMapping(value = "/ss")
public class HelloWorldController {

	@Autowired
	private HelloWorldService helloService;
	
	@RequestMapping("/hello")
    public Map<String, Object> index() {
		System.out.println("2");
		String str = helloService.sayHelloToDb();
		Map<String, Object> res = new HashMap<>();
		res.put("data", str);
        return res;
    }
	
	@RequestMapping("/h")
    public Map<String, Object> h() {
		System.out.println("2");
		Map<String, Object> res = new HashMap<>();
		res.put("data", "successssssssssssssss");
        return res;
    }
}
