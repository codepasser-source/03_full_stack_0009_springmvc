package com.mattdamon.model;

import java.io.Serializable;

public interface BaseConvert extends Serializable {

	public abstract String convertToJson();

	public abstract String convertToXml();
}
