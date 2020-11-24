# Java综合实战

## 集成MyBatisPlus
1. 在pom.xml文件引入相关的jar包依赖
2. 实现XxxMapper接口。通过此接口要操作数据持久化
3. 对XxxDO实体进行注解的定义，如：数据库表名，字段定义
4. 如需修改Plus默认配置，需实现MyBatisPlugsConfig类
5. 如需自定义一个方法，需要实现XxxxMapper.xml，来定义自定义SQL