package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.ResponseEntity.ResponseBean;
import com.example.demo.business.IBusinessHelper;
import com.example.demo.entity.LogoStorage;
import com.example.demo.entity.RandomNumberDetail;

@RestController
@RequestMapping("/api/v1/reader")
@CrossOrigin(value = "*")
public class GetNumberController {

	@Autowired
	private IBusinessHelper businessHelper;

	@GetMapping("/allNumbers")
	public ResponseBean getAllNumbers(@RequestParam("pageNum")Integer pageNum,@RequestParam("pageSize")Integer pageSize) {
		ResponseBean response = null;
		try {
			response = businessHelper.findAllNumber(pageNum, pageSize);
		} catch (Exception e) {
			response = new ResponseBean();
		}
		return response;

	}

	@GetMapping("/number/{date}")
	public RandomNumberDetail getNumber(@PathVariable("date") String date) {

		RandomNumberDetail res = null;
		try {
			res = businessHelper.findNumber(date);
		} catch (Exception e) {
			// TODO: handle exception
			res = new RandomNumberDetail();
		}
		return res;

	}
	
	@GetMapping("/logo/{logoId}")
	public LogoStorage logoDetails(@PathVariable("logoId") Integer logoId) {
		return businessHelper.getLogoDetails(logoId);
		
	}
	
	@GetMapping("/allLogos")
	public List<LogoStorage> alllogoDetails(HttpServletRequest request) {
		return businessHelper.getAllLogos();
		
	}
	
}
