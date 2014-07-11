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

## 更新介绍

#### Version 1.1

* Sample程序表达更加清楚。
* 修改方法名createParcelable()为convertParcelable()，原方法@Deprecated 不影响原有代码。
* 增加PG.convert(Object)方法，与createParcelable()功能类似，只是返回值不同，convertParcelable()返回Parcelable类型，convert()返回类型与传入的对象类型一致，只是该对象已经支持序列化。使用场景直接上代码，如下。

```java
// 当传递对象的属性包含其他对象，或者是List，而该对象或List中的对象不支持序列化，那么直接传递将会出现null
// 解决办法，将不支持序列化的类用@Parcelable修饰，在对象赋值的时候调用PG.convert()方法转换一下即可。
// 例如一个教室对象
Classroom room = new Classroom();
// 教室中包含一个老师，Teacher类用@Parcelable修饰
Teacher teacher = new Teacher("teacherName");
// 将老师对象赋值给教室，调用PG.convert()对象，返回的还是Teacher对象，但是该对象已经支持序列化。
room.setTeacher(PG.convert(teacher));
// 再例如，教室中包含很多学生，使用List保存，Student类用@Parcelable修饰
List<Student> students = new ArrayList<Student>();
// 在给List添加学生对象的时候，调用PG.convert()方法转换
students.add(PG.convert(new Student("stu1")));
students.add(PG.convert(new Student("stu2")));
students.add(PG.convert(new Student("stu3")));
room.setStudents(students);
// 传递教室对象
intent.putExtra("classroom", PG.convertParcelable(room));
```

#### Version 1.0

* 将任意对象转换为Parcelable类型


## 在你的项目中使用

### 导入jar包

将jar包导入到项目中，jar包只有10Kb大小，相当轻巧。
<p>
<a href="https://github.com/baoyongzhang/ParcelableGenerator/raw/master/pg1.2.jar" alt="download jar">
<font size="32px">Download jar</font>
<a>
</p>

### 环境配置

不知道各位是否用过butterknife，本项目的原理与butterknife类似，使用的APT，需要配置AnnotationProcessing

#### Eclipse配置方法

* 项目右键->Properties->Java Compiler->Annotation Processing	开启 Enable project specific settings

![](https://raw.githubusercontent.com/baoyongzhang/test_pages/gh-pages/pg/image-1.jpg)

* 然后展开Annotation Processing->Factory Path	开启Enable project specific settings

![](https://raw.githubusercontent.com/baoyongzhang/test_pages/gh-pages/pg/image-2.jpg)

* 点击AddJars 添加pg.jar

![](https://raw.githubusercontent.com/baoyongzhang/test_pages/gh-pages/pg/image-3.jpg)

* 最后点一直OK，弹出对话框点YES即可。
