## MyBatis中的分页插件配置及使用

```sql
PageHelper插件并不是mybatis中原有的，这是一个国内开发大佬自己编写的一个功能插件。
我们在sql语句中希望进行分页查询，使用的是limit关键字：
select * from t_user order by age limit (n-1)3,3;
其中n表示查询第几页，3表示一页显示三行数据
第pageNo页：（pageNo-1）*pageSize，pageSize

--我们在mybatis中使用PageHelper插件，就可以实现查询数据的分页功能
具体操作如下：
```

### 在项目d-maven-dynamic-sql-mybatis04中进行测试

### 1、添加PageHelper插件的maven依赖，Resolve一下POM文件

```xml
<!--PageHelper依赖-->
    <dependency>
      <groupId>com.github.pagehelper</groupId>
      <artifactId>pagehelper</artifactId>
      <version>5.1.10</version>
    </dependency>
```

### 2、在核心配置文件中配置PageHelper的插件，注意要在环境属性的上方配置插件属性

```xml
...... 
<!--配置插件-->
    <plugins>
        <plugin interceptor="com.github.pagehelper.PageInterceptor"></plugin>
    </plugins>

    <!--environments是环境配置，是复数说明下面可以配置多个环境
        所以default属性的值表示这个配置文件用下面的哪一套数据库配置信息
        这里使用development1
    -->
    <environments default="development1">
        <!--第一套数据库连接信息配置，唯一标识id叫development-->
        <environment id="development1">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="${jdbc.driver}"/>
                <property name="url" value="${jdbc.url}"/>
                <property name="username" value="${jdbc.username}"/>
                <property name="password" value="${jdbc.password}"/>
            </dataSource>
        </environment>
    </enviroments>
.......
```

### 3、在获取SqlSession对象和调用接口方法的类中使用PageHelper类中的starPage(int    pageNum, int    pageSize)方法，该Java语句要在调用接口方法的前面

### 在dao接口中添加一个方法

```java
package com.studymyself.dao;

import com.studymyself.entity.User;

import java.util.List;

//接口操作t_user表
public interface UserDao {

    //通过if动态sql语句查询数据,参数必须是Java对象
    public List<User> selectByIf(User user);

    //通过if和where动态sql语句查询数据,参数必须是Java对象
    public List<User> selectByWhere(User user);

    //执行查询有in条件的sql语句
    public List<User> selectByForeach(List<Integer> integers);
    
    //查询数据分页显示
    public List<User> selectForPageHelper();
}

```

### 测试程序代码如下

```java
 @Test
    public void testSelectForPageHelper(){

        //获取SqlSession对象
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        //通过SqlSession对象中的方法获取实现类对象
        UserDao userDao = sqlSession.getMapper(UserDao.class);

        //设置查询的页码和每页的记录条数
        PageHelper.startPage(2,3);

        //上面设置后存到list集合的就只有三条数据了
        List<User> users = userDao.selectForPageHelper();

        for (User user:
             users) {
            System.out.println(user);
        }

    }
```

### 日志和解析如下

![](F:\Git_Repositories\MyBatis\截图\maven-mybatis\3.png)

