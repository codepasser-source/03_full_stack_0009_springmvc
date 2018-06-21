package com.mattdamon.core.env;

/**
 * 
 * @author MATTDAMON
 * 
 */
@Properties(value = "core.config.properties")
public class CoreConfigProperties {

	private String domain;
	private String subdomain;
	private String sessionMode;
	private String rootDir;
	private String projectDir;

	public String getDomain() {
		return domain;
	}

	@Property(key = "neu.ecommerce.domain", defaultValue = "")
	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getSubdomain() {
		return subdomain;
	}

	@Property(key = "neu.ecommerce.subdomain", defaultValue = "")
	public void setSubdomain(String subdomain) {
		this.subdomain = subdomain;
	}

	public String getSessionMode() {
		return sessionMode;
	}

	@Property(key = "neu.ecommerce.session.mode", defaultValue = "")
	public void setSessionMode(String sessionMode) {
		this.sessionMode = sessionMode;
	}

	public String getRootDir() {
		return rootDir;
	}

	@Property(key = "neu.ecommerce.root.dir", defaultValue = "")
	public void setRootDir(String rootDir) {
		this.rootDir = rootDir;
	}

	public String getProjectDir() {
		return projectDir;
	}

	@Property(key = "neu.ecommerce.project.dir", defaultValue = "")
	public void setProjectDir(String projectDir) {
		this.projectDir = projectDir;
	}

}
