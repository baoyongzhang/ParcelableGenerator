package com.baoyz.pg.library;

/**
 * 
 * @author baoyz
 * @date 2014-6-8ÉÏÎç1:35:16
 */
public class ProxyTemplate {

	private static final String PACKAGE = "%package";
	private static final String PROXY = "%proxy";
	private static final String CLASS = "%class";

	public static String createCode(String packageName, String proxyName,
			String className) {
		return getTemplate().replace(PACKAGE, packageName)
				.replace(PROXY, proxyName).replace(CLASS, className);
	}

	public static String getTemplate() {
		return template;
	}

	private static String template = "package %package;"
			+ ""
			+ "import android.os.Parcel;"
			+ "import android.os.Parcelable;"
			+ "import com.baoyz.pg.library.PGUtils;"
			+ ""
			+ "public class %proxy extends %class implements Parcelable {"
			+ ""
			+ "	public %proxy(%class user) {"
			+ "		PGUtils.clone(user, this);"
			+ "	}"
			+ ""
			+ "	public %proxy(Parcel source) {"
			+ "		PGUtils.read(this, source);"
			+ "	}"
			+ ""
			+ "	@Override"
			+ "	public int describeContents() {"
			+ "		return 0;"
			+ "	}"
			+ ""
			+ "	@Override"
			+ "	public void writeToParcel(Parcel dest, int flags) {"
			+ "		PGUtils.write(this, dest);"
			+ "	}"
			+ ""
			+ "	public static final Parcelable.Creator<%proxy> CREATOR = new Parcelable.Creator<%proxy>() {"
			+ "		@Override"
			+ "		public %proxy createFromParcel(Parcel source) {"
			+ "			return new %proxy(source);" + "		}" + "" + "		@Override"
			+ "		public %proxy[] newArray(int size) {"
			+ "			return new %proxy[size];" + "		}" + "	};" + "}";
}
