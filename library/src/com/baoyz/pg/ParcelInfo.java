package com.baoyz.pg;

import android.os.Parcelable;

/**
 * 
 * @author baoyz
 * @date 2014-8-1
 * 
 */
class ParcelInfo {

	private static final String SUFFIX = "$$Parcelable";

	private String className;
	private Parcelable parcel;

	public ParcelInfo(Parcelable parcel) {
		super();
		this.parcel = parcel;
		String qualifiedName = parcel.getClass().getCanonicalName();
		if (qualifiedName.endsWith(SUFFIX)) {
			className = qualifiedName.substring(0, qualifiedName.length()
					- SUFFIX.length());
		}
	}

	public <T> T getSource() {
		try {
			Object obj = Class.forName(className).newInstance();
			PGUtils.clone(parcel, obj);
			return (T) obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (T) parcel;
	}
}
