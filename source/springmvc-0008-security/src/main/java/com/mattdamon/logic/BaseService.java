package com.mattdamon.logic;

import java.util.List;

import com.mattdamon.model.BaseModel;

public interface BaseService<T extends BaseModel> {

	public List<T> search(T base) throws Exception;

	public <PK> PK save(T base) throws Exception;

	public void edit(T base) throws Exception;

	public void remove(T base) throws Exception;

}
