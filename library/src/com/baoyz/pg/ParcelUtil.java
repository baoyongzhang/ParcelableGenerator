package com.baoyz.pg;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.SparseArray;

/**
 * 
 * @author baoyz
 * @date 2014-7-12
 * 
 */
public class ParcelUtil {

	private static final int VAL_NULL = -1;
	private static final int VAL_STRING = 0;
	private static final int VAL_INTEGER = 1;
	private static final int VAL_MAP = 2;
	private static final int VAL_BUNDLE = 3;
	private static final int VAL_PARCELABLE = 4;
	private static final int VAL_SHORT = 5;
	private static final int VAL_LONG = 6;
	private static final int VAL_FLOAT = 7;
	private static final int VAL_DOUBLE = 8;
	private static final int VAL_BOOLEAN = 9;
	private static final int VAL_CHARSEQUENCE = 10;
	private static final int VAL_LIST = 11;
	private static final int VAL_SPARSEARRAY = 12;
	private static final int VAL_BYTEARRAY = 13;
	private static final int VAL_STRINGARRAY = 14;
	private static final int VAL_IBINDER = 15;
	private static final int VAL_PARCELABLEARRAY = 16;
	private static final int VAL_OBJECTARRAY = 17;
	private static final int VAL_INTARRAY = 18;
	private static final int VAL_LONGARRAY = 19;
	private static final int VAL_BYTE = 20;
	private static final int VAL_SERIALIZABLE = 21;
	private static final int VAL_SPARSEBOOLEANARRAY = 22;
	private static final int VAL_BOOLEANARRAY = 23;
	private static final int VAL_CHARSEQUENCEARRAY = 24;

	public static void writeValue(Parcel dest, Object v) {
		if (v == null) {
			dest.writeInt(VAL_NULL);
		} else if (v instanceof String) {
			dest.writeInt(VAL_STRING);
			dest.writeString((String) v);
		} else if (v instanceof Integer) {
			dest.writeInt(VAL_INTEGER);
			dest.writeInt((Integer) v);
		} else if (v instanceof Map) {
			dest.writeInt(VAL_MAP);
			dest.writeMap((Map) v);
		} else if (v instanceof Bundle) {
			// Must be before Parcelable
			dest.writeInt(VAL_BUNDLE);
			dest.writeBundle((Bundle) v);
		} else if (v instanceof Parcelable) {
			dest.writeInt(VAL_PARCELABLE);
			dest.writeParcelable((Parcelable) v, 0);
		} else if (v instanceof Short) {
			dest.writeInt(VAL_SHORT);
			dest.writeInt(((Short) v).intValue());
		} else if (v instanceof Long) {
			dest.writeInt(VAL_LONG);
			dest.writeLong((Long) v);
		} else if (v instanceof Float) {
			dest.writeInt(VAL_FLOAT);
			dest.writeFloat((Float) v);
		} else if (v instanceof Double) {
			dest.writeInt(VAL_DOUBLE);
			dest.writeDouble((Double) v);
		} else if (v instanceof Boolean) {
			dest.writeInt(VAL_BOOLEAN);
			dest.writeInt((Boolean) v ? 1 : 0);
		} else if (v instanceof CharSequence) {
			// Must be after String
			dest.writeInt(VAL_CHARSEQUENCE);
			TextUtils.writeToParcel((CharSequence) v, dest, 0);
		} else if (v instanceof List) {
			dest.writeInt(VAL_LIST);
			dest.writeList((List) v);
		} else if (v instanceof SparseArray) {
			dest.writeInt(VAL_SPARSEARRAY);
			dest.writeSparseArray((SparseArray) v);
		} else if (v instanceof boolean[]) {
			dest.writeInt(VAL_BOOLEANARRAY);
			dest.writeBooleanArray((boolean[]) v);
		} else if (v instanceof byte[]) {
			dest.writeInt(VAL_BYTEARRAY);
			dest.writeByteArray((byte[]) v);
		} else if (v instanceof String[]) {
			dest.writeInt(VAL_STRINGARRAY);
			dest.writeStringArray((String[]) v);
		} else if (v instanceof CharSequence[]) {
			// Must be after String[] and before Object[]
			dest.writeInt(VAL_CHARSEQUENCEARRAY);
			CharSequence[] val = (CharSequence[]) v;
			int N = val.length;
			dest.writeInt(N);
			for (int i = 0; i < N; i++) {
				TextUtils.writeToParcel((CharSequence) val[i], dest, 0);
			}
		} else if (v instanceof IBinder) {
			dest.writeInt(VAL_IBINDER);
			dest.writeStrongBinder((IBinder) v);
		} else if (v instanceof Parcelable[]) {
			dest.writeInt(VAL_PARCELABLEARRAY);
			dest.writeParcelableArray((Parcelable[]) v, 0);
		} else if (v instanceof Object[]) {
			dest.writeInt(VAL_OBJECTARRAY);
			dest.writeArray((Object[]) v);
		} else if (v instanceof int[]) {
			dest.writeInt(VAL_INTARRAY);
			dest.writeIntArray((int[]) v);
		} else if (v instanceof long[]) {
			dest.writeInt(VAL_LONGARRAY);
			dest.writeLongArray((long[]) v);
		} else if (v instanceof Byte) {
			dest.writeInt(VAL_BYTE);
			dest.writeInt((Byte) v);
		} else if (v instanceof Serializable) {
			// Must be last
			dest.writeInt(VAL_SERIALIZABLE);
			dest.writeSerializable((Serializable) v);
		} else {
			throw new RuntimeException("Parcel: unable to marshal value " + v);
		}
	}
}
