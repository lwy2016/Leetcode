## 1. JMM 
Java Memory Model Java内存模型
解决 并发过程中，如何处理可见性、原子性、有序性的问题
并发编程中两个关键问题：
a. 线程之间如何通信:  wait() notify() notifyAll()
	a)共享内存 - 隐式通信，读取共享状态
	b)消息传递 - 显示通信，发送消息
b. 线程之间如何同步： 
	同步是指，程序中用于控制不同线程之间操作发生的相对顺序的机制
	在共享内存的并发模型中，同步是显示做的 synchronized
	在消息传递的并发模型中，由于消息的发送必须在消息接收之前，所以同步是隐式的

## 2. 定位内存可见性问题
什么对象是内存共享的，什么不是

jpg

## 3. 
volatile： 原子性、可见性， 不能做到复合操作原子性，轻量级，开销小
1. 对于声明了volatile的变量进行写操作的时候，JVM会向处理器发送一条Lock前缀的指令。会把这个变量所在的缓存行写回到主内存
2. 在多处理器的情况下，保证各个处理器缓存一致性的特点，就会实现缓存一致性协议，其他线程嗅探到该共享变量已被修改，会使当前线程的缓存行失效，重新从主内存中读取共享变量的值

synchronized： 可重入锁、互斥性、可见性


Lock与synchronized的区别

## 1. synchronized锁什么时候释放
a. 获取锁的线程执行完了该代码块
b. 线程执行出现异常

jpg

## 2. synchronized的缺陷
不能实现读写分离

Lock可以主动去释放锁
synchronized 是被动释放锁

ReadWriteLock 读写锁 --> readLock 和 writeLock
ReentrantLock 可重入锁 
可中断锁
公平锁 等待时间越长获得锁的几率越大
ReentrantReadWriteLock 可重入读写锁

