package com.baoyz.pg;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import android.os.Parcel;
import android.util.Log;

/**
 * 
 * @author baoyz
 * @date 2014-6-8
 *
 */
public class PGUtils {

	public static void write(Object obj, Parcel dest) {
		write(obj, obj.getClass().getSuperclass(), dest);
	}

	private static void write(Object obj, Class<?> clazz, Parcel dest) {
		Field[] declaredFields = clazz.getDeclaredFields();
		for (Field field : declaredFields) {
			log("write = " + field.getName());
			PGUtils.writeValue(dest, field, obj);
		}
		// super class
		if (clazz.getSuperclass() != null && clazz != Object.class) {
			write(obj, clazz.getSuperclass(), dest);
		}
	}

	private static void writeValue(Parcel dest, Field field, Object target) {
		try {
			if (!checkSerializable(field)) {
				return;
			}
			field.setAccessible(true);
			if (field.getType().equals(int.class)) {
				dest.writeInt(field.getInt(target));
			} else if (field.getType().equals(double.class)) {
				dest.writeDouble(field.getDouble(target));
			} else if (field.getType().equals(float.class)) {
				dest.writeFloat(field.getFloat(target));
			} else if (field.getType().equals(long.class)) {
				dest.writeLong(field.getLong(target));
			} else if (field.getType().equals(boolean.class)) {
				dest.writeInt(field.getBoolean(target) ? 1 : 0);
			} else if (field.getType().equals(char.class)) {
				dest.writeInt(field.getChar(target));
			} else if (field.getType().equals(byte.class)) {
				dest.writeByte(field.getByte(target));
			} else if (field.getType().equals(short.class)) {
				dest.writeInt(field.getShort(target));
			} else {
				Object value = field.get(target);
				ParcelUtil.writeValue(dest, value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static boolean checkSerializable(Field field) {
		return !Modifier.isTransient(field.getModifiers())
				&& !field.isAnnotationPresent(ParcelIgnore.class);
		// && (field.getType().isPrimitive()
		// || Serializable.class.isAssignableFrom(field.getType()) ||
		// Parcelable.class
		// .isAssignableFrom(field.getType()));
		// return true;
	}

	public static void read(Object obj, Parcel source) {
		read(obj, obj.getClass().getSuperclass(), source);
	}

	private static void read(Object obj, Class<?> clazz, Parcel source) {
		Field[] declaredFields = clazz.getDeclaredFields();
		for (Field field : declaredFields) {
			log("read = " + field.getName());
			PGUtils.readValue(source, field, obj);
		}
		// super class
		if (clazz.getSuperclass() != null && clazz != Object.class) {
			read(obj, clazz.getSuperclass(), source);
		}
	}

	private static void readValue(Parcel source, Field field, Object target) {
		try {
			if (!checkSerializable(field)) {
				return;
			}
			field.setAccessible(true);
			if (field.getType().equals(int.class)) {
				field.setInt(target, source.readInt());
			} else if (field.getType().equals(double.class)) {
				field.setDouble(target, source.readDouble());
			} else if (field.getType().equals(float.class)) {
				field.setFloat(target, source.readFloat());
			} else if (field.getType().equals(long.class)) {
				field.setLong(target, source.readLong());
			} else if (field.getType().equals(boolean.class)) {
				field.setBoolean(target, source.readInt() != 0);
			} else if (field.getType().equals(char.class)) {
				field.setChar(target, (char) source.readInt());
			} else if (field.getType().equals(byte.class)) {
				field.setByte(target, source.readByte());
			} else if (field.getType().equals(short.class)) {
				field.setShort(target, (short) source.readInt());
			} else {
				field.set(target,
						source.readValue(target.getClass().getClassLoader()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void clone(Object source, Object dest) {
		clone(source.getClass(), source, dest);
	}

	private static void clone(Class<?> clazz, Object source, Object dest) {
		Field[] declaredFields = clazz.getDeclaredFields();
		try {
			for (Field field : declaredFields) {
				field.setAccessible(true);
				field.set(dest, field.get(source));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (clazz.getSuperclass() != null
				&& clazz.getSuperclass() != Object.class) {
			clone(clazz.getSuperclass(), source, dest);
		}
	}

	private static void log(String log) {
//		Log.i("byz", log);
	}
}
