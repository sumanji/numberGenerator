package com.example.demo.ResponseEntity;

import java.util.List;

import com.example.demo.entity.RandomNumberDetail;

public class ResponseBean {
	int totalcount;
	List<RandomNumberDetail> results;

	public int getTotalcount() {
		return totalcount;
	}

	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}

	public List<RandomNumberDetail> getResults() {
		return results;
	}

	public void setResults(List<RandomNumberDetail> results) {
		this.results = results;
	}

}
