package com.mattdamon.core.info;

/**
 * 
 * @author MATTDAMON
 * 
 */
public interface MessageCode {

	/********************** SYSTEM MESSAGE ***************************/
	/**
	 * 系统消息-操作成功。
	 */
	int MESSAGE_SUCCESS = 00000000;
	/**
	 * 系统消息-操作失败。
	 */
	int MESSAGE_FAILED = 00000001;

	/**
	 * 系统消息-登录成功。
	 */
	int MESSAGE_LOGIN_SUCCESS = 00000002;

	/**
	 * 系统消息-登录失败。
	 */
	int MESSAGE_LOGIN_FAILED = 00000003;

}