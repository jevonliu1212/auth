### auth项目准备工作

 1. 确认数据库和 `redis` 数据源配置是否一致（都是默认配置），数据库内新建一个叫 `auth` 的库，地址是 `jdbc:mysql://localhost:3306/auth` 。

 2. `RunApplication` 内的 `main` 方法就是启动入口，第一次启动将 `spring.jpa.properties.hibernate.hbm2ddl.auto` 设置为 `create` （会自动建表），然后关掉应用，再改回为 `none` 。
