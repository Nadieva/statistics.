package com.nadiamm.statistics.model;

import java.sql.Timestamp;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sun.istack.NotNull;

@Entity
@Table(name="HOURLY_STATS")
@JsonPropertyOrder({ "customerId", "timestamp","requestCount","invalidCount" })
public class HourlyStats {

	@Id
	@NotNull
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@Column(name="customer_id")
	@JsonProperty("customerid")
	private long customerId;
	
	@NotNull
	@JsonProperty("timestamp")
	private Timestamp time;
	
	@NotNull
	@Column(name="request_count")
	@JsonProperty("requestCount")
	private int validRequestCount=0;
	
	@NotNull
	@Column(name="invalid_count")
	@JsonProperty("invalidCount")
	private int invalidRequestCount=0;

	public HourlyStats(long customerId, int time,int validRequestCount,int invalidRequestCount) {
		this.customerId=customerId;
		this.time=new Timestamp(time);
		this.validRequestCount=validRequestCount;
		this.invalidRequestCount=invalidRequestCount;
	}
	
	public HourlyStats() {
		
	}

}