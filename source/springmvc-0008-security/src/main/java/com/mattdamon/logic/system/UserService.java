package com.mattdamon.logic.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.mattdamon.dbcore.DBCoreRWProvider;
import com.mattdamon.logic.BaseService;
import com.mattdamon.model.system.SysUserEntity;

/**
 * 
 * @author MATTDAMON
 * 
 */
@Service
public class UserService implements BaseService<SysUserEntity> {

	@Autowired
	private DBCoreRWProvider dbCoreRWProvider;

	@Override
	@Cacheable(value = "default", key = "'searchUser'")
	public List<SysUserEntity> search(SysUserEntity base) throws Exception {
		List<SysUserEntity> userList = dbCoreRWProvider
				.getAll(SysUserEntity.class);
		return userList;
	}

	@Override
	public <PK> PK save(SysUserEntity base) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void edit(SysUserEntity base) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(SysUserEntity base) throws Exception {
		// TODO Auto-generated method stub

	}

}
