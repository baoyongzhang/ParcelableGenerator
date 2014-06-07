package com.baoyz.pg.library;

/**
 * 
 * @author baoyz
 * @date 2014-6-8ÉÏÎç1:35:08
 */
public class ProxyInfo {

	private static final String SUFFIX = "$$Parcelable";

	private String packageName;
	private String proxyName;
	private String className;

	public ProxyInfo(String qualifiedName) {
		super();
		packageName = qualifiedName
				.substring(0, qualifiedName.lastIndexOf("."));
		className = qualifiedName.substring(packageName.length() + 1);
		proxyName = className + SUFFIX;
	}

	public String createCode() {
		return ProxyTemplate.createCode(packageName, proxyName, className);
	}

	public String getFullName() {
		return packageName + "." + proxyName;
	}
}
