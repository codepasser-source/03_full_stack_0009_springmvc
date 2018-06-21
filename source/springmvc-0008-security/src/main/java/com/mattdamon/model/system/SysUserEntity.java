package com.mattdamon.model.system;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.mattdamon.model.BaseModel;

/**
 * 
 * @author MATTDAMON
 * 
 */
@Entity
@Table(name = "SYS_USER", schema = "")
@XmlRootElement
public class SysUserEntity extends BaseModel {

	private static final long serialVersionUID = -3313935954166376513L;

	private String user_name;
	private String password;

	private List<SysRoleEntity> roles = new ArrayList<SysRoleEntity>();

	@Column(name = "USER_NAME", nullable = false)
	@XmlElement
	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	@Column(name = "PASSWORD", nullable = false)
	@XmlElement
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Transient
	@XmlElement
	public List<SysRoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(List<SysRoleEntity> roles) {
		if (roles == null) {
			return;
		}
		this.roles = roles;
	}

	public void addRole(SysRoleEntity role) {
		if (role == null) {
			return;
		}
		this.roles.add(role);
	}
}
