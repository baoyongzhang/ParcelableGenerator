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

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.SparseArray;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * ParcelableGenerator
 * Created by baoyz on 15/6/24.
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
        try {
            writeValueInternal(dest, v);
        } catch (Exception e) {
            writeValueInternal(dest, PG.convert(v));
        }
    }

    private static void writeValueInternal(Parcel dest, Object v) {
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
            writeMap(dest, (Map) v);
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
            writeList(dest, (List) v);
        } else if (v instanceof SparseArray) {
            dest.writeInt(VAL_SPARSEARRAY);
            writeSparseArray(dest, (SparseArray<Object>) v);
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
            writeArray(dest, (Object[]) v);
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

    private static void writeMap(Parcel dest, Map<Object, Object> val) {
        if (val == null) {
            dest.writeInt(-1);
            return;
        }
        Set<Map.Entry<Object, Object>> entries = val.entrySet();
        dest.writeInt(entries.size());
        for (Map.Entry<Object, Object> e : entries) {
            writeValue(dest, e.getKey());
            writeValue(dest, e.getValue());
        }
    }

    private static void writeList(Parcel dest, List val) {
        if (val == null) {
            dest.writeInt(-1);
            return;
        }
        int N = val.size();
        int i = 0;
        dest.writeInt(N);
        while (i < N) {
            writeValue(dest, val.get(i));
            i++;
        }
    }

    private static void writeSparseArray(Parcel dest, SparseArray<Object> val) {
        if (val == null) {
            dest.writeInt(-1);
            return;
        }
        int N = val.size();
        dest.writeInt(N);
        int i = 0;
        while (i < N) {
            dest.writeInt(val.keyAt(i));
            writeValue(dest, val.valueAt(i));
            i++;
        }
    }

    private static void writeArray(Parcel dest, Object[] val) {
        if (val == null) {
            dest.writeInt(-1);
            return;
        }
        int N = val.length;
        int i = 0;
        dest.writeInt(N);
        while (i < N) {
            writeValue(dest, val[i]);
            i++;
        }
    }
}
