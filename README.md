ParcelableGenerator
===================
## 介绍
ParcelableGenerator可以将任意对象转换为Parcelable类型，方便对象传输。

在Android中，对象的序列化一般有两种方式，一种是Serializable，一种是Parcelable。

* Serializable 在Java中就存在，效率较低。
* Parcelable 是Android中提供的，也是官方推荐的方式，效率比Serializable高很多。

虽然Parcelable效率高，但是使用起来比Serializable麻烦很多，很多人不使用Parcelable就是因为写法太麻烦，尤其是属性特别多的时候，我们要将每个属性Parcel.write()然后在Parcel.read()回来，相当繁琐，不如Serializable简单粗暴，直接有效。

ParcelableGenerator可以解决Parcelable使用麻烦的问题，让使用Parcelable的简单性可以和使用Serializable相媲美。

## 使用方法

例如我们有一个User类，用来保存用户的一些信息，我们需要使用@Parcelable修饰该类

```java
import com.baoyz.pg.Parcelable;

@Parcelable
public class User {

	private String name;
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
```

我们要将一个User对象通过Intent传递给一个Activity叫做ShowUserActivity。我们需要调用Intent.putExtra()方法将对象传入，这时候直接传递肯定是不行的，我们需要调用PG.createParcelable()方法将对象转换为Parcelable在传入Intent中。

```java
import com.baoyz.pg.PG;

// 模拟创建对象，并设置属性值 
User user = new User();
user.setName("zhangsan");
user.setAge(18);
		
Intent intent = new Intent(this, ShowUserActivity.class);
// 调用PG将对象转换成Parcelable，传入Intent中
intent.putExtra("user", PG.createParcelable(user));
startActivity(intent);
```

在ShowUserActivity中获取User对象，无需写任何转换的代码，直接getIntent().getParcelableExtra()赋值给原对象类型变量即可。

```java
public class ShowUserActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// 直接获取原对象类型
		User user = getIntent().getParcelableExtra("user");
		
		// 获取属性值
		user.getName();
		user.getAge();
		
	}

}
```

## 在你的项目中使用

### 导入jar包

将jar包导入到项目中，jar包只有10Kb大小，相当轻巧。
<p>
<a href="https://github.com/baoyongzhang/ParcelableGenerator/raw/master/pg1.0.jar" alt="download jar">
<font size="32px">Download jar</font>
<a>
</p>

### 环境配置

不知道各位是否用过butterknife，本项目的原理与butterknife类似（其实我是抄的），需要配置AnnotationProcessing

#### Eclipse配置方法

* 项目右键->Properties->Java Compiler->Annotation Processing	开启 Enable project specific settings

![](https://raw.githubusercontent.com/baoyongzhang/test_pages/gh-pages/pg/image-1.jpg)

* 然后展开Annotation Processing->Factory Path	开启Enable project specific settings

![](https://raw.githubusercontent.com/baoyongzhang/test_pages/gh-pages/pg/image-2.jpg)

* 点击AddJars 添加pg.jar

![](https://raw.githubusercontent.com/baoyongzhang/test_pages/gh-pages/pg/image-3.jpg)

* 最后点一直OK，弹出对话框点YES即可。

## 详细介绍

不嫌啰嗦的可以继续看

### 支持的数据类型

* 涵盖Parcel可write的所有类型

### 对比

还是用上面的例子

#### 使用之前

* Model类使用之前

```java
public class User implements Parcelable {
	
	private String name;
	private int age;
	/* N个属性 */
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
		dest.writeInt(age);
		/* writeN个属性 */
	}

	public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
		@Override
		public User createFromParcel(Parcel source) {
			User user = new User();
			user.setName(source.readString());
			user.setAge(source.readInt());
			/* readN个属性 */
			return user;
		}

		@Override
		public User[] newArray(int size) {
			return new User[size];
		}
	};
}

```

* Model类使用之后

```java
@Parcelable
public class User implements Parcelable {
	
	private String name;
	private int age;
	/* N个属性 */
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
}

```

如果属性很多的话，数据类型很多，就更麻烦了，使用ParcelableGenerator之后，一个注解搞定。

* 传递使用之前

```java
Intent intent = new Intent(this, ShowUserActivity.class);
// 传入Intent中
intent.putExtra("user", user);
startActivity(intent);
```
* 传递使用之后

```java
Intent intent = new Intent(this, ShowUserActivity.class);
// 调用PG将对象转换成Parcelable，传入Intent中
intent.putExtra("user", PG.createParcelable(user));
startActivity(intent);
```

唯一增加代码量的地方就是传递的时候，需要使用PG.createParcelable()，但这么几个字母的代码量微乎其微吧。

* 获取数据使用之前和之后是一模一样的，无需做任何处理

```java
// 直接获取原对象类型
User user = getIntent().getParcelableExtra("user");
		
// 获取属性值
user.getName();
user.getAge();
// ...
```

## 原理解说

刚开始有做这个想法的时候，也是被Parcelable的写法虐的不行，用Serializable效率低心里不爽，毕竟有更高效的方法，所以就想能不能把Parcelable简化，开始想到了动态代理的原理，生成一个代理类，继承自原类，在这个代理类里面实现Parcelable，写那些繁琐的代码，后来发现Android里面没有java中动态编译的API，然后就Google一下，查到了Apache的BCEL，看了一些文章，感觉太复杂，然后继续找其他方法，忽然间想到了butterknife，隐约想起butterknife好像是动态生成了一些类，$$ViewInjector这样后缀的，然后就去看他的源码，还好内容很少。
经过一番研究，发现butterknife使用的是APT(Annotation processing tool)，这里就不更详细的说，以后写一篇文章专门介绍APT，这里说一说基本原理。

首先注册@Parcelable注解类型，APT会扫描源码如果有这个注解就会通知一个Processor处理，这个Processor会获取到@Parcelable注解所修饰的类信息，然后创建一个java文件到Source folder，java文件中的类继承修饰的类，就是个代理类（这里这么叫），然后实现Parcelable接口，利用反射获取属性值Parcel.write()以及Parcel.read()。
调用PG.createParcelable()其实就是去寻找这个代理类，然后实例化代理类，将被代理类对象的值设置给代理类并返回。
