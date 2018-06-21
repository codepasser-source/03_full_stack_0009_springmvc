package com.mattdamon.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.collect.Maps;
import com.google.common.hash.Hashing;
import com.mattdamon.core.exception.CoreException;
import com.mattdamon.core.exception.ErrorDescription;

/**
 * 
 * @author MATTDAMON
 * 
 */
public class HttpClientUtil {

	public static String V2_CHANNEL = "channel";
	public static String V2_CHANNEL_LC = "LUCKY-COMMUNITY";
	public static String V2_SESSION = "session";

	private static String V2_SIGN_KEY = "74c8b8d2e316f65db9e0eea7ada040a5";
	private static String V2_SIGN = "sign";

	public static String V2_RESULT_KEY_DATA = "data";
	public static String V2_RESULT_KEY_STATUS = "status";
	public static String V2_RESULT_DATA_KEY_SUCCESS = "success";
	public static String V2_RESULT_DATA_KEY_RRSUID = "result";
	public static String V2_RESULT_DATA_KEY_SESSIONID = "sessionId";
	public static String V2_RESULT_DATA_KEY_ERROR = "error";

	public static synchronized JSONObject httpGet(String url,
			Map<String, String> params) throws CoreException {
		String result = "";
		try {
			// 创建参数 sign MD5处理
			params = buildParams(params);

			url = url + "?"
					+ Joiner.on('&').withKeyValueSeparator("=").join(params);

			// 创建HttpClient实例
			HttpClient httpclient = HttpClientBuilder.create().build();
			// 创建Get方法实例
			HttpGet httpGet = new HttpGet(url);

			HttpResponse response = httpclient.execute(httpGet);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					InputStream instreams = entity.getContent();
					result = convertStreamToString(instreams);
					httpGet.abort();
				}
			} else {
				throw new CoreException(
						ErrorDescription.ERROR_HTTPEXCEPTION_OCCURRED_2,
						new Object[] { url,
								response.getStatusLine().getStatusCode() });
			}
		} catch (Exception e) {
			throw new CoreException(ErrorDescription.ERROR_OTHER_1, e);
		}
		return JSONObject.parseObject(result);
	}

	public static synchronized JSONObject httpPost(String url,
			Map<String, String> params) throws CoreException {
		String result = "";
		try {

			// 创建参数 sign MD5处理
			params = buildParams(params);

			url = url + "?"
					+ Joiner.on('&').withKeyValueSeparator("=").join(params);

			// 创建HttpClient实例
			HttpClient httpclient = HttpClientBuilder.create().build();
			// 创建Get方法实例
			HttpPost httpPost = new HttpPost(url);

			httpPost.setHeader("Authorization", "Basic UTF-8");
			httpPost.addHeader("Accept", "application/json");
			httpPost.setHeader("Content-Type",
					"application/x-www-form-urlencoded; charset=UTF-8");

			HttpResponse response = httpclient.execute(httpPost);

			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					InputStream instreams = entity.getContent();
					result = convertStreamToString(instreams);
					httpPost.abort();
				}
			} else {
				throw new CoreException(
						ErrorDescription.ERROR_HTTPEXCEPTION_OCCURRED_2,
						new Object[] { url,
								response.getStatusLine().getStatusCode() });
			}
		} catch (Exception e) {
			throw new CoreException(ErrorDescription.ERROR_OTHER_1, e);
		}
		return JSONObject.parseObject(result);
	}

	private static String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	// 入参按升序排列
	public static Map<String, String> buildParams(Map<String, String> params) {
		// sort if not sorted map
		if (!(params instanceof SortedMap)) {
			params = new TreeMap<String, String>(params);
		}

		// filter null value
		Map<String, String> filterMap = Maps.filterKeys(params,
				new Predicate<String>() {
					@Override
					public boolean apply(String input) {
						return input != null;
					}
				});

		String toVerify = Joiner.on('&').withKeyValueSeparator("=")
				.join(filterMap);
		// only sign once now
		String sign = sign(toVerify + V2_SIGN_KEY, 1);
		params.put(V2_SIGN, sign);
		return params;

	}

	// 签名
	protected static String sign(String toVerify, int deep) {
		if (deep == 0) {
			return toVerify;
		}
		String expect = Hashing.md5().newHasher()
				.putString(toVerify, Charsets.UTF_8).hash().toString();
		return sign(expect, deep - 1);
	}
}
