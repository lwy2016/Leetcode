import java.util.*;

public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        while (sc.hasNext()) {
            String str = sc.nextLine();
            String[] arr = str.split(" ");
            StringBuilder sb = new StringBuilder();
            for (int i = arr.length - 1; i > 0; i--) {
                sb.append(arr[i] + " ");
            }
            sb.append(arr[0]);
            
            System.out.println(sb.toString());
        }
    }
}

归并排序
-------------------------------------------------------------
public void sort(int[] nums, int low, int high) {
    if (low >= high) return ;

    int mid = (high - low) / 2 + low;
    sort(nums, low, mid);
    sort(nums, mid + 1, high);

    merge(nums, low, mid, high);
}

public void merge(int[] nums, int low, int mid, int high) {
    int[] copy = new int[nums.length];
    for (int i = 0; i < nums.length; i++) {
        copy[i] = nums[i];
    }

    int i = low, j = mid + 1; 
    for (int k = low; k <= high; k++) {
        if (i > mid) {
            nums[k] = copy[j++]; 
        } else if (j > high) {
            nums[k] = copy[i++];
        } else if (copy[i] < copy[j]) {
            nums[k] = copy[i++];
        } else {
            nums[k] = copy[j++];
        }
    }
}

快速排序
---------------------------------------------------------
public void quickSort(int[] nums, int left, int right) {
    if (left >= right) return ;

    int pivot = helper(nums, left, right);
    quickSort(nums, left, pivot - 1);
    quickSort(nums, pivot + 1, right);
}

public int helper(int[] nums, int left, int right) {
    int pivot_value = nums[left];

    int l = left + 1;
    int r = right;

    while (l <= r) {
        while (l <= r && nums[l] < pivot_value) l++;
        while (l <= r && nums[r] >= pivot_value) r--;

        if (l > r) break;
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }
    int t = nums[left];
    nums[left] = nums[r];
    nums[r] = t;

    return r; 
}


单例模式
-------------------------------------
懒汉式 - 多线程不安全
public class Singleton {
    // 懒加载，多线程不安全
    private static Singleton instance;
    private Singleton(){};

    public static Singleton getInstance() {
        if (instance == null) {      
            instance = new Singleton();
        }
        return instance;
    }  
} 
懒汉式 - 多线程安全
public class Singleton {
    // 线程安全，但并不高效，任何时候只能有一个线程调用 getInstance() 方法，但是同步操作只需要在第一次调用时才需要
    // 即 第一次创建 单例实例对象时，这就引出了双重检验锁
    private static Singleton instance;
    private Singleton(){};

    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
双重检验锁 - 需声明为 volatile 变量
public class Singleton {
    // 同步块 内 外 各检查一次，因为可能有多个线程进入同步块 外 的if，如果在 同步块 内 不进行二次检验，就有可能生成多个实例
    // 需要将 instance变量 声明为 volatile, 主要在于  instance = new Singleton()不是一个原子操作，分3步
    // 1.给 instance分配内存，2.用Singleton的构造函数来初始化成员变量，3.将instance对象指向分配的内存空间
    // JVM编译器，指令重排优化，最终有可能是1-2-3或1-3-2，如果是后者，3执行完，2未执行之前被其他线程抢占，这时instance已经
    // 是 非null了（但是却没有初始化）
    // volatile 禁止指令重排优化，在volatile变量的赋值操作后面，会有一个内存屏障，读操作不会被重排序到内存屏障之前
    // 也就是 先行发生原则--对于一个volatile变量的写操作都先行发生于后面对这个变量的读操作
    private static Singleton volatile instance;
    private Singleton(){};

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized(Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
饿汉式 - 不是懒加载, final
public class Singleton {
    private static final Singleton instance = new Singleton();  // 类加载时就初始化
    private Singleton(){};

    public static Singleton getInstance() {
        return instance;
    }
}
静态内部类
public class Singleton {
    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
    } 
    private Singleton(){};

    public static final Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}

汉诺塔
https://www.kancloud.cn/freya001/leetcode/277804
实现这个算法分为三个步骤：
1. 把 n - 1 个盘子 由A移到B
2. 把 第 n 个盘子 由A移到C
3. 把 n - 1 个盘子 由B移到C
(1)中间的一步是把最大的一个盘子由A移到C上去；
(2)中间一步之上可以看成把A上n-1个盘子通过借助辅助塔（C塔）移到了B上，
(3)中间一步之下可以看成把B上n-1个盘子通过借助辅助塔（A塔）移到了C上；


static int m = 0;
public static void move(int disk, char M, char N) {
    System.out.println("第" + (++m) + "次移动: 把 " + disk + " 号圆盘从 " + M + " -> 移到 -> " + N);
}
public static void hanoi(int n, char A, char B, char C) {
    if (n == 1) {
        move(1, A, C);
    } else {
        hanoi(n - 1, A, C, B);
        move(n, A, C);
        hanoi(n - 1, B, A, C);
    }
}

分布式锁
----------------------参见代码
使用到的命令
setnx key val 当且仅当 key 不存在时，set一个key的val字符串，返回1，若 key 存在，则什么都不做，返回 0
expire key timeout   为 key 设置一个超时时间，单位为 second秒，超过这个时间 锁 会自动释放，避免死锁
delete key 删除 key 
实现思想
1. 获取锁的时候，使用 setnx 加锁，并使用 expire 命令为锁添加一个超时时间，超过该时间则自动释放锁，锁的 value 值是一个随机生成的UUID，通过UUID在释放锁的时候进行判断
2. 获取锁的时候还设置一个获取的超时时间，若超过这个时间则放弃获取锁
3. 释放锁的时候，通过UUID判断是不是该锁，若是该锁，则执行delete进行锁释放
set()方法只会导致两种结果: 
1. 当前没有锁(key不存在)那么就进行加锁操作，并对锁设置有效期，同时value表示加锁的客户端
2. 已有锁存在，不做任何操作
释放锁：
watch监视，通过前面返回的value值判断是不是该锁，若是该锁，则加事务，delete lockKey,释放监视


网站分析
-----------------------
发布文章
对文章进行访问次数统计
按文章的发布时间或文章的访问次数进行排序显示

文章信息包括 标题、作者、访问数 使用HASH来存储每种信息及其对应值的映射
使用命名空间的方式实现将不同类型的数据放在一起，键名的前面部分存储命名空间，后面部分存储ID，如 
article:92761  --- hash
title   go to statement
link    http://google.com
poster  user:83271
time    1331382699.33
votes   528

统计访问次数
vote字段+1操作，建立文章的已访问用户集合来记录
voted:10048 --- set
user:12335
user:15651
user:15452
user:53651
user:87634

对文章进行排序
为了按发布时间和访问次数进行排序，可以建立一个文章发布时间 和 一个文章访问次数的 有序集合
time: --- zset 
article:92761  1331382699.33
article:92767  1331382623.31
article:92688  1331382329.65

score: --- zset 
article:92761  10086
article:92767  23231
article:92688  23623


重载就是一句话：同名不同参，返回值无关。
覆盖/重写：同名同参

String类重写了equlas方法，类型不同返回false

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
Vector, Stack, Hashtable, Enumeration.

## hashmap 和 hashtable 底层原理：

## 异常
java运行时异常是可能在java虚拟机正常工作时抛出的异常。
java提供了两种异常机制。一种是运行时异常(RuntimeExepction)，一种是检查式异常(checked execption)。
检查式异常：我们经常遇到的IO异常及sql异常就属于检查式异常。对于这种异常，java编译器要求我们必须对出现的这些异常进行catch。所以，面对这种异常不管我们是否愿意，只能自己去写一堆catch来捕捉这些异常。
运行时异常：我们可以不处理。当出现这样的异常时，总是由虚拟机接管。比如：我们从来没有人去处理过NullPointerException异常，它就是运行时异常，并且这种异常还是最常见的异常之一。

OutOfMemoryError            
NullPointerException        空指针异常   -->链表访问空节点
IndexOutofBoundsException   数组越界异常
IllegalArgumentException    既然是继承就不得不考虑final的问题。我们知道final类型不能有子类，所以CGLIB不能代理final类型,否则异常
ArithmeticException         分母是0
ClassNotFoundException      Class.forName("类的全限定名")时，catch中
ConcurrentModificationException  fail-fast机制 当某个线程遍历list的过程中，list的内容被另一个线程改变了，就会抛出该异常


---CheckedException
ClassNotFoundException   
IOException
SQLException   


##动态代理 -- com.lwy94.dynproxy
静态代理
JDK动态代理
cglib动态代理 https://blog.csdn.net/javazejian/article/details/70768369
JDK原生动态代理是Java原生支持的，不需要任何外部依赖，但是它只能基于接口进行代理；
CGLIB通过继承的方式进行代理，无论目标对象有没有实现接口都可以代理，但是无法处理final的情况。

## 生产者消费者 -- com.lwy94.pruducerConsumer 
生产者和消费者在同一时间段内共用同一个存储空间，生产者往存储空间中添加产品，消费者从存储空间中取走产品，当存储空间为空时，消费者阻塞，当存储空间满时，生产者阻塞。
https://juejin.im/entry/596343686fb9a06bbd6f888c
wait(),notify()
ReentrantLock
BlockingQueue
Semaphore
管道输入输出流