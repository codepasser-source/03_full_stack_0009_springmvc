package com.matt.damon.hibernate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "TP_USER", schema = "")
public class UserEntity {

	/** id */
	private String id;
	private String username;
	private String password;
	private String name;
	private int acl;

	// Oracle sequence
	// @Id
	// @GeneratedValue(strategy = GenerationType.SEQUENCE, generator =
	// "payablemoney_seq")
	// @SequenceGenerator(name = "payablemoney_seq", sequenceName =
	// "SEQ_TP_USER_ID")
	// @Column(name = "ID", nullable = false, length = 32)
	@Id
	@Column(name = "ID", nullable = false, length = 32)
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "USER_NAME", nullable = false)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "PASSWORD", nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "NAME", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "ACL", nullable = false)
	public int getAcl() {
		return acl;
	}

	public void setAcl(int acl) {
		this.acl = acl;
	}

}
