package com.generator.pojo;

public class ResponseBean extends BaseBean {

	String uniqueIdentifier;
	Object responseData;
	Integer totalCount;

	public String getUniqueIdentifier() {
		return uniqueIdentifier;
	}

	public void setUniqueIdentifier(String uniqueIdentifier) {
		this.uniqueIdentifier = uniqueIdentifier;
	}

	public Object getResponseData() {
		return responseData;
	}

	public void setResponseData(Object data) {
		this.responseData = data;
	}

	public Integer  getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	
}
