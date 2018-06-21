package com.mattdamon.logic;

import java.io.Serializable;
import java.util.List;

public interface BaseAdapterService<T extends Serializable> {

	public List<T> search(T base) throws Exception;

	public <PK> PK save(T base) throws Exception;

	public void edit(T base) throws Exception;

	public void remove(T base) throws Exception;

}
