# 第二章 装配bean
## 声明bean
## 构造器注入和Setter方法注入
## 装配bean
## 控制bean的创建和销毁

在Spring中，对象无需自己查找或创建与其所关联的其他对象。相反，容器负责把需要相互协作的对象引用赋予各个对象。
创建应用对象之间协作关系的行为通常称为装配（wiring），这也是依赖注入（DI）的本质。
Spring从两个角度来实现自动化装配：
    组件扫描(component scanning)Spring会自动发现应用上下文中所创建的bean
    自动装配(autowiring)Spring自动满足bean之间的依赖

    