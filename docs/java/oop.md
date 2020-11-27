

# 面向对象

简单介绍面向对象中的 几大特性

# 继承

## 继承的概述

> 可以使子类具有父类的属性和方法，还可以在子类中重新定义，追加属性和方法
>
> 语法 `public class 子类名 extends 父类名 {}`
>
> 看下面两个类，狗类、猫类。他们都有相同的属性 （名字和年龄），还有相同的方法（设置名字和年龄），都是属于动物类的共有特性
>
> 所以他们可以将 共有的属性 抽离出来 做成一个类，通过继承的形式


## 继承的好处和弊端
> 好处：提高代码的复用性和降低代码冗余
> 
> 弊端：因为Java是单继承，只能继承一个父类。而且会造成代码的耦合，削弱子类的独立性。

## 关键字super 和 this
> 在一个类中 使用 `this.age` 代表的是获取当前 类中的 叫 age 的成员变量
> 
> 当我们需要去获取父类中定义的成员变量时候，通过 `super.age` 来获取
>

|      关键字      | 访问成员变量 | 访问构造方法  |     访问成员方法     |
| :--------------: | :----------: | :-----------: | :------------------: |
| `this` 获取本类  |  `this.age`  | `this(args)`  | `this.方法名(args)`  |
| `super` 获取父类 | `super.age`  | `super(args)` | `super.方法名(args)` |



## 继承中变量的访问特点

> 子类的局部范围（方法内部）-> 子类成员变量（在子class中声明的）-> 父类成员变量（在父class中声明的）

## 继承中构造方法的访问特点

> 子类中的所有构造方法默认都会访问父类中的无参构造方法， `无论是调用子类的无参还是有参构造方法`
>
> 因为子类会去继承父类的中的数据，可能还会去使用父类中的数据，所以，`在子类初始化之前，一定要先完成父类数据的初始化`
>
> `每一个子类的构造方法的第一条语句默认都是：super()` ，所以就会默认访问父类的无参构造方法
>
> 如果在父类中没有默认给出无参构造方法的情况下  在子类中调用 有无带参构造方法，默认都会去触发父类的无参构造方法，所以会报错
>
> 如果想解决，可以在子类的构造方法中 通过 `super(args)` 手动调用 父类的带参构造方法 实现
>
> 为了避免这些问题，所以一般在父类中都构建两个构造方法

## 继承中变量的访问特点

> 当我在创建 类的实例后，可以调用该类和其父类上的方法
>
> 流程：子类范围 -> 父类范围
>
> 当子类和父类中都存在方法时候，只会调用子类的方法，当子类没有的时候，才会去父类中找，一直找到基类 Object

``` java
// 动物类

public class Animal {
    private String name;
    private int age;

    // 无参构造方法
    public Animal() {}

    // 带参构造方法
    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
```

``` java
// 猫类
public class Cat extends Animal {
    public Cat() { }

    public Cat(String name, int age) {
        super(name, age);
    }

    public void catchMouse() {
        System.out.println("捉老鼠");
    }
}

```


``` java
// 狗类
public class Dog extends Animal {
    // 实现默认构造方法
    public Dog() {}

    // 带参构造方法 将 参数传给基类
    public Dog(String name, int age) {
        super(name, age);
    }

    // 狗 类的方法
    public void wangWang() {
        System.out.println("汪汪汪");
    }
}


```

_______________



## 方法的重写

### 重写的概述

> 子类中出现了和父类一摸一样的方法声明
>
> 当子类需要父类的功能，而功能主体子类有自己特有内容时，可以重写父类中的方法，这样，即沿袭了父类的功能，又定义了子类特有的内容
>
> 在重写的方法前 使用 `@Override` 做重写声明
>
> 如果还需要去执行父类的方法 可通过 `super.方法(args)` 调用
>
> 重点： 子类只能去重写父类中 公共的方法 （`public` 和 `protect`），而使用了 `private` 不能被重写
>
> 子类重写的父类的方法 `访问权限不能比父类的低` （public  -> 默认 -> 私有）

#  多态

> 同一个对象，在不同时刻表现出来的不同形态
>
> 多态的形式：
>
> - 具体类多态、抽象类多态、接口多态
>
> 举例：猫
>
> - 我们可以说猫是猫：Cat cat= new Cat();
> - 我们也可以说猫是动物：Animal animal = new Cat();
> - 这里猫在不同的时刻表现出来了不同的形态，这就是多态

## 多态的前提和体现

> 1. 有继承 /  实现关系 
> 2. 有方法重写
> 3. 有父类引用指向子类对象  `Animal animal = new Cat();`

## 多态中成员的访问特点

> - `成员变量：编译看左边，执行看左边`
>
> - `成员方法：编译看左边，执行看右边`
> - 多态的形式 `不能访问 具体一个子类中特有的方法 (狗能吃屎，人不能像狗一样吃屎)`
>
> 为什么成员变量和成员方法的访问不一样呢？
>
> 因为成员方法有重写，而成员变量没有

``` java
// Animal 类
public class Animal {
    public int age = 3;
    public void eat() {
        System.out.println("动物吃东西");
    }
}

// 猫类
public class Cat extends Animal {		// 多态的体现一： 有继承
    public int age = 5;
    public int weight = 1;
    @Override
    public void eat() {
        // 多态的体现二： 重写父类的 方法
        System.out.println("猫吃鱼");
    }

    public void miaomiao() {
        System.out.println("猫~喵喵喵");
    }
}

// 测试类
public class Demo {
    public static void main(String[] args) {
        // 多态的形式 创建一个 由父类引用指向子类对象 的对象
        Animal cat =  new Cat();

        // 虽然我们在内存中看到的是dog 但是在外部看到的是 Animal
        // 所以以多态的形式访问成员变量的时候 访问的是父类中的  编译要看左边 运行也要看左边
        System.out.println(cat.age);    // 多态访问成员变量  编译看左边 运行也是看左边 所以输出的是父类的成员变量
//        System.out.println(dog.weight);     // 父类没有该成员变量 编译报错

        cat.eat();      // 多态访问成员方法 编译看左边  运行看右边  因为在子类中有重写  所以调用的是 子类中的方法
//        cat.miaomiao();  // 父类没有该成员方法 编译报错
    }
}



```

## 多态中的转型

> 向上转型：
>
> - 从子到父
> - 父类引用 指向 子类对象
>
> 向下转型：
>
> - 从父到子
> - 父类引用转为子类对象

``` java
Animal dog = new Dog(); // 向上转型  将一个狗类提升成一个动物类

Dog dog2 = (Dog)dog;	// 向下转型 从一个动物类的狗 强行 转换成狗类 
```



**进阶写法**

``` java
// 动物类
public class Animal {
    public void eat() {
        System.out.println("动物吃东西");
    }
}

// 猫类
public class Cat extends Animal {
    public String name = "蓝猫";

    public String getName() {
        return name;
    }

    @Override
    public void eat() {
        System.out.println("猫吃鱼");
    }
}



/**
 * 动物操作类
 */
public class AnimalOperator {
    // 当存在多个 类 需要创建n个对应的方法去执行 这显然不现实
    // 我们发现 无论是 dog 还是 cat 它们都是继承自 Animal 类
//    public void useAnimal(Dog dog) {
//         // 实际上传的参数就是这个 Dog dog = new Dog();
//        System.out.println("动物的名字是： "+ dog.getName());
//        dog.eat();
//    }
//
//    public void useAnimal(Cat cat) {
//        System.out.println("动物的名字是： "+ cat.getName());
//        cat.eat();
//    }

    // 所以我们只用去封装一个方法 参数就是 它们的父类 Animal
    public void useAnimal(Animal animal) {
        // 传过来的实际上就是  Animal dog = new Dog();
        // 以多态的形式传参  编译看左边 执行看右边
        // Animal 类中没有 getName 方法 编译报错
//        System.out.println("动物的名字是： "+ animal.getName());   //
        // 左边 Animal 类中有 eat 方法  编译通过   执行看右边  右边Dog类中有重写 该方法 所以执行的是Dog类中的方法
        animal.eat();
        // 但是多态不能去访问子类特有的方法  如果需要调用则需要通过转型
//        animal.watchDoor();
    }
}

// 测试类
public class TestDemo {
    public static void main(String[] args) {
        // 创建动物操作类的对象 调用方法
        AnimalOperator ao = new AnimalOperator();
        // 创建一个 dog 类型的变量
        Dog dog = new Dog();
        // 然后将 new 出来的 dog 以参数的形式传给 动物操作类
        ao.useAnimal(dog);
        
        Cat cat = new Cat();
        ao.useAnimal(cat);
        // 解决多态无法调用 子类方法问题
        Animal dog2 = new Dog();
        dog2.eat();
        // 编译看左边 左边 Animal 类 没有 watchDoor 方法 报错
//        dog2.watchDoor();

        // 使用向下转型法  将父类类型 转成 子类类型 这样就可以使用子类的方法了
        Dog dog3 = (Dog)dog2;
        dog3.watchDoor();
    }
}
```

----



# 抽象类

> 抽象类是对事物的抽象
>
> 在之前的 动物类中 声明了动物 `eat()` 的方法。但是我们却给了一个具体的实现（该方法中存在执行代码）
>
> 在抽象概念中，我们应该只是给出 一个 `eat`的声明，不给方法体，由具体的类实现而去具体实现该方法
>
> 在一个抽象类中  可以存在抽象方法和非抽象方法
>
> 当 `存在抽象方法 或者 方法没有方法体` 则这个类就需要声明为抽象类
>
> 抽象类不是具体的，不能直接去 new 创建对象  ~~`Animal animal = new Animal ();`~~
>
> 需要通过 `多态` 的形式创建对象，通过`继承的形式 创建一个 该类的子类 在子类中去实现抽象的方法`，然后再去 `new` 这个子类 （抽象类多态）
>
> `作为一个抽象类的子类 要么通过重写抽象方法，要么把自己也设为抽象类`

## 语法

> 关键字：`**abstract**` 
>
> - 抽象类：`public abstract class Animal {}` 
> - 抽象方法：`public abstract void eat();`

## 抽象类成员特点

> - 成员变量
>   可以是变量
>
>   也可以是常量
>
> - 构造方法
>
>   有构造方法，但是不能实例化
>
> 那么，构造方法的作用是什么呢?
>
> ​	用于子类访问父类数据的初始化成员方法
>
> ​	可以有抽象方法：限定子类必须完成某些动作
>
> ​	也可以有非抽象方法：提高代码复用性

``` java
// 抽象 动物类
public abstract class Animal {
    private String name;
    private int age = 10;
    private final String cate = "动物";

    public Animal() { }
    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }
    // 当一个类 中存在抽象方法 或者 方法没有方法体 则这个类就需要声明为抽象类
    public abstract void eat();
    public void sleep() {
        System.out.println(getName() + " 喜欢 睡觉");
    }
    public void show() {
        System.out.println(getName() +" 是一个"+ cate+ "  今年 "+age+" 岁了");
    }
    public String getName() { return name;}
    public void setName(String name) { this.name = name;}
    public int getAge() { return age;}
    public void setAge(int age) { this.age = age; }
    public String getCate() { return cate;}
}

// 抽象类的子类 
// 抽象类的子类 要么重写父类的方法  要么转换成抽象类 在 class 前加入 abstract 关键字
public class Cat extends Animal {
    public Cat() {}

    public Cat(String name, int age) {
        super(name, age);
    }

    @Override
    public void eat() {
        System.out.println(getName() + " 吃的东西是 鱼");
    }
}

// 测试类
public class AnimalDemo {
    public static void main(String[] args) {
//        Animal animal = new Animal();       // 报错 Animal是一个抽象类 不是具体的 所以不能创建对象
        // 如果需要实例化 则需要参照多态的方式  创建一个子类
        Animal animal = new Dog();
        animal.eat();
        animal.sleep();
        animal.show();
		// 使用无参构造方法 
        Animal dog = new Dog();
        dog.setName("小金毛");
        dog.setAge(3);
        dog.show();
        dog.eat();
        // 使用带参构造方法
        Animal dog2 = new Dog("柴犬",1);
        dog2.show();
        dog2.eat();
        dog2.sleep();

        Animal cat = new Cat("美短",1);
        cat.show();
        cat.sleep();
        cat.eat();
    }
}
```

----

# 接口

## 接口概述

> 接口就是一种公共的规范标准，`是对行为的抽象`，只要符合规范标准，大家都可以通用
>
> 一个类可以继承多个接口
>
> `**接口不能有构造方法**`，在实现的类中调用的`super` 其实是使用的是`Object` 中的构造方法
>
> 接口也是抽象的，不能直接被实例化，也是需要对方法进行重写，不然就得加上 `abstract` 将该类设置成抽象的
>
> Java中的接口更多的是体现在对行为的抽象

## 语法

``` java
public interface 接口名 {}		// 声明接口
public class Dog implements 接口名 {} // 实现接口
```

## 接口的成员特点

> 接口里面的成员变量 默认是被 final 修饰的常量 不能再修改  而且还是被 static 修饰的 可以通过 `接口名.属性` 获取
>
> `int number = 0;` 等同于 ~`public static final`~` int number = 0; ` 
>
> `**接口里面只能有 抽象方法**` 不能有非抽象方法
>
> `void eat;`  等同于 ~`public abstract`~ `void eat();`

``` java

```



-----

# 类和接口的关系

## 类和类的关系

> 继承关系，只能单继承，但是可以多层继承

## 类和接口的关系

>实现关系，可以单实现，也可以多实现，还可以在继承一个类的同时实现多个接口

## 接口和接口的关系

>`继承关系，可以单继承，也可以多继承`

## 抽象类和接口的区别

> 成员区别：
>
> - 抽象类： 包含 变量、常量、构造方法、抽象和非抽象方法
> - 接口：只包含 常量、抽象方法
>
> 关系区别：
>
> - 类与类： 继承、单继承
> - 类与接口：实现、可以单实现、也可以多实现
> - 接口与接口： 继承、单继承、多继承
>
> 设计理念和区别
>
> - 抽象类： 对类抽象，包括属性和行为
> - 接口： 对行为进行抽象，主要是行为

