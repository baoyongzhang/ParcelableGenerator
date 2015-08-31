ParcelableGenerator
===================
[ ![Download](https://api.bintray.com/packages/baoyongzhang/maven/ParcelableGenerator/images/download.svg) ](https://bintray.com/baoyongzhang/maven/ParcelableGenerator/_latestVersion)
## 介绍
ParcelableGenerator可以将任意对象转换为Parcelable类型，方便对象传输。

在Android中，对象的序列化一般有两种方式，一种是Serializable，一种是Parcelable。

* Serializable 在Java中就存在，效率较低。
* Parcelable 是Android中提供的，也是官方推荐的方式，效率比Serializable高很多。

虽然Parcelable效率高，但是使用起来比Serializable麻烦很多，很多人不使用Parcelable就是因为写法太麻烦，尤其是属性特别多的时候，我们要将每个属性Parcel.write()然后在Parcel.read()回来，相当繁琐，不如Serializable简单粗暴，直接有效。

ParcelableGenerator可以解决Parcelable使用麻烦的问题，让使用Parcelable的简单性可以和使用Serializable相媲美。


## 使用方法

例如我们有一个User类，用来保存用户的一些信息，我们需要使用@Parcelable修饰该类，注意@Parcelable修饰的类必须有公有无参构造方法。

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
intent.putExtra("user", PG.convertParcelable(user));
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

#### 对于继承: `@Parcelable` 自动作用于被继承的类, 子类需修饰, 但父类无需修饰. 比如:

```java
public class Base {
    private String str;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
```

注意, 上面的 `Base` 是没有修饰的 POD.

```java
@Parcelable
public class Child extends Base {
    private int i;

    public int getI() {
        return i;
    }

    public voiid setI(int i) {
        this.i = i;
    }
}
```

注意, 子类 `Child` 是被 `@Parcelable` 修饰的.

此时, 我们有如下代码:

```java
        Intent intent = new Intent(this, MainActivity.class);
        Child child = new Child();
        child.setStr("child");      // 基类成员
        child.setI(1234);           // 子类成员
        intent.putExtra("bean", PG.convertParcelable(child));
```
基类 `str` 和子类 `i` 两个字段均可被 Parcel.
#### 对于组合: 需要被组合的对象需 `@Parcelable` 修饰. 例如:
```java
@Parcelable
public class X {
    private String str;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
```

```java
@Parcelable
public class Y {
    private X x;

    public X getX() {
        return x;
    }

    public void setX(X x) {
        this.x = x;
    }
}
```

注意, `X` 和 `Y` 都需要 `@Parcelable` 修饰.

</br>

## 更新介绍

#### Version 2.0

* 修复BUG，使用基本数据类型包装类会出现问题等。
* 增加 @ParcelIgnore 注解，修饰在Model的Field上面，可以忽略该字段不进行序列化。
* 使用更加方便，当Model中的属性是其他对象，或者List中包含其他对象，该对象的类用 @Parcelable 声明之后无需加转换代码。

```java
// 当传递对象的属性包含其他对象，或者是List，而该对象或List中的对象不支持序列化，那么直接传递将会出现null
// 解决办法，将不支持序列化的类用@Parcelable修饰
// 例如一个教室对象
Classroom room = new Classroom();
// 教室中包含一个老师，Teacher类用@Parcelable修饰
Teacher teacher = new Teacher("teacherName");
// 将老师对象直接赋值给教室
room.setTeacher(teacher);
// 再例如，教室中包含很多学生，使用List保存，Student类用@Parcelable修饰
List<Student> students = new ArrayList<Student>();
// 直接创建Student对象添加到List中
students.add(new Student("stu1"));
students.add(new Student("stu2"));
students.add(new Student("stu3"));
room.setStudents(students);
// 传递教室对象，调用转换方法，此时内部会自动将Teacher、和List中的Student对象转为Parcelable类型并传递
intent.putExtra("classroom", PG.convertParcelable(room));
```

#### Version 1.1

* Sample程序表达更加清楚。
* 修改方法名createParcelable()为convertParcelable()，原方法@Deprecated 不影响原有代码。
* 增加PG.convert(Object)方法，与createParcelable()功能类似，只是返回值不同，convertParcelable()返回Parcelable类型，convert()返回类型与传入的对象类型一致，只是该对象已经支持序列化。

#### Version 1.0

* 将任意对象转换为Parcelable类型


## 在你的项目中使用

### Gradle

```
dependencies {
    provided 'com.baoyz.pg:compiler:2.1.1'
    compile 'com.baoyz.pg:core:2.1.1'
}
```
