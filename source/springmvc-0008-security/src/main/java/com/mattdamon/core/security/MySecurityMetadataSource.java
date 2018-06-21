package com.mattdamon.core.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import com.mattdamon.model.system.SysResourceEntity;

//1 加载资源与权限的对应关系
public class MySecurityMetadataSource implements
		FilterInvocationSecurityMetadataSource {

	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

	// private ResourcesDao resourcesDao;
	// // 构造器：由spring调用
	// public MySecurityMetadataSource(ResourcesDao resourcesDao) {
	// this.resourcesDao = resourcesDao;
	// loadResourceDefine();
	// }
	// public ResourcesDao getResourcesDao() {
	// return resourcesDao;
	// }
	// public void setResourcesDao(ResourcesDao resourcesDao) {
	// this.resourcesDao = resourcesDao;
	// }

	// 返回所请求资源所需要的权限
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		String requestUrl = ((FilterInvocation) object).getRequestUrl();
		System.out.println("requestUrl is " + requestUrl);
		if (resourceMap == null) {
			loadResourceDefine();
		}
		return resourceMap.get(requestUrl);
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return true;
	}

	// 加载所有资源与权限的关系
	private void loadResourceDefine() {

		if (resourceMap == null) {
			resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
			// @真实环境接入数据
			// List<Resources> resources = this.resourcesDao.findAll();

			List<SysResourceEntity> resources = new ArrayList<SysResourceEntity>();
			SysResourceEntity resource1 = new SysResourceEntity();
			resource1.setName("0001");
			resource1.setUri("/system/user/view.action");
			resources.add(resource1);

			SysResourceEntity resource2 = new SysResourceEntity();
			resource2.setName("0002");
			resource2.setUri("/system/user/search.do");
			resources.add(resource2);

			for (SysResourceEntity res : resources) {
				Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
				// 以权限名封装为Spring的security Object
				ConfigAttribute configAttribute = new SecurityConfig(
						res.getName());
				configAttributes.add(configAttribute);
				resourceMap.put(res.getUri(), configAttributes);
			}

		}

		Set<Entry<String, Collection<ConfigAttribute>>> resourceSet = resourceMap
				.entrySet();
		@SuppressWarnings("unused")
		Iterator<Entry<String, Collection<ConfigAttribute>>> iterator = resourceSet
				.iterator();

	}
}