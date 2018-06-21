package com.mattdamon.logic;

import com.mattdamon.model.BaseModel;

public interface ModifyBaseService<T extends BaseModel> {

	/**
	 * 修改
	 * 
	 * @param base
	 * @throws Exception
	 */
	public void modify(T base) throws Exception;

}
