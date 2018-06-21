package com.mattdamon.model;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;

import org.hibernate.annotations.GenericGenerator;

import com.alibaba.fastjson.JSONObject;

@MappedSuperclass
public class BaseModel implements BaseConvert {

	private static final long serialVersionUID = 5635820437322045456L;

	// obj id
	protected String id;
	// 是否删除，0：否；1：是
	protected Integer is_del;
	// 创建时间
	protected Date create_at;
	// 更新时间
	protected Date update_at;
	// 描述说明
	protected String description;

	// 附加属性
	protected HashMap<String, Object> attachedProperty = new HashMap<String, Object>();

	@Id
	@Column(name = "ID", nullable = false, length = 32)
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@XmlElement
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "IS_DEL", nullable = false, length = 1)
	@XmlElement
	public Integer getIs_del() {
		return is_del;
	}

	public void setIs_del(Integer is_del) {
		this.is_del = is_del;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_AT", nullable = false)
	@XmlElement
	public Date getCreate_at() {
		return create_at;
	}

	public void setCreate_at(Date create_at) {
		this.create_at = create_at;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATE_AT")
	@XmlElement
	public Date getUpdate_at() {
		return update_at;
	}

	public void setUpdate_at(Date update_at) {
		this.update_at = update_at;
	}

	@Column(name = "DESCRIPTION")
	@XmlElement
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Transient
	@XmlElement
	public HashMap<String, Object> getAttachedProperty() {
		return attachedProperty;
	}

	public void setAttachedProperty(HashMap<String, Object> attachedProperty) {
		if (attachedProperty == null) {
			return;
		}
		this.attachedProperty = attachedProperty;
	}

	public void addAttachedProperty(String key, Object value) {
		this.attachedProperty.put(key, value);
	}

	public void removeAttachedProperty(String key) {
		this.attachedProperty.remove(key);
	}

	public void clearAttachedProperty() {
		this.attachedProperty.clear();
	}

	public boolean containsKey(String key) {
		return this.attachedProperty.containsKey(key);
	}

	@Override
	public String convertToJson() {
		return JSONObject.toJSONString(this);
	}

	@Override
	public String convertToXml() {

		StringWriter writer = new StringWriter();
		String xml = null;
		try {
			JAXBContext context = JAXBContext.newInstance(this.getClass());
			Marshaller mar = context.createMarshaller();
			mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			mar.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			mar.marshal(this, writer);
			xml = writer.toString();
		} catch (JAXBException e) {
			// IGNORE
			e.printStackTrace();
		} finally {
			if (writer != null) {
				writer.flush();
				try {
					writer.close();
				} catch (IOException e) {
					// IGNORE
				}
			}
		}
		return xml;
	}

}
