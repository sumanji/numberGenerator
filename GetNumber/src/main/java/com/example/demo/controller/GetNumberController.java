package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.business.IBusinessHelper;
import com.example.demo.entity.RandomNumberDetail;

@RestController
@RequestMapping("/api/v1")
public class GetNumberController {

	@Autowired
	private IBusinessHelper businessHelper;

	@GetMapping("/allNumbers")
	public List<RandomNumberDetail> getAllNumbers() {
		List<RandomNumberDetail> response = null;
		try {
			response = businessHelper.findAllNumber();
		} catch (Exception e) {

		}
		return response;

	}

	@GetMapping("/number")
	public RandomNumberDetail getNumber(@RequestBody String date) {

		RandomNumberDetail res = null;
		try {
			res = businessHelper.findNumber(date);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return res;

	}
}
