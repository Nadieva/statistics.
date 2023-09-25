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
}
