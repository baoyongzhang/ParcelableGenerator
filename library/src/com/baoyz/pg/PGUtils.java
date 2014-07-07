package com.baoyz.pg;

import java.io.FileDescriptor;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import android.util.SparseBooleanArray;

/**
 * 
 * @author baoyz
 * @date 2014-6-8ÉÏÎç12:27:14
 */
public class PGUtils {

	public static void write(Object obj, Parcel dest) {
		write(obj, obj.getClass().getSuperclass(), dest);
	}

	private static void write(Object obj, Class<?> clazz, Parcel dest) {
		Field[] declaredFields = clazz.getDeclaredFields();
		for (Field field : declaredFields) {
			PGUtils.writeValue(dest, field, obj);
		}
		// super class
		if (clazz.getSuperclass() != null) {
			write(obj, clazz.getSuperclass(), dest);
		}
	}

	private static void writeValue(Parcel dest, Field field, Object target) {
		try {
			field.setAccessible(true);
			if (field.getType().equals(String.class)) {
				dest.writeString((String) field.get(target));
			} else if (field.getType().equals(int.class)) {
				dest.writeInt(field.getInt(target));
			} else if (field.getType().equals(double.class)) {
				dest.writeDouble(field.getDouble(target));
			} else if (field.getType().equals(float.class)) {
				dest.writeFloat(field.getFloat(target));
			} else if (field.getType().equals(long.class)) {
				dest.writeLong(field.getLong(target));
			} else if (field.getType().equals(boolean.class)) {
				dest.writeInt(field.getBoolean(target) ? 1 : 0);
			} else if (field.getType().equals(Bundle.class)) {
				dest.writeBundle((Bundle) field.get(target));
			} else if (field.getType().equals(List.class)) {
				dest.writeList((List) field.get(target));
			} else if (field.getType().equals(Map.class)) {
				dest.writeMap((Map) field.get(target));
			} else if (field.getType().equals(SparseArray.class)) {
				dest.writeSparseArray((SparseArray) field.get(target));
			} else if (field.getType().equals(SparseBooleanArray.class)) {
				dest.writeSparseBooleanArray((SparseBooleanArray) field
						.get(target));
			} else if (field.getType().equals(Serializable.class)) {
				dest.writeSerializable((Serializable) field.get(target));
			} else if (field.getType().equals(IBinder[].class)) {
				dest.writeBinderArray((IBinder[]) field.get(target));
			} else if (field.getType().equals(Parcelable.class)) {
				Parcelable p = (Parcelable) field.get(target);
				dest.writeParcelable(p,
						Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
			} else if (field.getType().equals(String[].class)) {
				dest.writeStringArray((String[]) field.get(target));
			} else if (field.getType().equals(int[].class)) {
				dest.writeIntArray((int[]) field.get(target));
			} else if (field.getType().equals(float[].class)) {
				dest.writeFloatArray((float[]) field.get(target));
			} else if (field.getType().equals(boolean[].class)) {
				dest.writeBooleanArray((boolean[]) field.get(target));
			} else if (field.getType().equals(long[].class)) {
				dest.writeLongArray((long[]) field.get(target));
			} else if (field.getType().equals(byte.class)) {
				dest.writeByte(field.getByte((target)));
			} else if (field.getType().equals(byte[].class)) {
				dest.writeByteArray((byte[]) field.get((target)));
			} else if (field.getType().equals(char[].class)) {
				dest.writeCharArray((char[]) field.get((target)));
			} else if (field.getType().equals(double[].class)) {
				dest.writeDoubleArray((double[]) field.get((target)));
			} else if (field.getType().equals(Parcelable[].class)) {
				dest.writeParcelableArray((Parcelable[]) field.get((target)),
						Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
			} else if (field.getType().equals(Object[].class)) {
				dest.writeArray((Object[]) field.get(target));
			} else if (field.getType().equals(Exception.class)) {
				dest.writeException((Exception) field.get(target));
			} else if (field.getType().equals(FileDescriptor.class)) {
				dest.writeFileDescriptor((FileDescriptor) field.get(target));
			} else if (field.getType().equals(IInterface.class)) {
				dest.writeStrongInterface((IInterface) field.get(target));
			} else {
				dest.writeValue(field.get(target));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void read(Object obj, Parcel source) {
		read(obj, obj.getClass().getSuperclass(), source);
	}

	private static void read(Object obj, Class<?> clazz, Parcel source) {
		Field[] declaredFields = clazz.getDeclaredFields();
		for (Field field : declaredFields) {
			PGUtils.readValue(source, field, obj);
		}
		// super class
		if (clazz.getSuperclass() != null) {
			read(obj, clazz.getSuperclass(), source);
		}
	}

	private static void readValue(Parcel source, Field field, Object target) {
		try {
			field.setAccessible(true);
			if (field.getType().equals(String.class)) {
				field.set(target, source.readString());
			} else if (field.getType().equals(int.class)) {
				field.setInt(target, source.readInt());
			} else if (field.getType().equals(double.class)) {
				field.setDouble(target, source.readDouble());
			} else if (field.getType().equals(float.class)) {
				field.setFloat(target, source.readFloat());
			} else if (field.getType().equals(long.class)) {
				field.setLong(target, source.readLong());
			} else if (field.getType().equals(boolean.class)) {
				field.setBoolean(target, source.readInt() != 0);
			} else if (field.getType().equals(Bundle.class)) {
				field.set(target, source.readBundle());
			} else if (field.getType().equals(List.class)) {
				List outVal = new ArrayList();
				source.readList(outVal, target.getClass().getClassLoader());
				field.set(target, outVal);
			} else if (field.getType().equals(Map.class)) {
				Map outVal = new HashMap();
				source.readMap(outVal, target.getClass().getClassLoader());
				field.set(target, outVal);
			} else if (field.getType().equals(SparseArray.class)) {
				field.set(target, source.readSparseArray(target.getClass()
						.getClassLoader()));
			} else if (field.getType().equals(SparseBooleanArray.class)) {
				field.set(target, source.readSparseBooleanArray());
			} else if (field.getType().equals(Serializable.class)) {
				field.set(target, source.readSerializable());
			} else if (field.getType().equals(IBinder[].class)) {
				int N = source.readInt();
				IBinder[] val = new IBinder[N];
				for (int j = 0; j < val.length; j++) {
					val[j] = source.readStrongBinder();
				}
				field.set(target, val);
			} else if (field.getType().equals(Parcelable.class)) {
				field.set(target, source.readParcelable(target.getClass()
						.getClassLoader()));
			} else if (field.getType().equals(String[].class)) {
				int N = source.readInt();
				String[] val = new String[N];
				for (int j = 0; j < val.length; j++) {
					val[j] = source.readString();
				}
				field.set(target, val);
			} else if (field.getType().equals(int[].class)) {
				int N = source.readInt();
				int[] val = new int[N];
				for (int j = 0; j < val.length; j++) {
					val[j] = source.readInt();
				}
				field.set(target, val);
			} else if (field.getType().equals(float[].class)) {
				int N = source.readInt();
				float[] val = new float[N];
				for (int j = 0; j < val.length; j++) {
					val[j] = source.readFloat();
				}
				field.set(target, val);
			} else if (field.getType().equals(boolean[].class)) {
				int N = source.readInt();
				boolean[] val = new boolean[N];
				for (int j = 0; j < val.length; j++) {
					val[j] = source.readInt() != 0;
				}
				field.set(target, val);
			} else if (field.getType().equals(long[].class)) {
				int N = source.readInt();
				long[] val = new long[N];
				for (int j = 0; j < val.length; j++) {
					val[j] = source.readLong();
				}
				field.set(target, val);
			} else if (field.getType().equals(byte.class)) {
				field.setByte(target, source.readByte());
			} else if (field.getType().equals(byte[].class)) {
				int N = source.readInt();
				byte[] val = new byte[N];
				for (int j = 0; j < val.length; j++) {
					val[j] = source.readByte();
				}
				field.set(target, val);
			} else if (field.getType().equals(char[].class)) {
				int N = source.readInt();
				byte[] val = new byte[N];
				for (int j = 0; j < val.length; j++) {
					val[j] = source.readByte();
				}
				field.set(target, val);
			} else if (field.getType().equals(double[].class)) {
				int N = source.readInt();
				double[] val = new double[N];
				for (int j = 0; j < val.length; j++) {
					val[j] = source.readDouble();
				}
				field.set(target, val);
			} else if (field.getType().equals(Parcelable[].class)) {
				field.set(target, source.readParcelableArray(target.getClass()
						.getClassLoader()));
			} else if (field.getType().equals(Object[].class)) {
				// TODO
				field.set(target,
						source.readArray(target.getClass().getClassLoader()));
			} else if (field.getType().equals(Exception.class)) {
				// TODO
			} else if (field.getType().equals(FileDescriptor.class)) {
				field.set(target, source.readFileDescriptor());
			} else if (field.getType().equals(IInterface.class)) {
				// TODO
			} else {
				field.set(target,
						source.readValue(target.getClass().getClassLoader()));
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
