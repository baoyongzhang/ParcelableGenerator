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

例如我们有一个User类，用来保存用户的一些信息，我们需要使用@Parcelable修改该类

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

