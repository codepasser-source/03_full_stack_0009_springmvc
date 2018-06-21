package com.mattdamon.site.handlebars;

import java.io.IOException;

import org.springframework.web.servlet.view.AbstractTemplateViewResolver;
import org.springframework.web.servlet.view.AbstractUrlBasedView;

import com.github.jknack.handlebars.io.ServletContextTemplateLoader;

/**
 * HandlebarsViewResolver
 *
 * @author <A>jiao_yang@neusoft.com</A>
 * @date May 19, 2015
 * @Copyright: © 2001-2015 东软集团股份有限公司
 */
public class HandlebarsViewResolver extends AbstractTemplateViewResolver {
	public static final String DEFAULT_CONTENT_TYPE = "text/html;charset=UTF-8";

	public HandlebarsViewResolver() {
		this.setViewClass(HandlebarsView.class);
		this.setContentType(DEFAULT_CONTENT_TYPE);
	}

	public void setPrefix(String prefix) {
		throw new UnsupportedOperationException("Use "
				+ ServletContextTemplateLoader.class.getName() + "#setPrefix");
	}

	public void setSuffix(String suffix) {
		throw new UnsupportedOperationException("Use "
				+ ServletContextTemplateLoader.class.getName() + "#setSuffix");
	}

	protected AbstractUrlBasedView buildView(String viewName) throws Exception {
		return this.configure((HandlebarsView) super.buildView(viewName));
	}

	protected AbstractUrlBasedView configure(HandlebarsView view)
			throws IOException {
		String url = view.getUrl();
		if (!url.startsWith("/")) {
			url = "/" + url;
		}
		view.init();
		return view;
	}

	protected Class<?> requiredViewClass() {
		return HandlebarsView.class;
	}
}
