package com.sns.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sns.dao.SnsMapper;

@Controller
	public class TestController {
	@Autowired
	private SnsMapper snsMapper;
		@ResponseBody
		@RequestMapping("/test1")
		public String test1() {
			return "Hello World!";
		}
	
	@ResponseBody
	@RequestMapping("/test2")
	public Map<String,Object> test2(){
		Map<String,Object> map = new HashMap<>();
		map.put("a",111);
		map.put("b",123);
		map.put("c",456);
		return map;
	}
	
	@RequestMapping("/test3")
	public String test3() {
		return "test/test1";
	}
	
	@ResponseBody
	@RequestMapping("/test4")
	public List<Map<String,Object>> test4(){
		return snsMapper.selectSnsList();
	}
}
