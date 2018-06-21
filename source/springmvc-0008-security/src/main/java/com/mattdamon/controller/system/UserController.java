package com.mattdamon.controller.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mattdamon.controller.BaseController;
import com.mattdamon.core.exception.CoreException;
import com.mattdamon.core.info.CoreMessage;
import com.mattdamon.core.info.MessageDescription;
import com.mattdamon.logic.BaseService;
import com.mattdamon.model.system.SysUserEntity;

@Controller
@RequestMapping(value = "/system")
public class UserController extends BaseController {

	@RequestMapping(value = "/user/view.action", method = RequestMethod.GET)
	public ModelAndView view() throws CoreException {
		List<SysUserEntity> userList = null;
		try {
			userList = userService.search(null);
			System.out.println(userList);
			// int[] s = { 0 };
			// System.out.println(s[1]);
			return new ModelAndView("/view/system/user.html");
		} catch (CoreException e) {
			throw e;
		} catch (Exception ex) {
			throw new CoreException(ex);
		}

	}

	@Autowired
	private BaseService<SysUserEntity> userService;

	@RequestMapping(value = "/user/search.do", method = RequestMethod.GET)
	@ResponseBody
	// public CoreMessage search(@RequestBody SysUserEntity base)
	public CoreMessage search() throws CoreException {
		List<SysUserEntity> userList = null;
		try {
			userList = userService.search(null);
			System.out.println(userList);
			int[] s = { 0 };
			System.out.println(s[1]);
			return message(MessageDescription.MESSAGE_SUCCESS_1, userList,
					new Object[] { "用户查询" });
		} catch (CoreException e) {
			throw e;
		} catch (Exception ex) {
			throw new CoreException(ex);
		}
	}

}
