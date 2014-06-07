package com.baoyz.pg.library;

import java.lang.reflect.Constructor;

import android.os.Parcelable;

/**
 * 
 * @author baoyz
 * @date 2014-6-8ионГ1:34:40
 */
public class PG {

	public static Parcelable createParcelable(Object obj) {
		ProxyInfo pi = new ProxyInfo(obj.getClass().getCanonicalName());
		try {
			Class<?> clazz = Class.forName(pi.getFullName());
			Constructor<?> constructor = clazz.getConstructor(obj.getClass());
			return (Parcelable) constructor.newInstance(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
