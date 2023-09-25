package com.nadiamm.statistics.model;

import javax.persistence.*;
import com.sun.istack.NotNull;

@Entity
@Table(name="IP_BLACKLIST")
public class IpBlacklist {
	@Id
	@NotNull
	@Column(name="IP")
	private long remoteIP;
}
