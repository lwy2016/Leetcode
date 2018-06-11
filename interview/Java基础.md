重载就是一句话：同名不同参，返回值无关。
覆盖/重写：同名同参

String类重写了equlas方法，类型不同返回false
```
public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
        if (anObject instanceof String) {
            String anotherString = (String) anObject;
            int n = value.length;
            if (n == anotherString.value.length) {
                char v1[] = value;
                char v2[] = anotherString.value;
                int i = 0;
                while (n-- != 0) {
                    if (v1[i] != v2[i])
                            return false;
                    i++;
                }
                return true;
            }
        }
        return false;
    }
```

Thread.sleep() 和 Object.wait(),都可以抛出 InterruptedException。这个异常是不能忽略的,因为它是一个检查异常(checked exception)

堆区：只存放类对象，线程共享； a是类中的成员变量，存放在堆区
方法区：又叫静态存储区，存放class文件和静态数据，线程共享;
栈区：存放方法局部变量，基本类型变量区、执行环境上下文、操作指令区，线程不共享; b、c都是方法中的局部变量，存放在栈区

##volatile关键字:
volatile关键字用在多线程同步中，可保证读取的可见性
JVM保证从主内存加载到线程工作内存的值是最新的
volatile能禁止指令重排序
但volatile不能保证原子性，也就不能保证线程安全

## 线程安全的集合对象： 
喂，SHE。 Vector, Stack, Hashtable, Enumeration.

## hashmap 和 hashtable 底层原理：


