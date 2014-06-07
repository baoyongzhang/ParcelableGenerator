package com.baoyz.pg.library;

import java.lang.reflect.Field;

import android.os.Parcel;

/**
 * 
 * @author baoyz
 * @date 2014-6-8ÉÏÎç12:27:14
 */
public class PGUtils {

	public static void write(Object obj, Parcel dest) {
		Field[] declaredFields = obj.getClass().getSuperclass()
				.getDeclaredFields();
		for (Field field : declaredFields) {
			PGUtils.writeValue(dest, field, obj);
		}
	}

	public static void writeValue(Parcel dest, Field field, Object target) {
		try {
			field.setAccessible(true);
			if (field.getType().equals(String.class)) {
				dest.writeString((String) field.get(target));
			} else if (field.getType().equals(int.class)) {
				dest.writeInt(field.getInt(target));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void read(Object obj, Parcel source) {
		Field[] declaredFields = obj.getClass().getSuperclass()
				.getDeclaredFields();
		for (Field field : declaredFields) {
			PGUtils.readValue(source, field, obj);
		}
	}

	public static void readValue(Parcel source, Field field, Object target) {
		try {
			field.setAccessible(true);
			if (field.getType().equals(String.class)) {
				field.set(target, source.readString());
			} else if (field.getType().equals(int.class)) {
				field.setInt(target, source.readInt());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void clone(Object source, Object dest) {
		Field[] declaredFields = source.getClass().getDeclaredFields();
		try {
			for (Field field : declaredFields) {
				field.setAccessible(true);
				field.set(dest, field.get(source));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
