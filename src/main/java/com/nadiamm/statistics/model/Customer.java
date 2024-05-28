package com.nadiamm.statistics.model;
import javax.persistence.*;
import com.sun.istack.NotNull;

@Entity
@Table(name="CUSTOMERS")
public class Customer {
	
	@Id
	@NotNull
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;
	
	@NotNull
	private String name;
	
	@NotNull
	private boolean active = true;


	public Customer(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
