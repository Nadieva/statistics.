package com.adex.statistics.entity;

import java.util.List;
import com.google.gson.annotations.*;

public class RequestList {

	@SerializedName("requests")
	@Expose
	private List<Request> requestList=null;
	
	public List<Request> getRequestList() {
		return requestList;
	}
	
}
