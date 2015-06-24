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

import android.os.Parcelable;

import java.lang.reflect.Constructor;

/**
 * ParcelableGenerator
 * Created by baoyz on 15/6/24.
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

    public static <T> T unconvert(Parcelable parcel) {
        ParcelInfo info = new ParcelInfo(parcel);
        return info.getSource();
    }
}
