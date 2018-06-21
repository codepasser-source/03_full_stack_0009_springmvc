package com.mattdamon.core.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mattdamon.model.system.SysResourceEntity;
import com.mattdamon.model.system.SysRoleEntity;
import com.mattdamon.model.system.SysUserEntity;

@SuppressWarnings("deprecation")
public class MyUserDetailServiceImpl implements UserDetailsService {

	// @真实环境中接入数据
	// private UsersDao usersDao;
	// public UsersDao getUsersDao() {
	// return usersDao;
	// }
	// public void setUsersDao(UsersDao usersDao) {
	// this.usersDao = usersDao;
	// }

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		System.out.println("username is " + username);

		SysUserEntity user = null;
		if (username != null && username.equals("tom")) {
			// @真实环境中接入数据
			// Users users = this.usersDao.findByName(username);
			// 模拟数据
			user = new SysUserEntity();
			user.setUser_name("tom");
			user.setPassword("123456");

			SysRoleEntity role = new SysRoleEntity();
			role.setName("超级用户");

			SysResourceEntity resource = new SysResourceEntity();
			resource.setName("0001");
			resource.setUri("/system/user/view.action");

			role.addResource(resource);

			user.addRole(role);
		} else {
			throw new UsernameNotFoundException(username);
		}

		// 获取用户允许访问的resource
		Collection<GrantedAuthority> grantedAuths = obtionGrantedAuthorities(user);

		boolean enables = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		User userdetail = new User(user.getUser_name(), user.getPassword(),
				enables, accountNonExpired, credentialsNonExpired,
				accountNonLocked, grantedAuths);

		return userdetail;
	}

	// 取得用户的权限
	private Set<GrantedAuthority> obtionGrantedAuthorities(SysUserEntity user) {
		Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
		List<SysRoleEntity> roles = user.getRoles();

		for (SysRoleEntity role : roles) {
			List<SysResourceEntity> tempRes = role.getResources();
			for (SysResourceEntity res : tempRes) {
				authSet.add(new GrantedAuthorityImpl(res.getName()));
			}
		}
		return authSet;
	}

}