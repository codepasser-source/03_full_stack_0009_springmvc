package com.mattdamon.core.env;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.ConvertUtils;

import com.mattdamon.core.exception.CoreException;
import com.mattdamon.core.exception.ErrorDescription;

/**
 * 
 * @author MATTDAMON
 * 
 */
public final class CoreProperties {

	private static Map<String, NullableProperties> propertiesMap = new HashMap<String, NullableProperties>();

	private static Map<String, File> propertiesFileMap = new HashMap<String, File>();

	private static Map<String, Long> lastModifyTimeMap = new HashMap<String, Long>();

	@SuppressWarnings("unchecked")
	public static final synchronized <T> T getProperties(Class<T> clazz)
			throws CoreException {
		// create instance for type T.
		T instance;
		try {
			Constructor<? extends Object> constructor = clazz.getConstructor();
			instance = (T) constructor.newInstance();
		} catch (Exception e1) {
			throw new CoreException(e1);
		}
		// check annotation
		if (!clazz
				.isAnnotationPresent(com.mattdamon.core.env.Properties.class)) {
			return instance;
		}
		// read properties file.
		com.mattdamon.core.env.Properties annotationProperties = clazz
				.getAnnotation(com.mattdamon.core.env.Properties.class);
		String propertiesName = annotationProperties.value();
		String dir = annotationProperties.dir();

		NullableProperties properties = propertiesMap.get(propertiesName);
		File propertiesFile = propertiesFileMap.get(propertiesName);
		Long lastModifyTime = lastModifyTimeMap.get(propertiesName);
		if (properties == null || propertiesFile == null
				|| propertiesFile.lastModified() > lastModifyTime) {
			propertiesFile = getPropertiesFile(propertiesName, dir);
			propertiesFileMap.put(propertiesName, propertiesFile);

			lastModifyTime = propertiesFile.lastModified();
			lastModifyTimeMap.put(propertiesName, lastModifyTime);

			properties = (NullableProperties) readProperties(propertiesFile);
			propertiesMap.put(propertiesName, properties);
		} else {
			properties = propertiesMap.get(propertiesName);
		}

		// find set method which has Property annotation.
		Method[] methods = clazz.getMethods();
		for (Method method : methods) {
			int mod = method.getModifiers();
			if (!Modifier.isPublic(mod)
					|| !method.isAnnotationPresent(Property.class)) {
				continue;
			}

			Class<?>[] parameterTypes = method.getParameterTypes();
			if (parameterTypes.length != 1) {
				throw new CoreException(
						ErrorDescription.ERROR_METHOD_PARAMETER_NUMBER_2,
						method.getName(), 1);
			}

			Property annotationProperty = method.getAnnotation(Property.class);
			String key = annotationProperty.key();
			String defaultValue = annotationProperty.defaultValue();
			String propertyValue = properties.getProperty(key, defaultValue);
			try {
				method.invoke(instance,
						ConvertUtils.convert(propertyValue, parameterTypes[0]));
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}

		if (instance instanceof java.util.Properties) {
			java.util.Properties props = (java.util.Properties) instance;
			props.putAll(properties);
		}

		return instance;
	}

	private static Properties readProperties(File file) throws CoreException {
		BufferedInputStream bis = null;
		Properties prop = null;
		try {
			bis = new BufferedInputStream(new FileInputStream(file));
			prop = new NullableProperties();
			prop.load(bis);
		} catch (FileNotFoundException ex1) {
			throw new CoreException(ex1);
		} catch (SecurityException e) {
			throw new CoreException(e);
		} catch (IOException ex2) {
			throw new CoreException(ex2);
		} finally {
			try {
				if (bis != null) {
					bis.close();
				}
			} catch (IOException ex) {
			}
		}
		return prop;
	}

	private static File getPropertiesFile(String filename, String defaultDir)
			throws CoreException {

		defaultDir = processPath(defaultDir);

		File file = new File(defaultDir, filename);
		if (!file.exists()) {
			try {
				ClassLoader loader = Thread.currentThread()
						.getContextClassLoader();
				if (loader == null) {
					loader = CoreProperties.class.getClassLoader();
				}
				if (loader != null) {
					URL url = file.isAbsolute() ? loader.getResource(filename)
							: loader.getResource(file.getPath());
					if (url != null) {
						String newFilename = URLDecoder.decode(url.getFile(),
								"ISO-8859-1");
						file = new File(newFilename);
					}
				}
				if (!file.exists()) {
					file = new File(filename);
				}
			} catch (SecurityException e) {
				throw new CoreException(e);
			} catch (IOException ex2) {
				throw new CoreException(ex2);
			}
		}

		return file;
	}

	private static String processPath(String defaultDir) {
		Pattern pattern = Pattern.compile("\\$\\{(\\p{Alpha}+[\\w\\.]*)\\}");
		Matcher matcher = pattern.matcher(defaultDir);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			String variable = matcher.group(1);
			String value = System.getProperty(variable);
			if (value != null) {
				value = value.replaceAll("\\\\", "\\\\\\\\");
				matcher.appendReplacement(sb, value);
			}
		}

		matcher.appendTail(sb);
		return sb.toString();
	}

	public static class NullableProperties extends Properties {

		private static final long serialVersionUID = 8591137352668286302L;

		@Override
		public String getProperty(String key, String defaultValue) {
			String origin = super.getProperty(key, defaultValue);
			if (origin != null && origin.trim().length() == 0) {
				// 空白
				return defaultValue;
			}
			return origin;
		}
	};
}
