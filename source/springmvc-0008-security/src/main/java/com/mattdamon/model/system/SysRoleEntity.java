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
@Table(name = "SYS_ROLE", schema = "")
@XmlRootElement
public class SysRoleEntity extends BaseModel {

	private static final long serialVersionUID = -2635119650003013458L;

	private String name;

	private List<SysResourceEntity> resources = new ArrayList<SysResourceEntity>();

	@Column(name = "NAME", nullable = false)
	@XmlElement
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Transient
	@XmlElement
	public List<SysResourceEntity> getResources() {
		return resources;
	}

	public void setResources(List<SysResourceEntity> resources) {
		this.resources = resources;
	}

	public void addResource(SysResourceEntity resource) {
		if (resource == null) {
			return;
		}
		this.resources.add(resource);
	}
}
