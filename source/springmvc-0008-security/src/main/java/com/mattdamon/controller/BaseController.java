package com.mattdamon.controller;

import java.io.Serializable;
import java.util.Map;

import com.mattdamon.common.util.SessionUtil;
import com.mattdamon.core.exception.CoreException;
import com.mattdamon.core.info.CoreMessage;
import com.mattdamon.core.info.MessageDescription;
import com.mattdamon.model.system.SysUserEntity;

/**
 * 
 * @author MATTDAMON
 * 
 */
public abstract class BaseController {

	protected Map<String, Serializable> getSession() throws CoreException {
		return SessionUtil.getSession();
	}

	protected Serializable getSession(String key) throws CoreException {
		return SessionUtil.getSession(key);
	}

	protected void setSession(String key, Serializable obj)
			throws CoreException {
		SessionUtil.setSession(key, obj);
	}

	protected SysUserEntity getLoginUser() throws CoreException {
		return (SysUserEntity) SessionUtil
				.getSession(SessionUtil.USER_SESSION_KEY);
	}

	protected CoreMessage message(final MessageDescription msgDescription,
			Object clientData) {
		return new CoreMessage(msgDescription, clientData);
	}

	protected CoreMessage message(final MessageDescription msgDescription,
			final Object... args) {
		return new CoreMessage(msgDescription, null, args);
	}

	protected CoreMessage message(final MessageDescription msgDescription,
			Object clientData, final Object... args) {
		return new CoreMessage(msgDescription, clientData, args);
	}

}
