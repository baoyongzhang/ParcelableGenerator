ParcelableGenerator
===================
## 介绍
ParcelableGenerator可以将任意对象转换为Parcelable类型，方便对象传输。

在Android中，对象的序列化一般有两种方式，一种是Serializable，一种是Parcelable。

* Serializable 在Java中就存在，效率较低。
* Parcelable 是Android中提供的，也是官方推荐的方式，效率比Serializable高很多。

虽然Parcelable效率高，但是使用起来比Serializable麻烦很多，很多人不使用Parcelable就是因为写法太麻烦，不如Serializable简单粗暴，直接有效。

ParcelableGenerator可以解决Parcelable使用麻烦的问题，让使用Parcelable的简单性可以和使用Serializable相媲美。
