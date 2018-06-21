package com.mattdamon.site.handlebars;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractTemplateView;

/**
 * HandlebarsView
 * <p/>
 * 代表一个handlebars view对象
 *
 * @author <A>jiao_yang@neusoft.com</A>
 * @date May 19, 2015
 * @Copyright: © 2001-2015 东软集团股份有限公司
 */
public class HandlebarsView extends AbstractTemplateView {

	public HandlebarsView() {
	}

	protected void renderMergedTemplateModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.getWriter()
				.write("HandlebarsView->>renderMergedTemplateModel");
	}

	void init() {
		System.out.println("HandlebarsView->>init");
	}
}
