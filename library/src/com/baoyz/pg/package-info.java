/**
 * ParcelableGenerator 可以快速将一个任意对象转换成Parcelable，方便传输，无需手动编写Parcelable繁琐的代码。
 * 使用方法
 * 1. 创建一个需要Parcelable传输的对象，例如叫做Model
 * Model m = new Model();
 * 2. 设置Model的属性值
 * m.set...();
 * 3. 调用PG生成Parcelable对象
 * Parcelable p = PG.createParcelable(m);
 * 4. 将p传输，例如在Intent中
 * intent.putExtran("model", p);
 * 5. 获取对象，直接获取Parcelable复制给原对象类型
 * Model m = intent.getParcelable("model");
 * 6. 获取对象的属性值
 * m.get...();
 */
package com.baoyz.pg;
