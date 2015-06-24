/*
 * The MIT License (MIT)
 * 
 * Copyright (c) 2015 baoyongzhang <baoyz94@gmail.com>
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.baoyz.pg;

/**
 * ParcelableGenerator
 * Created by baoyz on 15/6/24.
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
            + "import com.baoyz.pg.PGUtils;"
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
