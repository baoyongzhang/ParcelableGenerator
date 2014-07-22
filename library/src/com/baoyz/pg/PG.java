package com.baoyz.pg;

import java.lang.reflect.Constructor;

import android.os.Parcelable;

/**
 * 
 * @author baoyz
 * @date 2014-6-8ÉÏÎç1:34:40
 */
public class PG {

	/**
	 * 
	 * {@link #convertParcelable(Object obj)}.
	 * 
	 * @param obj
	 * @return
	 */
	@Deprecated
	public static Parcelable createParcelable(Object obj) {
		return convertParcelable(obj);
	}

	/**
	 * 
	 * Object2Parcelable
	 * 
	 * @param obj
	 * @return
	 */
	public static Parcelable convertParcelable(Object obj) {
		if (obj instanceof Parcelable) {
			return (Parcelable) obj;
		}
		ProxyInfo pi = new ProxyInfo(obj.getClass().getCanonicalName());
		try {
			Class<?> clazz = Class.forName(pi.getFullName());
			Constructor<?> constructor = clazz.getConstructor(obj.getClass());
			return (Parcelable) constructor.newInstance(obj);
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * Object2Parcelable
	 * 
	 * @param obj
	 * @return
	 */
	public static <T> T convert(T obj) {
		return (T) convertParcelable(obj);
	}
}
