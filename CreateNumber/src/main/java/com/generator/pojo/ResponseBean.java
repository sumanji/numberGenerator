package com.generator.pojo;

public class ResponseBean extends BaseBean {

	String uniqueIdentifier;
	Object responseData;

	public String getUniqueIdentifier() {
		return uniqueIdentifier;
	}

	public void setUniqueIdentifier(String uniqueIdentifier) {
		this.uniqueIdentifier = uniqueIdentifier;
	}

	public Object getData() {
		return responseData;
	}

	public void setData(Object data) {
		this.responseData = data;
	}

}
