## 三层架构

### 包括

##### 界面层（User   Interface   Layer）、业Dat务逻辑层（Business    Logic    Layer）、数据访问层（Data    Access    Layer）

```sql
界面层：和用户打交道，接收用户请求参数，显示处理结果（包含如jsp、html、servlet等）

业务逻辑层：接收界面层传递的数据，计算逻辑，调用数据库，获取数据

数据访问层：访问数据库，执行对数据的查询，修改，删除等。

--三层中在项目目录中对应创建的包：
界面层（Controller层）：controller包，servlet

业务逻辑层（Service层）：service包，XXXService类

数据访问层（Dao层）：dao包，XXXDao类

--三层中java类的交互
用户使用界面层->业务逻辑层->数据访问层（持久层）->数据库（MySQL）
--具体实现过程：
	用户在界面发起请求，界面层接收请求数据传递给业务逻辑层，逻辑层根据请求进行业务处理，需要使用到数据库，然后访问到持久层，持久层连接数据库，数据库根据需求返回数据给持久层，持久层又把数据给业务逻辑层，业务逻辑层拿到数据进行加工，最后把结果通过界面层展示给用户
	
--三层需要的对应处理框架
界面层（Controller层）-servlet-springMVC（框架）

业务逻辑层（Service层）-Service类-spring（框架）

数据访问层（Dao层）-Dao类-MyBatis（框架）
```

