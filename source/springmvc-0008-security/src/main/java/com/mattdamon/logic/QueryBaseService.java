package com.mattdamon.logic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface QueryBaseService {

	/**
	 * 查询
	 * 
	 * @param base
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> query(Map<String, Object> params)
			throws Exception;

}
