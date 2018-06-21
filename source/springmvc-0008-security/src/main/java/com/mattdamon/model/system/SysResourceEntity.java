package com.mattdamon.model.system;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.mattdamon.model.BaseModel;

/**
 * 
 * @author MATTDAMON
 * 
 */
@Entity
@Table(name = "SYS_RESOURCE", schema = "")
@XmlRootElement
public class SysResourceEntity extends BaseModel {

	private static final long serialVersionUID = 4780156018805829967L;

	private String name;
	private String uri;
	private String priority;
	private String category;

	@Column(name = "NAME", nullable = false)
	@XmlElement
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "URI", nullable = false)
	@XmlElement
	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	@Column(name = "PRIORITY", nullable = false)
	@XmlElement
	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	@Column(name = "CATEGORY", nullable = false)
	@XmlElement
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
