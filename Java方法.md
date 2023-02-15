## Java

#### String

```java
public class Main {
    public static void main (String[] args) {
        String a = new String("hallo");
        a.toLowerCase();		// 全部转换成小写
        a.toUpperCase();		// 全部转换成大写
        a.trim();			    // 去掉空格
        
        String b = new String("hallo");
        a.compareTo(b);			// 按字典序比较两个字符串大 小
    }
}
```

## 常见问题

##### 类调用方法

**调用方 把参数传递给实例方法时，调用时 传递的值会按参数位置一一绑定**

修改外部的局部变量`n`，不影响实例`p`的`age`字段，原因是`setAge()`方法获得的参数，复制了`n`的值，因此，`p.age`和局部变量`n`互不影响。

**结论：基本类型参数的传递，是调用方值的复制。双方各自的后续修改，互不影响。**

```java
public class Main {
    public static void main(String[] args) {
        Person p = new Person();
        int n = 15; // n的值为15
        p.setAge(n); // 传入n的值
        System.out.println(p.getAge()); // 15
        n = 20; // n的值改为20
        System.out.println(p.getAge()); // 15还是20?     是15
    }
}

class Person {
    private int age;

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
```

注意到`setName()`的参数现在是一个数组。一开始，把`fullname`数组传进去，然后，修改`fullname`数组的内容，结果发现，实例`p`的字段`p.name`也被修改了！

**结论：引用类型参数的传递，调用方的变量，和接收方的参数变量，指向的是同一个对象。双方任意一方对这个对象的修改，都会影响对方（因为指向同一个对象嘛）。**

```java
public class Main {
    public static void main(String[] args) {
        Person p = new Person();
        String[] fullname = new String[] { "Homer", "Simpson" };
        p.setName(fullname); // 传入fullname数组
        System.out.println(p.getName()); // "Homer Simpson"
        fullname[0] = "Bart"; // fullname数组的第一个元素修改为"Bart"
        System.out.println(p.getName()); // "Homer Simpson"还是"Bart Simpson"?
    }
    // 输出  Homer Simpson     Bart Simpson
}

class Person {
    private String[] name;

    public String getName() {
        return this.name[0] + " " + this.name[1];
    }

    public void setName(String[] name) {
        this.name = name;
    }
}
```

```java
public class Main {
    public static void main(String[] args) {
        Person p = new Person();
        String bob = "Bob";
        p.setName(bob); // 传入bob变量
        System.out.println(p.getName()); // "Bob"
        bob = "Alice"; // bob改名为Alice
        System.out.println(p.getName()); // "Bob"还是"Alice"?
    }   // 输出 Bob   Bob
}

class Person {
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
//解释为什么上面的代码两次输出都是"Bob"。
/*
第一个：是string数组对象，数组对象里面，每一个都是一个对象的引用，即string对象的引用。因为对象赋值，是直接把当前对象的地址交给被赋值对象，所以第一个，被赋值的是一个string数组对象，所以两个数组对象指向同一个引用（引用数组的引用），所以第一个修改的时候，其实是修改了引用数组中某一个元素的引用，即数组元素发生了改变。

第二个：就是基础的引用，string对象是不可变类型，重新赋值就是申请另一块空间赋值给被赋值的引用。

第一个，是修改了具体的对象，第二个是，对string引用重新赋值，并没有涉及对象的修改。
1. 定义字符串时，bob存储了字符串"Bob"在内存中的地址

String bob = "Bob" // bob -> "Bob"
 2. 实例化对象p的时候，给p.name传入了bob 存储的地址，即"Bob"在内存中的地址

p.setName(bob)  // p.name -> "Bob"
 3.  给bob赋值后，bob指向了内存中新的字符串"Alice"的地址

bob = "Alice" // bob -> "Alice" 
 4. 此时p.name仍然指向 "Bob"在内存中的地址

// p.name -> "Bob"
数组：

1. 定义数组时，由于是String数组，所以fullname[0] 存储的实际上是字符串 "Homer"的地址；同理，fullname[1] 指向 字符串"Simpson" 的地址

String[] fullname = new String[] { "Homer", "Simpson" }; // fullname[0] -> "Homer"  

2. 实例化对象p的时候，传入了fullname指向的地址，所以name[0]指向fullname[0]在内存中的地址，而fullname[0] 又指向字符串"Homer"

p.setName(fullname); //p.name[0] -> fullname[0] -> "Homer"
3. 修改fullname[0]时，就是改变 fullname[0] 的指向，修改后为fullname[0]存储的是字符串"Bart"的地址

fullname[0] = "Bart" // fullname[0] -> "Bart"
4.  而此时p.name[0] 的指向并没有变，还是指向fullname[0]所在的地址，访问p.name[0] 时先在内存中找到fullname[0]，又根据fullname[0]中存储的地址信息访问到"Bart"

fullname[0] = "Bart" // p.name[0] -> fullname[0] -> "Bart"
*/
```

##### Integer

```java
public class Mainaa {
	public static void main(String[] args) {
		Integer a = 100, b= 100,c= 500,d=500;
		System.out.println(a==b);
		System.out.println(c==d);
	} // ture false; 
}
/*
对于两个非new生成的Integer对象，进行比较时，如果两个变量的值在区间-128到127之间，则比较结果为true，如果两个变量的值不在此区间，则比较结果为false
Integer 的 范围是 -128 ~ 127 在此范围内 会 先使用缓存区
java在编译Integer i1 = 127时，会翻译成Integer i1 = Integer.valueOf(127)
java会将[-128,127]之间的数进行缓存。Integer i1 = 127时，会将127缓存，Integer j2 = 127时，就直接从缓存中取，不会new了，所以结果为true
*/
```

```java
/*
由于Integer变量实际上是对一个Integer对象的引用，所以两个通过new生成的Integer变量永远是不相等的（因为new生成的是两个对象，其内存地址不同）
*/
Integer i = new Integer(100);
Integer j = new Integer(100);
System.out.print(i == j); //false
```

```java
/*
非new生成的Integer变量和new Integer()生成的变量比较时，结果为false。（因为 ①当变量值在-128~127之间时，非new生成的Integer变量指向的是java常量池中的对象，而new Integer()生成的变量指向堆中新建的对象，两者在内存中的地址不同；②当变量值不在-128~127之间时，非new生成Integer变量时，java API中最终会按照new Integer(i)进行处理（参考下面第4条），最终两个Interger的地址同样是不相同的）
*/
Integer i = new Integer(100);
Integer j = 100;
System.out.print(i == j); //false
```

```java
/*
Integer变量和int变量比较时，只要两个变量的值是向等的，则结果为true（因为包装类Integer和基本数据类型int比较时，java会自动拆包装为int，然后进行比较，实际上就变为两个int变量的比较）
*/
Integer i = new Integer(100);
int j = 100；
System.out.print(i == j); //true
```

int和Integer的区别
1、Integer是int的包装类，int则是java的一种基本数据类型 
2、Integer变量必须实例化后才能使用，而int变量不需要 
3、Integer实际是对象的引用，当new一个Integer时，实际上是生成一个指针指向此对象；而int则是直接存储数据值 
4、Integer的默认值是null，int的默认值是0
