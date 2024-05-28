package com.nadiamm.statistics.model;

import javax.persistence.*;
import com.google.gson.annotations.*;
import com.sun.istack.NotNull;

@Entity
@Table(name="REQUESTS")
public class Request{
	private final static String USERID_PATTERN =
	        "^([a-zA-Z]{8})\\-" +
	        "([a-zA-Z]{4})\\-" +
	        "([a-zA-Z]{4})\\-" +
	        "([0-9]{4})\\-" +
	        "([0-9]{12})$";
	
	private final static String IPADDRESS_PATTERN =
	        "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
	        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
	        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
	        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";	
	
	@Id
	@NotNull
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@SerializedName("ID")
	@Expose
	private long ID;

	@SerializedName("customerID")
	@Expose
	@Column(name="customer_id")
	private long customerID;
	
	@SerializedName("tagID")
	@Expose
	@Column(name="tagId")
	private long tagID;
	
	@SerializedName("userID")
	@Expose
	@Column(name="userId")
	private String userID;

	@SerializedName("remoteIP")
	@Expose
	@Column(name="IP")
	private String remoteIP;
	
	@SerializedName("timestamp")
	@Expose
	@Column(name="time")
	private int timestamp;
	
	@Expose
	@Column(name="valid")
	private boolean valid;

	public Request(long customerID, long tagID, String userID, String remoteIP, int timestamp) {
		this.customerID = customerID;
		this.tagID = tagID;
		this.userID = userID;
		this.remoteIP = remoteIP;
		this.timestamp = timestamp;
	}

	public long getCustomerID() {
		return customerID;
	}


	public long getTagID() {
		return tagID;
	}

	public String getUserID() {
		return userID;
	}

	public String getRemoteIP() {
		return remoteIP;
	}

	public int getTimestamp() {
		return timestamp;
	}

	public boolean getValid() {
		return valid;
	}

	public boolean isValid() {
		return hasValidUserID(getUserID()) && hasValidIntegers() && hasValidRemoteIP(getRemoteIP());
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public boolean hasValidIntegers() {
		return getCustomerID() >0 && getTagID( )>0 && getTimestamp()>0 ;
	}
	
	public boolean hasValidUserID(String s) {
		return s != null && s.matches(USERID_PATTERN);
	}

	public boolean hasValidRemoteIP(String s) {
		return s != null && s.matches(IPADDRESS_PATTERN);
	}

	@Override
	public String toString() {
		return String.format("Request [customerID=%s, tagID=%s, userID=%s, remoteIP=%s, timestamp=%s, valid=%s]", 
				customerID, tagID, userID, remoteIP, timestamp, valid);
	}
	
}
