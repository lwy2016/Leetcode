
在Spring中，对象无需自己查找或创建与其所关联的其他对象。相反，容器负责把需要相互协作的对象引用赋予各个对象。
创建应用对象之间协作关系的行为通常称为装配（wiring），这也是依赖注入（DI）的本质。
Spring从两个角度来实现自动化装配：
    组件扫描(component scanning)Spring会自动发现应用上下文中所创建的bean
    自动装配(autowiring)Spring自动满足bean之间的依赖

## IoC

IoC -- Inverse of Controller 控制反转 -- 将对象的控制权反转给Spring，可以解决程序高耦合问题。
IoC (Beans, core, Context, spEL) spring expression language,  logging日志

DI -- Dependency Injection 依赖注入  -- 在Spring框架负责创建Bean对象时，动态的将依赖对象注入到Bean组件中 
注入方式:
    构造方法注入:
    Setter方法注入:
    p名称空间注入：(了解)
    SeEl注入：(了解)
为什么要使用依赖注入
 - 传统的代码，每个对象负责管理与自己需要依赖的对象，导致如果需要切换依赖对象的实现类时，需要修改多处地方。同时，过度耦合也使得对象难以进行单元测试。
 - 依赖注入把对象的创造交给外部去管理,很好的解决了代码紧耦合（tight couple）的问题，是一种让代码实现松耦合（loose couple）的机制。
 - 松耦合让代码更具灵活性，能更好地应对需求变动，以及方便单元测试。

为什么要使用Spring
 - 使用Spring框架主要是为了简化Java开发（大多数框架都是为了简化开发），它帮我们封装好了很多完善的功能，而且Spring的生态圈也非常庞大。
 - 基于XML的配置是Spring提供的最原始的依赖注入配置方式，从Spring诞生之时就有了，功能也是最完善的

Constructor注入 vs Setter注入
 - Constructor注入能够强制要求调用者注入构造函数中的所有参数，否则在容器初始化时就会失败；但是如果要注入的对象过多，就会导致构造函数过于庞大。
 - Setter注入，类似于Builder模式，将原本庞大的构造函数，拆解为了一个小的构造函数和许多个set方法。setter注入不能保证对象一定会被注入，但是可以使用@Required注解，强制要求使用者注入对象，否则在容器初始化时就会报错

注解：
@Component(value="")
@Controller(value="")
@Service(value="")
@Repository(value="")
@Value(value="") 属性上面
@Autowired  按照类型自动装配，不是按名称
@Qualifier(value="")  按照名称注入
@Resource(name="")  Java的注解，Spring支持
@Scope(value="prototype/singleton") 单例多例
@PostContruct   初始化方法前
@PreDestroy     销毁方法前


## AOP
面向切面，通过 预编译方式 和 运行期动态代理 实现程序功能的统一维护的一种技术
AOP采取横向抽取机制，取代了传统纵向继承体系重复性代码（性能监视，事务管理，安全检查，缓存）
可以在不修改源代码的前提下，对程序进行增强

JDK动态代理，有接口--运行期间
cglib技术，代理模式，没有借口，生成类的子类的方式--预编译期

Joinpoint(连接点) -- 那些被拦截到的点, Spring中，这些点指的是方法 
Pointcut(切入点)  -- 我们要对哪些Joinpoint进行拦截的定义
Advice(通知/增强) -- 指拦截到Joinpoint之后所要做的事情就是通知，分为，前置通知，后置通知，异常通知，最终通知，环绕通知(切面要完成的功能)
Target(目标对象)  -- 代理的目标对象
Weaving(织入)    --  把增强应用到目标对象来创建新的代理对象的过程
Proxy(代理)      -- 一个类被AOP织入增强后，就产生了一个代理类
Aspect(切面)     -- 是切入点与通知的结合

xml配置切面
注解
JDBC模板
事务管理

事务的特性：原子性，一致性，隔离性，持久性
事务的传播行为：解决的是业务层之间的调用，事务的管理操作

编程式事务管理
声明式事务管理
    XML  配置平台事务管理器，配置通知，配置AOP(传播行为)   
    注解  配置平台事务管理器，开启事务的注解 


-------
Maven

1.Maven的依赖管理    源码项目很小，jar包repository单独存放
2.项目的一键构建      项目的清理，编译，测试，运行，打包，安装，部署都交给mvn执行

mvn clean         清理target目录(编译后的项目) 
mvn compile       编译
mvn test          测试
mvn tomcat:run    运行
mvn package       打包
mvn install       安装(打成jar包或war包发布到本地仓库)

本地仓库的配置  config/settings.xml -- localReposity
生命周期  clean, default, site 三种


------
MyBatis 
#{} 占位符 传参是基本类型则变量名称可以随意写，
${} 拼接符 变量名必须是 value，使用拼接符有可能导致SQL注入（在页面输入的时候可以加入校验，不可输入SQL关键字，不可输入空格）
原生Dao实现（需要接口和实现）
动态代理方式（只需要接口）
mapper接口代理实现编写规则（实际项目中使用的就是这种，突泉，横山）
1.映射文件中namespace要等于接口的全路径名称
2.映射文件中SQL语句id要等于接口的方法名称 
3.映射文件传入参数类型要等于接口方法的传入参数类型 
4.映射文件返回结果集类型要等于接口方法的返回值类型
映射文件 
    传参类型 parameterType 
    返回结果集 resultType 
与Hibernate区别 
    Hibernate是一个标准orm框架，重量级，高度封装，使用起来不写SQL，降低开发周期，但无法进行SQL优化，
    应用场景：OA(办公自动化系统)，ERP（企业的流程系统），还有一些政府项目，用户量不大，并发量小
    MyBatis不是一个orm框架，是对jdbc的轻量封装，SQL语句可以优化，编码量较大
    应用场景：互联网项目，比如电商，P2P等，用户量大，并发高

输入映射
输出映射


-------Spring MVC 



--------
Spring Boot

RESTful API设计
请求类型     URL              功能说明
GET         /users          查询用户列表
POST        /users          创建一个用户
GET         /users/id       根据id查询一个用户
PUT         /users/id       根据id更新一个用户
DELETE      /users/id       根据id删除一个用户



0312 -- Spring1
0313 -- Spring2
0314 -- Spring3
0315 -- Maven1
0316 -- Maven2
0317 -- MyBatis1
0318 -- MyBatis2 
0319 -- SpringMVC1

